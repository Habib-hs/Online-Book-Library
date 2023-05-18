package com.HabibDev.BookShopApplication.service;

import com.HabibDev.BookShopApplication.model.AuthenticationRequest;
import com.HabibDev.BookShopApplication.model.AuthenticationResponse;
import com.HabibDev.BookShopApplication.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> register(UserRequestModel requestModel);
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest requestModel);
}