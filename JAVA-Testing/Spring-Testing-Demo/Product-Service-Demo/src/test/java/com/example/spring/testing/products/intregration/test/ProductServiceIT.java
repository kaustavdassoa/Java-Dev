package com.example.spring.testing.products.intregration.test;

import com.example.spring.testing.products.model.Product;
import com.example.spring.testing.products.repository.ProductRepository;
import com.example.spring.testing.products.service.ProductService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.MockMvc;



import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.sql.DataSource;
import java.sql.SQLException;


@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductServiceIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DataSource dataSource;

    public ConnectionHolder getConnectionHolder() throws SQLException {
        // Return a function that retrieves a connection from our data source
        return () -> dataSource.getConnection();
    }

    @Test
    @DisplayName("Validate getProduct - product found")
    @DataSet("products.yml")
    public void validate_getProduct_product_found() throws Exception {
        mockMvc.perform(get("/product/{id}",2))
                //validating status and contentType
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate header values
                .andExpect(header().string("ETAG","\"2\""))
                .andExpect(header().string("LOCATION","/product/2"))

                //Validate response object
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.name",is("Product 2")))
                .andExpect(jsonPath("$.quantity",is(5)))
                .andExpect(jsonPath("$.version",is(2)));
    }

    @Test
    @DisplayName("Validate getProduct - not found")
    @DataSet("products.yml")
    public void validate_getProduct_product_not_found() throws Exception {
        mockMvc.perform(get("/product/{id}",3))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

}
