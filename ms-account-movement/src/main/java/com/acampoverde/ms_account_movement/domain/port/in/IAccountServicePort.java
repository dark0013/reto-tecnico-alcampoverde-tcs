package com.acampoverde.ms_account_movement.domain.port.in;

import com.acampoverde.ms_account_movement.domain.model.Account;

import java.util.List;

public interface IAccountServicePort {

    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    Account findAccountNumberById(String accountNumber);

    Account saveAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Integer id);
}
