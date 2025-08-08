package com.acampoverde.ms_account_movement.infraestructure.controller;


import com.acampoverde.ms_account_movement.infraestructure.in.controller.AccountController;
import com.acampoverde.ms_account_movement.infraestructure.in.dto.AccountDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.AccountHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountHandler accountHandler;

    private ObjectMapper objectMapper;
    private AccountDto mockDto;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        mockDto = new AccountDto();
        mockDto.setAccountId(1);
        mockDto.setAccountNumber("1234567890");
        mockDto.setAccountType("SAVINGS");
        mockDto.setInitialBalance(500.0);
        mockDto.setStatus(true);
        mockDto.setCustomerId(101);
    }

    @Test
    void shouldReturnAllAccounts() throws Exception {
        when(accountHandler.findAllAccount()).thenReturn(List.of(mockDto));

        mockMvc.perform(get("/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber").value("1234567890"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldReturnAccountById() throws Exception {
        when(accountHandler.findAccountById(1)).thenReturn(mockDto);

        mockMvc.perform(get("/v1/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1));
    }

    @Test
    void shouldReturnAccountByAccountNumber() throws Exception {
        when(accountHandler.findAccountNumberById("1234567890")).thenReturn(mockDto);

        mockMvc.perform(get("/v1/accounts/accountNumber/1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("1234567890"));
    }

    @Test
    void shouldCreateAccount() throws Exception {
        when(accountHandler.saveAccount(any(AccountDto.class))).thenReturn(mockDto);

        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void shouldUpdateAccount() throws Exception {
        when(accountHandler.updateAccount(any(AccountDto.class))).thenReturn(mockDto);

        mockMvc.perform(put("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("1234567890"));
    }

    @Test
    void shouldDeleteAccount() throws Exception {
        doNothing().when(accountHandler).deleteAccount(1);

        mockMvc.perform(delete("/v1/accounts/1"))
                .andExpect(status().isNoContent());
    }
}

