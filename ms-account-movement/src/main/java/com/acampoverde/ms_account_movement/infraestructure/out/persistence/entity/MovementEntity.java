package com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_adm_transaction")
@Getter
@Setter
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movementId;
    private LocalDate date;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    private Boolean status = true;
}
