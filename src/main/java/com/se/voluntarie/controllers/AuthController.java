package com.se.voluntarie.controllers;

import com.se.voluntarie.dtos.LoginRequestDto;
import com.se.voluntarie.dtos.RegisterRequestDto;
import com.se.voluntarie.dtos.ResponseDto;
import com.se.voluntarie.models.UserModel;
import com.se.voluntarie.repositories.UserRepository;
import com.se.voluntarie.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body) {
        UserModel userModel = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), userModel.getPassword())) {
            String token = this.tokenService.generateToken(userModel);
            return ResponseEntity.ok(new ResponseDto(userModel.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto body) {
        Optional<UserModel> userModel = this.userRepository.findByEmail(body.email());

        if (userModel.isEmpty()) {
            UserModel newUser = new UserModel();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setName(body.name());
            newUser.setEmail(body.email());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
