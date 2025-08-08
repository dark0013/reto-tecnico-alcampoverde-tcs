package com.acampoverde.ms_account_movement.application;


import com.acampoverde.ms_account_movement.application.exception.AccountNotFoundException;
import com.acampoverde.ms_account_movement.application.service.AccountService;
import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.domain.port.out.IAccountRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private IAccountRepositoryPort accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void shouldReturnAllAccounts() {
        List<Account> accounts = List.of(
                Account.builder().accountId(1).accountNumber("123").build(),
                Account.builder().accountId(2).accountNumber("456").build()
        );

        when(accountRepository.findAllAccount()).thenReturn(accounts);

        List<Account> result = accountService.findAllAccount();

        assertEquals(2, result.size());
        verify(accountRepository).findAllAccount();
    }

    @Test
    void shouldReturnAccountById() {
        Account account = Account.builder().accountId(1).accountNumber("123").build();
        when(accountRepository.findAccountById(1)).thenReturn(Optional.of(account));

        Account result = accountService.findAccountById(1);

        assertEquals("123", result.getAccountNumber());
        verify(accountRepository).findAccountById(1);
    }

    @Test
    void shouldThrowWhenAccountNotFoundById() {
        when(accountRepository.findAccountById(99)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.findAccountById(99));
    }

    @Test
    void shouldReturnAccountByAccountNumber() {
        Account account = Account.builder().accountNumber("ACC123").build();
        when(accountRepository.findAccountNumberById("ACC123")).thenReturn(Optional.of(account));

        Account result = accountService.findAccountNumberById("ACC123");

        assertEquals("ACC123", result.getAccountNumber());
        verify(accountRepository).findAccountNumberById("ACC123");
    }

    @Test
    void shouldThrowWhenAccountNotFoundByAccountNumber() {
        when(accountRepository.findAccountNumberById("INVALID")).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.findAccountNumberById("INVALID"));
    }


    @Test
    void shouldUpdateAccountIfExists() {
        Account input = Account.builder()
                .accountId(1)
                .accountNumber("UPDATE123")
                .accountType("SAVINGS")
                .initialBalance(1000.0)
                .status(true)
                .customerId(1)
                .movements(new ArrayList<>())
                .build();

        when(accountRepository.findAccountById(1)).thenReturn(Optional.of(input));
        when(accountRepository.updateAccount(any(Account.class))).thenReturn(input);

        Account result = accountService.updateAccount(input);

        assertEquals("UPDATE123", result.getAccountNumber());
        verify(accountRepository).updateAccount(any(Account.class));
    }

    @Test
    void shouldThrowWhenUpdatingNonExistentAccount() {
        Account input = Account.builder().accountId(999).build();
        when(accountRepository.findAccountById(999)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.updateAccount(input));
    }

    @Test
    void shouldDeleteAccount() {
        accountService.deleteAccount(5);
        verify(accountRepository).deleteAccount(5);
    }
}

