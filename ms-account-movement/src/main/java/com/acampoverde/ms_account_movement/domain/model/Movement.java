package com.acampoverde.ms_account_movement.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Movement {

    private Integer movementId;
    private LocalDateTime date;
    private String movementType;
    private Double amount;
    private Double balance;
    private Account account;
    private Boolean status;
}
