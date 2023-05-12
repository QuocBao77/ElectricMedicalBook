package com.example.electronic_medical_book.config.authenticate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="AccountRole")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountRoleId;

    @ManyToOne(fetch = FetchType.EAGER)

    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)

    private Role role;


}
