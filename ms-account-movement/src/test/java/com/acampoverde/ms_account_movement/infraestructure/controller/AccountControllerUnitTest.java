package com.acampoverde.ms_account_movement.infraestructure.controller;


import com.acampoverde.ms_account_movement.infraestructure.in.controller.AccountController;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.AccountDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.AccountHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountControllerUnitTest {

    private AccountHandler accountHandler;
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountHandler = mock(AccountHandler.class);
        accountController = new AccountController(accountHandler);
    }

    @Test
    void shouldReturnAllAccounts() {
        List<AccountDto> mockList = List.of(new AccountDto());
        when(accountHandler.findAllAccount()).thenReturn(mockList);

        ResponseEntity<List<AccountDto>> response = accountController.findAllAccount();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldReturnAccountById() {
        AccountDto mockDto = new AccountDto();
        when(accountHandler.findAccountById(1)).thenReturn(mockDto);

        ResponseEntity<AccountDto> response = accountController.findAccountById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldSaveAccount() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        AccountDto input = new AccountDto();
        input.setAccountId(1);
        when(accountHandler.saveAccount(any())).thenReturn(input);

        ResponseEntity<AccountDto> response = accountController.saveAccount(input);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void shouldUpdateAccount() {
        AccountDto dto = new AccountDto();
        when(accountHandler.updateAccount(dto)).thenReturn(dto);

        ResponseEntity<AccountDto> response = accountController.updateAccount(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldDeleteAccount() {
        doNothing().when(accountHandler).deleteAccount(1);

        ResponseEntity<Void> response = accountController.deleteAccount(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
