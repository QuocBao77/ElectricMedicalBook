package com.example.electronic_medical_book.service.Impl;

import com.example.electronic_medical_book.config.authenticate.Account;
import com.example.electronic_medical_book.exception.RequestException;
import com.example.electronic_medical_book.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUsername(username);
        if(account == null){
            throw new RequestException("Not found this account!");
        }
        return  account;
    }
}