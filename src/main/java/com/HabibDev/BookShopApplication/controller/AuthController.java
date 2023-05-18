package com.HabibDev.BookShopApplication.controller;

import com.HabibDev.BookShopApplication.model.AuthenticationRequest;
import com.HabibDev.BookShopApplication.model.AuthenticationResponse;
import com.HabibDev.BookShopApplication.model.UserRequestModel;
import com.HabibDev.BookShopApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel){
        return userService.register(requestModel);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest requestModel){
        return userService.login(requestModel);
    }

}
