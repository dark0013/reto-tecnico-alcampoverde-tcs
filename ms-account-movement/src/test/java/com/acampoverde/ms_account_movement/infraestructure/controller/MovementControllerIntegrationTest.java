package com.acampoverde.ms_account_movement.infraestructure.controller;


import com.acampoverde.ms_account_movement.infraestructure.in.controller.MovementController;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.MovementHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovementController.class)
class MovementControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementHandler movementTransaction;

    private ObjectMapper objectMapper;
    private MovementDto mockDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        mockDto = new MovementDto();
        mockDto.setMovementId(1);
        mockDto.setDate(LocalDate.now());
        mockDto.setMovementType("DEPOSIT");
        mockDto.setTransactionAmount(new BigDecimal("100.00"));
        mockDto.setAccountId(1);
    }

    @Test
    void shouldReturnAllMovements() throws Exception {
        when(movementTransaction.findAll()).thenReturn(List.of(mockDto));

        mockMvc.perform(get("/v1/movements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementId").value(1))
                .andExpect(jsonPath("$[0].movementType").value("DEPOSIT"))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldDeactivateMovement() throws Exception {
        doNothing().when(movementTransaction).deactivateMovement(1);

        mockMvc.perform(delete("/v1/movements/1"))
                .andExpect(status().isNoContent());
    }
}

