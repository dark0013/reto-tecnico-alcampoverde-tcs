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
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private Boolean status;
    private BigDecimal amount;
    private BigDecimal initialBalance;
}
