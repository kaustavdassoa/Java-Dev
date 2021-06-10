package com.example.spring.testing.products.service.test;

import com.example.spring.testing.products.model.Product;
import com.example.spring.testing.products.repository.ProductRepository;
import com.example.spring.testing.products.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    @DisplayName("findByID - product found")
    public void validate_findById_product_found()
    {
        Product mockProduct=new Product(1, "PRODUCT_NAME",1,1);
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(mockProduct));

        assertTrue(productService.findById(1).isPresent());
    }

    @Test
    @DisplayName("findByID - product not found")
    public void validate_findById_product_not_found()
    {
        Product mockProduct=new Product(1, "PRODUCT_NAME",1,1);
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertFalse(productService.findById(1).isPresent());
    }

    @Test
    @DisplayName("findByID - throw error")
    public void validate_findById_throw_error()
    {
        Product mockProduct=new Product(1, "PRODUCT_NAME",1,1);
        when(productRepository.findById(1)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class,() -> productService.findById(1).isPresent());
    }


    @Test
    @DisplayName("findAll - products found")
    public void validate_findAll_product_found()
    {
        Product mockProduct1=new Product(1, "PRODUCT_NAME",1,1);
        Product mockProduct2=new Product(2, "PRODUCT_NAME",1,1);
        List<Product> listOfMockProducts = new ArrayList<>();
        listOfMockProducts.add(mockProduct1);
        listOfMockProducts.add(mockProduct2);

        when(productRepository.findAll()).thenReturn(listOfMockProducts);

        assertNotNull(productService.findAll());
        assertEquals(2,productService.findAll().size());

    }
}
