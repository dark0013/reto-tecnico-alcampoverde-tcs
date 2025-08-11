package com.acampoverde.ms_account_movement.infraestructure.out.persistence.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class MovementReportDto {
    private Integer accountId;
    private LocalDate date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private Double availableBalance;
    private Boolean status;
    private BigDecimal transactionAmount;
    private BigDecimal movementAvailableBalance;

    public MovementReportDto(LocalDate date, String customerName, String accountNumber, String accountType,
                                Double availableBalance, Boolean status,
                                BigDecimal transactionAmount, BigDecimal movementAvailableBalance) {
        this.date = date;
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.availableBalance = availableBalance;
        this.status = status;
        this.transactionAmount = transactionAmount;
        this.movementAvailableBalance = movementAvailableBalance;
    }
}
