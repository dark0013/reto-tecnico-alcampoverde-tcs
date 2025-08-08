package com.acampoverde.ms_account_movement.infraestructure.controller;


import com.acampoverde.ms_account_movement.infraestructure.in.controller.MovementController;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.MovementHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class MovementControllerUnitTest {

    private MovementHandler movementTransaction;
    private MovementController movementController;

    @BeforeEach
    void setUp() {
        movementTransaction = mock(MovementHandler.class);
        movementController = new MovementController(movementTransaction);
    }

    private MovementDto createMockMovementDto() {
        MovementDto dto = new MovementDto();
        dto.setMovementId(1);
        dto.setDate(LocalDate.now());
        dto.setMovementType("DEPOSIT");
        dto.setAmount(new BigDecimal("100.00"));
        dto.setBalance(new BigDecimal("1500.00"));
        dto.setAccountId(1);
        return dto;
    }

    @Test
    void shouldReturnAllMovements() {
        List<MovementDto> mockList = List.of(createMockMovementDto());
        when(movementTransaction.findAll()).thenReturn(mockList);

        ResponseEntity<List<MovementDto>> response = movementController.fincAllTransaction();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("DEPOSIT", response.getBody().get(0).getMovementType());
    }

    @Test
    void shouldCreateMovement() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        MovementDto input = createMockMovementDto();
        when(movementTransaction.transaction(any())).thenReturn(input);

        ResponseEntity<MovementDto> response = movementController.createTransaction(input);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation());
        assertTrue(response.getHeaders().getLocation().toString().endsWith("/1"));

        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void shouldDeactivateMovement() {
        doNothing().when(movementTransaction).deactivateMovement(1);

        ResponseEntity<Void> response = movementController.deactivateMovement(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(movementTransaction).deactivateMovement(1);
    }
}

