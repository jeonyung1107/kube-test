package com.caffeinegorilla.kubetest.service;

import com.caffeinegorilla.kubetest.persistence.todo.Todo;
import com.caffeinegorilla.kubetest.persistence.todo.TodoRepository;
import com.caffeinegorilla.kubetest.user.User;
import com.caffeinegorilla.kubetest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public Todo createTodo(String email, String body){
        User user = userRepository.findByEmail(email).orElseThrow();
        Todo todo = new Todo(null, body, false, user);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodos(String email){
        User user = userRepository.findByEmail(email).orElseThrow();

        return todoRepository.findAllByUser(user);
    }
}
