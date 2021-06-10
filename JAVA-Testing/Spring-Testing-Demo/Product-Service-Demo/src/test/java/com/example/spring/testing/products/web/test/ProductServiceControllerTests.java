package com.example.spring.testing.products.web.test;

import com.example.spring.testing.products.model.Product;
import com.example.spring.testing.products.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceControllerTests {

	public static final String PRODUCT_NAME = "Product Name";


	@MockBean
	ProductService productService;

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("GET product/1 - Found")
	void validate_getProductByID_found() throws Exception {
		Product mockProduct=new Product(1, PRODUCT_NAME,1,1);
		doReturn(Optional.of(mockProduct)).when(productService).findById(1);


		mockMvc.perform(get("/product/{id}",1))
				//validating status and contentType
				.andExpect(status().is(HttpStatus.OK.value()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))

				//Validate header values
				.andExpect(header().string("ETAG","\"1\""))
				.andExpect(header().string("LOCATION","/product/1"))

				//Validate response object
				.andExpect(jsonPath("$.id",is(1)))
				.andExpect(jsonPath("$.name",is(PRODUCT_NAME)))
				.andExpect(jsonPath("$.quantity",is(1)))
				.andExpect(jsonPath("$.version",is(1)));

	}//end

	@Test
	@DisplayName("GET /products")
	public void validate_getProducts() throws Exception {
		Product product1=new Product(1, "product name 1",1,1);
		Product product2=new Product(2, "product name 2",1,1);
		List<Product> mockProductList=new ArrayList<>();
		mockProductList.add(product1);
		mockProductList.add(product2);

		//doReturn(mockProductList).when(productService).findAll();
		when(productService.findAll()).thenReturn(mockProductList);

		mockMvc.perform(get("/products"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",iterableWithSize(2)))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.*",hasSize(2)))
				.andExpect(jsonPath("$[0].name",is("product name 1")))
				.andExpect(jsonPath("$[1].name",is("product name 2")));


	}


	@Test
	@DisplayName("POST /product - Success")
	void testCreateProduct() throws Exception {
		// Setup mocked service
		Product postProduct = new Product("Product Name", 10);
		Product mockProduct = new Product(1, "Product Name", 10, 1);
		doReturn(mockProduct).when(productService).save(any());

		mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(postProduct)))

				// Validate the response code and content type
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))

				// Validate the headers
				.andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
				.andExpect(header().string(HttpHeaders.LOCATION, "/product/1"))

				// Validate the returned fields
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Product Name")))
				.andExpect(jsonPath("$.quantity", is(10)))
				.andExpect(jsonPath("$.version", is(1)));
	}
	@Test
	@DisplayName("PUT /product/{id} - Found")
	public void validate_updateProduct_with_correct_productCode() throws Exception {

		// Setup mocked service
		Product putProduct = new Product("Product Name", 10);
		Product mockProduct = new Product(1, "Product Name", 10, 1);
		doReturn(Optional.of(mockProduct)).when(productService).findById(1);
		doReturn(true).when(productService).update(any());

		mockMvc.perform(put("/product/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.IF_MATCH, 1)
				.content(asJsonString(putProduct)))

				// Validate the response code and content type
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))

				// Validate the headers
				.andExpect(header().string(HttpHeaders.ETAG, "\"2\""))
				.andExpect(header().string(HttpHeaders.LOCATION, "/product/1"))

				// Validate the returned fields
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Product Name")))
				.andExpect(jsonPath("$.quantity", is(10)))
				.andExpect(jsonPath("$.version", is(2)));
	}

	@Test
	@DisplayName("PUT /product/{id} - NOT found")
	public void validate_updateProduct_with_wrong_product_id() throws Exception {
		Product mockProduct=new Product(1, PRODUCT_NAME,1,1);
		doReturn(Optional.of(mockProduct)).when(productService).findById(1);

		mockMvc.perform(put("/product/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.IF_MATCH, 2)
				.content(asJsonString(mockProduct)))
				.andExpect(status().is(HttpStatus.CONFLICT.value()));

	}

	@Test
	@DisplayName("PUT /product/{id} - Version Mismatch")
	public void validate_updateProduct_with_wrong_version() throws Exception {
		Product mockProduct=new Product(1, PRODUCT_NAME,1,1);
		doReturn(Optional.of(mockProduct)).when(productService).findById(1);

		mockMvc.perform(put("/product/{id}",1)
								.contentType(MediaType.APPLICATION_JSON)
								.header(HttpHeaders.IF_MATCH, 2)
								.content(asJsonString(mockProduct)))
				.andExpect(status().is(HttpStatus.CONFLICT.value()));

	}



	@Test
	@DisplayName("DELETE /product/{id} - found")
	public void validate_delete_products() throws Exception {
		Product mockProduct=new Product(1, PRODUCT_NAME,1,1);
		doReturn(Optional.of(mockProduct)).when(productService).findById(1);
		doReturn(true).when(productService).delete(mockProduct.getId());

		mockMvc.perform(delete("/product/{id}",1))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("DELETE /product/{id} - delete failed")
	public void validate_delete_failed() throws Exception {
		Product mockProduct=new Product(1, PRODUCT_NAME,1,1);
		doReturn(Optional.of(mockProduct)).when(productService).findById(1);
		doReturn(false).when(productService).delete(mockProduct.getId());

		mockMvc.perform(delete("/product/{id}",1))
				.andExpect(status().isInternalServerError());
	}


	@Test
	@DisplayName("DELETE /product/{id} - not found")
	public void validate_delete_product_not_found() throws Exception {
		Product mockProduct=new Product(1, PRODUCT_NAME,1,1);
		doReturn(Optional.empty()).when(productService).findById(anyInt());


		mockMvc.perform(delete("/product/{id}",1))
				.andExpect(status().isNotFound());
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
