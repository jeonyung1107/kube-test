package com.caffeinegorilla.kubetest.persistence.todo;

import com.caffeinegorilla.kubetest.common.Constants;
import com.caffeinegorilla.kubetest.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = Constants.Table.TODO)
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String body;

    @Column
    private Boolean done;

    @Column
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;
}
