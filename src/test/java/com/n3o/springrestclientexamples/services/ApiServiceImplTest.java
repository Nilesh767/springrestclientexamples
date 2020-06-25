package com.n3o.springrestclientexamples.services;

import com.n3o.springrestclientexamples.api.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    ApiService apiService;
    
    @BeforeEach
    void setUp() {
    }

    @Test
    public void testGetUsers() {
        List<User> users = apiService.getUsers(1);
        assertEquals(1, users.size());
    }
}