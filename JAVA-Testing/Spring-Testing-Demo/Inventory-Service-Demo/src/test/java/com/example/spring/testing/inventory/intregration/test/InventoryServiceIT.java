package com.example.spring.testing.inventory.intregration.test;

import com.example.spring.testing.inventory.model.InventoryRecord;
import com.example.spring.testing.inventory.model.PurchaseRecord;
import com.example.spring.testing.inventory.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class InventoryServiceIT {

    @Autowired
    private InventoryService service;

    private WireMockServer wireMockServer;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void beforeEach() {
        // Start the WireMock Server
        wireMockServer = new WireMockServer(9999);
        wireMockServer.start(); // when wire mock server starts it automatically looks in
                                // mapping directory and loads the mapping for stub creation
    }

    @AfterEach
    void afterEach() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Integration Test - validate getInventoryRecord - product found")
    void testGetInventoryByIdSuccess() throws Exception {
           mockMvc.perform(get("/inventory/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/inventory/1"))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.quantity", is(500)))
                .andExpect(jsonPath("$.productName", is("Super Great Product")))
                .andExpect(jsonPath("$.productCategory", is("Great Products")));
    }


    @Test
    @DisplayName("Integration Test - validate addPurchaseRecord")
    void testCreatePurchaseRecord() throws Exception {
        mockMvc.perform(post("/inventory/purchase-record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new PurchaseRecord(1, 495))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/inventory/1"))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.quantity", is(495)))
                .andExpect(jsonPath("$.productName", is("Super Great Product")))
                .andExpect(jsonPath("$.productCategory", is("Great Products")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
