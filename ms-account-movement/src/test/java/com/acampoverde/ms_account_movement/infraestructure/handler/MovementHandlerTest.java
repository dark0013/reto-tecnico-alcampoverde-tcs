package com.acampoverde.ms_account_movement.infraestructure.handler;


import com.acampoverde.ms_account_movement.domain.model.Movement;
import com.acampoverde.ms_account_movement.domain.port.in.IAccountMovementServicePort;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.MovementDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.MovementHandler;
import com.acampoverde.ms_account_movement.infraestructure.in.mapper.IMovementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class MovementHandlerTest {


    @Mock
    private IAccountMovementServicePort movimentService;

    @Mock
    private IMovementMapper movementMapper;

    @InjectMocks
    private MovementHandler movementHandler;

    private Movement movementDomain;
    private MovementDto movementDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movementDomain = Movement.builder()
                .movementId(1)
                .date(LocalDateTime.now())
                .movementType("DEPOSIT")
                .transactionAmount(100.0)
                .availableBalance(1500.0)
                .status(true)
                .build();

        movementDto = new MovementDto();
        movementDto.setMovementId(1);
        movementDto.setDate(movementDomain.getDate().toLocalDate());
        movementDto.setMovementType("DEPOSIT");
        movementDto.setTransactionAmount(BigDecimal.valueOf(100.0));
        movementDto.setAccountId(1);
    }

    @Test
    void findById_returnsDto() {
        when(movimentService.findById(1)).thenReturn(movementDomain);
        when(movementMapper.toDto(movementDomain)).thenReturn(movementDto);

        MovementDto result = movementHandler.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getMovementId());
        verify(movimentService).findById(1);
        verify(movementMapper).toDto(movementDomain);
    }

    @Test
    void findAll_returnsDtoList() {
        when(movimentService.findAll()).thenReturn(List.of(movementDomain));
        when(movementMapper.toDto(movementDomain)).thenReturn(movementDto);

        List<MovementDto> result = movementHandler.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("DEPOSIT", result.get(0).getMovementType());
        verify(movimentService).findAll();
        verify(movementMapper).toDto(movementDomain);
    }

    @Test
    void transaction_returnsDto() {
        when(movementMapper.toDomain(movementDto)).thenReturn(movementDomain);
        when(movimentService.transaction(movementDomain)).thenReturn(movementDomain);
        when(movementMapper.toDto(movementDomain)).thenReturn(movementDto);

        MovementDto result = movementHandler.transaction(movementDto);

        assertNotNull(result);
        assertEquals(1, result.getMovementId());
        verify(movementMapper).toDomain(movementDto);
        verify(movimentService).transaction(movementDomain);
        verify(movementMapper).toDto(movementDomain);
    }

    @Test
    void deactivateMovement_callsService() {
        doNothing().when(movimentService).deactivateMovement(1);

        movementHandler.deactivateMovement(1);

        verify(movimentService).deactivateMovement(1);
    }
}
