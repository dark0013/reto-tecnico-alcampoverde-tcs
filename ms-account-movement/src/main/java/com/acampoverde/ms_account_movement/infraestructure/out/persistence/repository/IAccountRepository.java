package com.acampoverde.ms_account_movement.infraestructure.out.persistence.repository;

import com.acampoverde.ms_account_movement.infraestructure.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

}
