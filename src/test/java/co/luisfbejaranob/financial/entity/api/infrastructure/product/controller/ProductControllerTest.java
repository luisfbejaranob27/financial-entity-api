package co.luisfbejaranob.financial.entity.api.infrastructure.product.controller;

import co.luisfbejaranob.financial.entity.api.application.usecase.ProductUseCase;
import co.luisfbejaranob.financial.entity.api.infrastructure.product.persistence.postgresql.ProductMother;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findById() throws Exception
    {
        var reference = ProductMother.getProductSavingsAccountActive();
        given(useCase.findById(reference.getId())).willReturn(reference);

        mockMvc.perform(get("/products/%s".formatted(reference.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountNumber").value(reference.getAccountNumber()));
    }

    @Test
    void findByAccountNumber() throws Exception
    {
        var reference = ProductMother.getProductSavingsAccountActive();
        given(useCase.findByAccountNumber(reference.getAccountNumber())).willReturn(reference);

        mockMvc.perform(get("/products/account-number/%s".formatted(reference.getAccountNumber()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountNumber").value(reference.getAccountNumber()));
    }
}