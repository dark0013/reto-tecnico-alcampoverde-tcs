package com.acampoverde.ms_account_movement.infraestructure.in.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementDto {

    private Integer movementId;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Movement type cannot be null")
    @Size(min = 3, max = 20, message = "Movement type must be between 3 and 20 characters")
    private String movementType;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.00", message = "Balance cannot be negative")
    private BigDecimal balance;

    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;
}

