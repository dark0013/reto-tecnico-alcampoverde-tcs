package com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MovementReportEntity {
    private LocalDate date;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private Boolean status;
    private BigDecimal amount;
    private Double initialBalance;
}
