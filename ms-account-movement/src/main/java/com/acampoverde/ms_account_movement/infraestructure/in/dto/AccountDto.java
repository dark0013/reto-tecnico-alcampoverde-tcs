package com.acampoverde.ms_account_movement.infraestructure.in.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {
    private Integer accountId;

    @NotNull(message = "Account number cannot be null")
    @Size(min = 6, max = 20, message = "Account number must be between 6 and 20 characters")
    private String accountNumber;

    @NotNull(message = "Account type cannot be null")
    private String accountType;

    @NotNull(message = "Available balance cannot be null")
    private Double availableBalance;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;
}
