package com.acampoverde.ms_account_movement.infraestructure.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementReportDto {

    private LocalDate date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private Double availableBalance;
    private Boolean status;
    private BigDecimal transactionAmount;
    private BigDecimal movementAvailableBalance;


}
