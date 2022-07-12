package org.kaczucha.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private long id;
    @Column(name = "BALANCE")
    private double balance;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "USER_ID")
    private Long userId;

    public Account(double balance, String currency) {
        this.balance = balance;
        this.currency = currency;
    }
}
