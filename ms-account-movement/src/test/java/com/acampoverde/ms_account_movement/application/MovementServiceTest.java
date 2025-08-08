package com.acampoverde.ms_account_movement.application;


import com.acampoverde.ms_account_movement.application.exception.AccountMovementNotFoundException;
import com.acampoverde.ms_account_movement.application.exception.AccountNotFoundException;
import com.acampoverde.ms_account_movement.application.exception.InsufficientBalanceException;
import com.acampoverde.ms_account_movement.application.exception.InvalidMovementTypeException;
import com.acampoverde.ms_account_movement.application.service.MovementService;
import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.domain.model.Movement;
import com.acampoverde.ms_account_movement.domain.port.out.IAccountMovementRepositoryPort;
import com.acampoverde.ms_account_movement.domain.port.out.IAccountRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {

    @Mock
    private IAccountMovementRepositoryPort movementRepository;

    @Mock
    private IAccountRepositoryPort accountRepository;

    @InjectMocks
    private MovementService movementService;


    @Test
    void shouldReturnMovementById() {
        Movement movement = Movement.builder().movementId(1).movementType("DEPOSIT").amount(100.0).build();
        when(movementRepository.findById(1)).thenReturn(Optional.of(movement));

        Movement result = movementService.findById(1);

        assertEquals("DEPOSIT", result.getMovementType());
        verify(movementRepository).findById(1);
    }


    @Test
    void shouldThrowWhenMovementNotFoundById() {
        when(movementRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(AccountMovementNotFoundException.class, () -> movementService.findById(999));
    }


    @Test
    void shouldReturnAllMovements() {
        List<Movement> movements = List.of(
                Movement.builder().movementId(1).movementType("DEPOSIT").amount(100.0).build(),
                Movement.builder().movementId(2).movementType("WITHDRAWAL").amount(50.0).build()
        );
        when(movementRepository.findAll()).thenReturn(movements);

        List<Movement> result = movementService.findAll();

        assertEquals(2, result.size());
        verify(movementRepository).findAll();
    }


    @Test
    void shouldProcessDepositTransaction() {
        // Arrange
        Account account = Account.builder().accountId(1).initialBalance(200.0).build();
        Movement movement = Movement.builder()
                .movementType("DEPOSIT")
                .amount(100.0)
                .account(account)
                .build();

        when(accountRepository.findAccountById(1)).thenReturn(Optional.of(account));
        when(accountRepository.saveAccount(any())).thenReturn(account);
        when(movementRepository.transaction(any())).thenAnswer(i -> i.getArgument(0));

        Movement result = movementService.transaction(movement);

        assertEquals(300.0, result.getBalance());
        verify(accountRepository).saveAccount(account);
        verify(movementRepository).transaction(any());
    }


    @Test
    void shouldProcessWithdrawalTransaction() {
        Account account = Account.builder().accountId(1).initialBalance(150.0).build();
        Movement movement = Movement.builder()
                .movementType("WITHDRAWAL")
                .amount(50.0)
                .account(account)
                .build();

        when(accountRepository.findAccountById(1)).thenReturn(Optional.of(account));
        when(accountRepository.saveAccount(any())).thenReturn(account);
        when(movementRepository.transaction(any())).thenAnswer(i -> i.getArgument(0));

        Movement result = movementService.transaction(movement);

        assertEquals(100.0, result.getBalance());
        verify(accountRepository).saveAccount(account);
        verify(movementRepository).transaction(any());
    }


    @Test
    void shouldThrowWhenWithdrawalExceedsBalance() {
        Account account = Account.builder().accountId(1).initialBalance(30.0).build();
        Movement movement = Movement.builder()
                .movementType("WITHDRAWAL")
                .amount(50.0)
                .account(account)
                .build();

        when(accountRepository.findAccountById(1)).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> movementService.transaction(movement));
        verify(accountRepository, never()).saveAccount(any());
        verify(movementRepository, never()).transaction(any());
    }


    @Test
    void shouldThrowWhenMovementTypeInvalid() {
        Account account = Account.builder().accountId(1).initialBalance(100.0).build();
        Movement movement = Movement.builder()
                .movementType("INVALID")
                .amount(50.0)
                .account(account)
                .build();

        when(accountRepository.findAccountById(1)).thenReturn(Optional.of(account));

        assertThrows(InvalidMovementTypeException.class, () -> movementService.transaction(movement));
    }


    @Test
    void shouldThrowWhenAccountNotFound() {
        Account account = Account.builder().accountId(1).build();
        Movement movement = Movement.builder().movementType("DEPOSIT").amount(100.0).account(account).build();

        when(accountRepository.findAccountById(1)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> movementService.transaction(movement));
    }


    @Test
    void shouldDeactivateMovement() {
        movementService.deactivateMovement(5);

        verify(movementRepository).deactivateMovement(5);
    }
}
