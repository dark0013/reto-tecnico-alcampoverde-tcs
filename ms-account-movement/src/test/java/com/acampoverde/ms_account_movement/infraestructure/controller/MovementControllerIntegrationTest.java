package com.acampoverde.ms_account_movement.infraestructure.controller;

import com.acampoverde.ms_account_movement.infraestructure.in.controller.MovementController;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.MovementHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MovementControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovementHandler movementHandler;

    private MovementDto getSampleMovement() {
        MovementDto dto = new MovementDto();
        dto.setMovementId(1);
        dto.setDate(LocalDate.of(2025, 8, 11));
        dto.setMovementType("DEPOSIT");
        dto.setTransactionAmount(BigDecimal.valueOf(500.00));
        dto.setAccountId(10);
        return dto;
    }

    @Test
    @DisplayName("GET /v1/movements - return list of moves")
    void testFindAllMovements() throws Exception {
        List<MovementDto> movements = Collections.singletonList(getSampleMovement());
        when(movementHandler.findAll()).thenReturn(movements);

        mockMvc.perform(get("/v1/movements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementType").value("DEPOSIT"))
                .andExpect(jsonPath("$[0].transactionAmount").value(500.00));
    }

    @Test
    @DisplayName("POST /v1/movements - create a movement")
    void testCreateMovement() throws Exception {
        MovementDto request = getSampleMovement();
        when(movementHandler.transaction(any(MovementDto.class))).thenReturn(request);

        mockMvc.perform(post("/v1/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("DELETE /v1/movements/{id} - disable a movement")
    void testDeactivateMovement() throws Exception {
        doNothing().when(movementHandler).deactivateMovement(eq(1));

        mockMvc.perform(delete("/v1/movements/{id}", 1))
                .andExpect(status().isNoContent());
    }
}

