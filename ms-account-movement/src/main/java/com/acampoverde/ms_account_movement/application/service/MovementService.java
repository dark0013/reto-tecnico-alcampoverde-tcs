package com.acampoverde.ms_account_movement.application.service;

import com.acampoverde.ms_account_movement.application.exception.AccountMovementNotFoundException;
import com.acampoverde.ms_account_movement.application.exception.AccountNotFoundException;
import com.acampoverde.ms_account_movement.application.exception.InsufficientBalanceException;
import com.acampoverde.ms_account_movement.application.exception.InvalidMovementTypeException;
import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.domain.model.Movement;
import com.acampoverde.ms_account_movement.domain.port.in.IAccountMovementServicePort;
import com.acampoverde.ms_account_movement.domain.port.out.IAccountMovementRepositoryPort;
import com.acampoverde.ms_account_movement.domain.port.out.IAccountRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;


public class MovementService implements IAccountMovementServicePort {

    private final IAccountMovementRepositoryPort accountMovementRepository;
    private final IAccountRepositoryPort accountRepository;

    public MovementService(IAccountMovementRepositoryPort accountMovementRepository, IAccountRepositoryPort accountRepository) {
        this.accountMovementRepository = accountMovementRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Movement findById(Integer id) {
        return accountMovementRepository.findById(id).orElseThrow(() -> new AccountMovementNotFoundException("Movement not found with id: " + id));
    }

    @Override
    public List<Movement> findAll() {
        return accountMovementRepository.findAll();
    }

    @Override
    public Movement transaction(Movement movement) {
        Account account = accountRepository.findAccountById(movement.getAccount().getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + movement.getAccount().getAccountId()));


        Double currentBalance = account.getInitialBalance();
        Double movementValue = movement.getAmount();


        if ("WITHDRAWAL".equalsIgnoreCase(movement.getMovementType())) {
            if (movementValue > currentBalance) {
                throw new InsufficientBalanceException("Insufficient balance");
            }
            currentBalance -= movementValue;
            movement.setBalance(-movementValue);
        } else if ("DEPOSIT".equalsIgnoreCase(movement.getMovementType())) {
            currentBalance += movementValue;
        } else {
            throw new InvalidMovementTypeException("Invalid movement type: " + movement.getMovementType());
        }


        account.setInitialBalance(currentBalance);
        accountRepository.saveAccount(account);


        movement.setBalance(currentBalance);
        movement.setDate(LocalDateTime.now());

        return accountMovementRepository.transaction(movement);
    }

    @Override
    public void deactivateMovement(Integer movementId) {
        accountMovementRepository.deactivateMovement(movementId);
    }

}
