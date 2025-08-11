package com.acampoverde.ms_account_movement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class MovementReport {
    private LocalDate date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private Double availableBalance;
    private Boolean status;
    private BigDecimal transactionAmount;
    private BigDecimal movementAvailableBalance;
}
