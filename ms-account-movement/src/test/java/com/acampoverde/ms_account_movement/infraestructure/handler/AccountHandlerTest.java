package com.acampoverde.ms_account_movement.infraestructure.handler;


import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.domain.port.in.IAccountServicePort;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.AccountDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.AccountHandler;
import com.acampoverde.ms_account_movement.infraestructure.in.mapper.IAccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class AccountHandlerTest {

    @Mock
    private IAccountServicePort accountService;

    @Mock
    private IAccountMapper accountMapper;

    @InjectMocks
    private AccountHandler accountHandler;

    private Account accountDomain;
    private AccountDto accountDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        accountDomain = Account.builder()
                .accountId(1)
                .accountNumber("1234567890")
                .accountType("SAVINGS")
                .availableBalance(500.0)
                .status(true)
                .customerId(101)
                .build();

        accountDto = new AccountDto();
        accountDto.setAccountId(1);
        accountDto.setAccountNumber("1234567890");
        accountDto.setAccountType("SAVINGS");
        accountDto.setAvailableBalance(500.0);
        accountDto.setStatus(true);
        accountDto.setCustomerId(101);
    }

    @Test
    void findAllAccount_returnsDtoList() {
        when(accountService.findAllAccount()).thenReturn(List.of(accountDomain));
        when(accountMapper.toDto(accountDomain)).thenReturn(accountDto);

        List<AccountDto> result = accountHandler.findAllAccount();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1234567890", result.get(0).getAccountNumber());

        verify(accountService).findAllAccount();
        verify(accountMapper).toDto(accountDomain);
    }

    @Test
    void findAccountById_returnsDto() {
        when(accountService.findAccountById(1)).thenReturn(accountDomain);
        when(accountMapper.toDto(accountDomain)).thenReturn(accountDto);

        AccountDto result = accountHandler.findAccountById(1);

        assertNotNull(result);
        assertEquals(1, result.getAccountId());

        verify(accountService).findAccountById(1);
        verify(accountMapper).toDto(accountDomain);
    }

    @Test
    void findAccountNumberById_returnsDto() {
        when(accountService.findAccountNumberById("1234567890")).thenReturn(accountDomain);
        when(accountMapper.toDto(accountDomain)).thenReturn(accountDto);

        AccountDto result = accountHandler.findAccountNumberById("1234567890");

        assertNotNull(result);
        assertEquals("1234567890", result.getAccountNumber());

        verify(accountService).findAccountNumberById("1234567890");
        verify(accountMapper).toDto(accountDomain);
    }

    @Test
    void saveAccount_returnsDto() {
        when(accountMapper.toDomain(accountDto)).thenReturn(accountDomain);
        when(accountService.saveAccount(accountDomain)).thenReturn(accountDomain);
        when(accountMapper.toDto(accountDomain)).thenReturn(accountDto);

        AccountDto result = accountHandler.saveAccount(accountDto);

        assertNotNull(result);
        assertEquals(accountDto.getAccountId(), result.getAccountId());

        verify(accountMapper).toDomain(accountDto);
        verify(accountService).saveAccount(accountDomain);
        verify(accountMapper).toDto(accountDomain);
    }

    @Test
    void updateAccount_returnsDto() {
        when(accountMapper.toDomain(accountDto)).thenReturn(accountDomain);
        when(accountService.updateAccount(accountDomain)).thenReturn(accountDomain);
        when(accountMapper.toDto(accountDomain)).thenReturn(accountDto);

        AccountDto result = accountHandler.updateAccount(accountDto);

        assertNotNull(result);
        assertEquals(accountDto.getAccountId(), result.getAccountId());

        verify(accountMapper).toDomain(accountDto);
        verify(accountService).updateAccount(accountDomain);
        verify(accountMapper).toDto(accountDomain);
    }

    @Test
    void deleteAccount_callsService() {
        doNothing().when(accountService).deleteAccount(1);

        accountHandler.deleteAccount(1);

        verify(accountService).deleteAccount(1);
    }
}

