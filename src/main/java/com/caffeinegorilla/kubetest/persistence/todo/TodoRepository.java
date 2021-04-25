package com.caffeinegorilla.kubetest.persistence.todo;

import com.caffeinegorilla.kubetest.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
