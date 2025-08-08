package com.acampoverde.ms_account_movement.infraestructure.in.controller;


import com.acampoverde.ms_account_movement.infraestructure.in.dto.AccountDto;
import com.acampoverde.ms_account_movement.infraestructure.in.handler.AccountHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountHandler accountHandler;

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAllAccount() {
        return new ResponseEntity<>(accountHandler.findAllAccount(), OK);
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<AccountDto> findAccountNumberById(@PathVariable String accountNumber){
        return new ResponseEntity<>(accountHandler.findAccountNumberById(accountNumber), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable Integer id){
        return new ResponseEntity<>(accountHandler.findAccountById(id), OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> saveAccount(@Valid @RequestBody AccountDto account) {
        AccountDto accountDto = accountHandler.saveAccount(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountDto.getAccountId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto account) {
        return new ResponseEntity<>(accountHandler.updateAccount(account), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        accountHandler.deleteAccount(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}

