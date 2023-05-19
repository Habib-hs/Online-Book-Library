package com.HabibDev.BookShopApplication.service.imp;

import com.HabibDev.BookShopApplication.entity.UserEntity;
import com.HabibDev.BookShopApplication.exception.custom.AuthenticationException;
import com.HabibDev.BookShopApplication.exception.custom.RegistrationException;
import com.HabibDev.BookShopApplication.exception.custom.UserAlreadyExistsException;
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
import org.springframework.security.authentication.BadCredentialsException;
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
        String email = requestModel.getEmail();

        // Check if a user with the given email already exists
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }

        //checking if all fields are valid
        if (requestModel == null ||
                requestModel.getFirstName() == null ||
                requestModel.getLastName() == null ||
                requestModel.getEmail() == null ||
                requestModel.getAddress() == null ||
                requestModel.getPassword() == null) {
            throw new RegistrationException("Missing required fields");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .address(requestModel.getAddress())
                .password(passwordEncoder.encode(requestModel.getPassword()))
                .role(requestModel.getRole())
                .build();

        userRepository.save(userEntity);

        AuthenticationResponse authRes = AuthenticationResponse.builder()
                .token(jwtService.generateToken(userEntity))
                .build();

        return new ResponseEntity<>("Your registration is Successfully done!", HttpStatus.CREATED);
    }


    //login service Implementation
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) throws AuthenticationException {
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
        } catch (BadCredentialsException ex) {
            throw new AuthenticationException("Incorrect email or password");
        } catch (Exception ex) {
            throw new AuthenticationException("Failed to authenticate user");
        }
    }


}

