package com.n3o.springrestclientexamples.services;

import com.n3o.springrestclientexamples.domain.User;
import com.n3o.springrestclientexamples.domain.UserData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        UserData userData = restTemplate.getForObject("http://private-anon-852ce83faa-apifaketory.apiary-mock.com/api/user?limit="+ limit,UserData.class);
        return userData.getData();
    }
}
