package com.n3o.springrestclientexamples.services;

import com.n3o.api.domain.User;

import java.util.List;

public interface ApiService {
    List<User> getUsers(Integer limit);
}
