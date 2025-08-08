package com.acampoverde.ms_account_movement.infraestructure.in.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementReportDto {

    private LocalDate date;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private BigDecimal balance;
    private BigDecimal amount;
    private Boolean status;


    public MovementReportDto() {
    }
    public MovementReportDto(LocalDate date, String accountNumber, String accountType, BigDecimal initialBalance, BigDecimal balance, BigDecimal amount, Boolean status) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.balance = balance;
        this.amount = amount;
        this.status = status;
    }

}
