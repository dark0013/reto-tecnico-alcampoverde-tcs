package com.acampoverde.ms_account_movement.application.service;

import com.acampoverde.ms_account_movement.application.exception.AccountNotFoundException;
import com.acampoverde.ms_account_movement.domain.model.Account;
import com.acampoverde.ms_account_movement.domain.port.in.IAccountServicePort;
import com.acampoverde.ms_account_movement.domain.port.out.IAccountRepositoryPort;
import com.acampoverde.ms_account_movement.domain.port.out.IRequestMessagePort;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class AccountService implements IAccountServicePort {

    private final IAccountRepositoryPort accountRepository;
    private final IRequestMessagePort requestMessagePort;

    public AccountService(IAccountRepositoryPort accountRepository, IRequestMessagePort requestMessagePort) {
        this.accountRepository = accountRepository;
        this.requestMessagePort = requestMessagePort;
    }


    @Override
    public List<Account> findAllAccount() {
        return this.accountRepository.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
        return this.accountRepository.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("NO ACCOUNT FOUND"));
    }

    @Override
    public Account findAccountNumberById(String accountNumber) {
        return this.accountRepository.findAccountNumberById(accountNumber).orElseThrow(() -> new AccountNotFoundException("NO ACCOUNT FOUND"));

    }

    @Override
    public Account saveAccount(Account account) {
        //antes de agurdar es importante consultar si el cliente existe
        requestMessagePort.sendMessage(account.getCustomerId().toString());
        return this.accountRepository.saveAccount(account);
    }

    public Account updateAccount(Account account) {
        //verificar si el cliente existe con una consulta al API customer
        Optional<Account> existingAccountOpt = accountRepository.findAccountById(account.getAccountId());
        if (existingAccountOpt.isEmpty()) {
            throw new AccountNotFoundException("NO ACCOUNT FOUND");
        }
        Account existingAccount = Account.builder()
                .accountId(account.getAccountId())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initialBalance(account.getInitialBalance())
                .status(account.getStatus())
                .customerId(account.getCustomerId())
                .movements(account.getMovements())
                .build();

        return accountRepository.updateAccount(existingAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        this.accountRepository.deleteAccount(id);
    }

}

