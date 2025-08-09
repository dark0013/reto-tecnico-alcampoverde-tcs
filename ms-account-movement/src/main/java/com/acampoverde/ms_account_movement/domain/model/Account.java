package com.acampoverde.ms_account_movement.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Account {

    private Integer accountId;
    private String accountNumber;
    private String accountType;
    private Double availableBalance;
    private Boolean status;
    private Integer customerId;
    private List<Movement> movements;

}
