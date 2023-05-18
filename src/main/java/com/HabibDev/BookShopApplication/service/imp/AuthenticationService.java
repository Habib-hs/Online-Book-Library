package com.HabibDev.BookShopApplication.service.imp;

import com.HabibDev.BookShopApplication.entity.UserEntity;
import com.HabibDev.BookShopApplication.model.AuthenticationRequest;
import com.HabibDev.BookShopApplication.model.AuthenticationResponse;
import com.HabibDev.BookShopApplication.model.UserRequestModel;
import com.HabibDev.BookShopApplication.repository.UserRepository;
import com.HabibDev.BookShopApplication.service.UserService;
import com.HabibDev.BookShopApplication.utlis.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    //register service Implementation
    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .address(requestModel.getAddress())
                .password(passwordEncoder.encode(requestModel.getPassword()))
                .role(requestModel.getRole())
                .build();
        userRepository.save(userEntity);
        //return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
       AuthenticationResponse authRes = AuthenticationResponse.builder()
               .token(jwtService.generateToken(userEntity))
                .build();
        return new ResponseEntity<>(authRes, HttpStatus.CREATED);
    }

    //login service Implementation
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            var user = userRepository.findByEmail(authenticationRequest.getEmail());
            var jwtToken = jwtService.generateToken(user);
            AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
            return ResponseEntity.ok(authenticationResponse);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

