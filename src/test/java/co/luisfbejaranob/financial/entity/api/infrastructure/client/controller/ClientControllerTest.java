package co.luisfbejaranob.financial.entity.api.infrastructure.client.controller;

import co.luisfbejaranob.financial.entity.api.application.usecase.ClientUseCase;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientInsufficientAge;
import co.luisfbejaranob.financial.entity.api.domain.client.ClientErrors.ClientNotFound;
import co.luisfbejaranob.financial.entity.api.infrastructure.client.persistence.postgresql.ClientMother;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientUseCase useCase;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findById() throws Exception
    {
        var reference = ClientMother.getClient();
        given(useCase.findById(reference.getId())).willReturn(reference);

        mockMvc.perform(get("/clients/%d".formatted(reference.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.identificationNumber")
                        .value(reference.getIdentificationNumber()));
    }

    @Test
    void findByIdThrowClientNotFound() throws Exception
    {
        given(useCase.findById(5L)).willThrow(new ClientNotFound(5L));

        mockMvc.perform(get("/clients/%d".formatted(5L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Client with ID '5' not found"));
    }

    @Test
    void findByIdentificationNumber() throws Exception
    {
        var reference = ClientMother.getClient();
        given(useCase.findByIdentificationNumber(reference.getIdentificationNumber()))
                .willReturn(reference);

        mockMvc.perform(get("/clients/identification-number/%s".formatted(reference.getIdentificationNumber()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.identificationNumber")
                        .value(reference.getIdentificationNumber()));
    }

    @Test
    void create() throws Exception
    {
        var payload = ClientMother.getPayloadClient();
        var reference = ClientMother.getClient();
        given(useCase.create(payload)).willReturn(reference);

        mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(reference.getId()));
    }

    @Test
    void createThrowClientInsufficientAge() throws Exception
    {
        var payload = ClientMother.getPayloadClientMinor();
        given(useCase.create(payload)).willThrow(new ClientInsufficientAge());

        mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Client is a minor and cannot register"));
    }

    @Test
    void update() throws Exception
    {
        var reference = ClientMother.getClient();
        reference.setEmail("luisfbejaranob27@gmail.com");
        given(useCase.update(reference)).willReturn(reference);

        mockMvc.perform(put("/clients").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reference)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(reference.getEmail()));
    }

    @Test
    void deleteById() throws Exception
    {
        var reference = ClientMother.getClient();
        willDoNothing().given(useCase).deleteById(reference.getId());

        mockMvc.perform(delete("/clients/%d".formatted(reference.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}