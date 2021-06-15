package com.example.spring.testing.products.repository.test.jpatest;

import com.example.spring.testing.products.model.Product;
import com.example.spring.testing.products.repository.ProductJPARepository;
import com.example.spring.testing.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceRepositoryTest {

    @Autowired
    private ProductJPARepository repository;


    @Test
    @Rollback(value = false)
   // @Order(1)
    public void validate_save()
    {
        Product product = new Product(1,"sample product",1,1);
        Product savedProduct=repository.save(product);
        Assertions.assertEquals(product.getName(),savedProduct.getName());
    }

    @Test
    @Rollback(value = false)
    @Order(2)
    public void validate_findByID()
    {

        Product product = new Product(1,"sample product",1,1);
        Product savedProduct=repository.save(product);

        Product foundProduct=repository.findById(1).get();

        Assertions.assertEquals(product.getName(),foundProduct.getName());
    }

    @Test
    @Rollback(value = false)
    //@Order(3)
    public void validate_delete()
    {
        Product product = new Product(1,"sample product",1,1);
        Product savedProduct=repository.save(product);

        boolean beforeDeleteProductFound= repository.findById(1).isPresent();

        repository.delete(product);

        boolean afterDeleteProductFound= repository.findById(1).isPresent();

        assertAll(() -> assertTrue(beforeDeleteProductFound),
                  () -> assertFalse(afterDeleteProductFound));
    }

}
