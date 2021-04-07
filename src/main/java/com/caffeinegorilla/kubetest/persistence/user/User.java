package com.caffeinegorilla.kubetest.persistence.user;

import com.caffeinegorilla.kubetest.common.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.Table.USER)
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public User(String name) {
        this.name = name;
    }
}
