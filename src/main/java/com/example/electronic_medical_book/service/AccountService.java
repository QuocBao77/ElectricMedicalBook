package com.example.electronic_medical_book.service;

import com.example.electronic_medical_book.config.authenticate.Account;
import com.example.electronic_medical_book.config.authenticate.AccountRole;

import java.util.Set;

public interface AccountService {

    public Account createAccount(Account account, Set<AccountRole> userRoles) throws Exception;

    Account findAccount(String username) throws Exception;
}
