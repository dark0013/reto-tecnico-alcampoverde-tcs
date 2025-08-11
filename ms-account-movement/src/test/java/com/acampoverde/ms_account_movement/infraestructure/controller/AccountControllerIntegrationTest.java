package com.acampoverde.ms_account_movement.infraestructure.controller;

import com.acampoverde.ms_account_movement.infraestructure.in.dto.AccountDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.AccountHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos a JSON

    @MockBean
    private AccountHandler accountHandler;

    private AccountDto getSampleAccount() {
        return new AccountDto(
                1,
                "123456",
                "SAVINGS",
                1000.0,
                true,
                101
        );
    }

    @Test
    @DisplayName("GET /v1/accounts - return list of accounts")
    void testFindAllAccounts() throws Exception {
        List<AccountDto> accounts = Arrays.asList(getSampleAccount());
        when(accountHandler.findAllAccount()).thenReturn(accounts);

        mockMvc.perform(get("/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber").value("123456"));
    }

    @Test
    @DisplayName("GET /v1/accounts/accountNumber/{accountNumber} - return one account per number")
    void testFindAccountByNumber() throws Exception {
        when(accountHandler.findAccountNumberById("123456")).thenReturn(getSampleAccount());

        mockMvc.perform(get("/v1/accounts/accountNumber/{accountNumber}", "123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountType").value("SAVINGS"));
    }

    @Test
    @DisplayName("GET /v1/accounts/{id} - return one account per ID")
    void testFindAccountById() throws Exception {
        when(accountHandler.findAccountById(1)).thenReturn(getSampleAccount());

        mockMvc.perform(get("/v1/accounts/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availableBalance").value(1000.0));
    }

    @Test
    @DisplayName("POST /v1/accounts - create an account")
    void testSaveAccount() throws Exception {
        AccountDto request = getSampleAccount();
        when(accountHandler.saveAccount(any(AccountDto.class))).thenReturn(request);

        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("PUT /v1/accounts - update an account")
    void testUpdateAccount() throws Exception {
        AccountDto request = getSampleAccount();
        when(accountHandler.updateAccount(any(AccountDto.class))).thenReturn(request);

        mockMvc.perform(put("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true));
    }

    @Test
    @DisplayName("DELETE /v1/accounts/{id} - delete an account")
    void testDeleteAccount() throws Exception {
        doNothing().when(accountHandler).deleteAccount(eq(1));

        mockMvc.perform(delete("/v1/accounts/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
