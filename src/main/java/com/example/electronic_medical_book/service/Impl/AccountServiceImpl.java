package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.config.authenticate.Account;
import com.example.electronic_medical_book.config.authenticate.AccountRole;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.repository.AccountRepository;
import com.example.electronic_medical_book.repository.RoleRepository;
import com.example.electronic_medical_book.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Account createAccount(Account admin, Set<AccountRole> userRoles) throws Exception {
        Account local = this.accountRepository.findByUsername(admin.getUsername());
        if (local != null) {
            System.out.println("Admin has already");
            throw new RequestException("This account has already!");
        } else {
            for (AccountRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            admin.getUserRole().addAll(userRoles);
            local = this.accountRepository.save(admin);

        }
        return local;
    }

    @Override
    public Account findAccount(String username) {
        Account local = this.accountRepository.findByUsername(username);
        if (local == null) {
            System.out.println("Not found this account: " + username);
            throw new RequestException("Not found account with username: " + username);
        } else
            return this.accountRepository.findByUsername(username);

    }
}
