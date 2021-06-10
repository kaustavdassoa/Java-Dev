package com.example.spring.testing.review.repository.test.withext;

import com.example.spring.testing.review.web.ReviewController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class MongoSpringExtension implements BeforeEachCallback, AfterEachCallback {

    private static final Logger logger = LogManager.getLogger(MongoSpringExtension.class);

    private static Path JSON_PATH = Paths.get("src", "test", "resources", "data");

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        context.getTestMethod().ifPresent(method -> {
            MongoDataFile mongoDataFile = method.getAnnotation(MongoDataFile.class);

            getMongoTemplate(context).ifPresent(mongoTemplate -> {
                try {
                    // Use Jackson's ObjectMapper to load a list of objects from the JSON file
                    List objects = mapper.readValue(JSON_PATH.resolve(mongoDataFile.value()).toFile(),
                    mapper.getTypeFactory().constructCollectionType(List.class, mongoDataFile.classType()));
                    // Save each object into MongoDB
                    objects.forEach(mongoTemplate::save);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            });
        });
    }//beforeEach


    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        context.getTestMethod().ifPresent(method -> {
            // Load the MongoDataFile annotation value from the test method
            MongoDataFile mongoDataFile = method.getAnnotation(MongoDataFile.class);
            // Load the MongoTemplate that we can use to drop the test collection
            Optional<MongoTemplate> mongoTemplate = getMongoTemplate(context);
            mongoTemplate.ifPresent(t -> t.dropCollection(mongoDataFile.collectionName()));
        });
    }



    /**
     * Helper method that uses reflection to invoke the getMongoTemplate() method on the test instance.
     * @param context   The ExtensionContext, which provides access to the test instance.
     * @return          An optional MongoTemplate, if it exists.
     */
    private Optional<MongoTemplate> getMongoTemplate(ExtensionContext context) {
        Optional<Class<?>> clazz = context.getTestClass();
        if (clazz.isPresent()) {
            Class<?> c = clazz.get();
            try {
                // Find the getMongoTemplate method on the test class
                Method method = c.getMethod("getMongoTemplate", null);
                // Invoke the getMongoTemplate method on the test class
                Optional<Object> testInstance = context.getTestInstance();
                if (testInstance.isPresent()) {
                    return Optional.of((MongoTemplate)method.invoke(testInstance.get(), null));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
