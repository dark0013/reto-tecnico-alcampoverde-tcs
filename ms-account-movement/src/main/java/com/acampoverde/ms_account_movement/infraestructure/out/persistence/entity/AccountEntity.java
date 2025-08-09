package com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_adm_account")
@Getter
@Setter
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    @Column(length = 15, nullable = false)
    private String accountNumber;
    @Column(length = 20, nullable = false)
    private String accountType;
    private Double availableBalance;
    private Boolean status;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementEntity> movements;

}