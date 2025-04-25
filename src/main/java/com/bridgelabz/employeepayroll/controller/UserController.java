package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.*;
import com.bridgelabz.employeepayroll.service.UserInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInterface userInterface;

    /*
    param:- registerDTO - Object containing user's full name, email, password
    return:- ResponseEntity containing an AuthResponseDTO with registration status and message
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO<String, String>> register(@Valid @RequestBody RegisterDTO registerDTO) {
        AuthResponseDTO<String, String> response = userInterface.registerUser(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    param:- loginDTO - Object containing user's email and password
    return:- ResponseEntity containing an AuthResponseDTO with login status and token or error message
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO<String, String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO<String, String> response = userInterface.loginUser(loginDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<AuthResponseDTO<String,String>> forgotPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        AuthResponseDTO<String, String> response = userInterface.forgotPassword(forgotPasswordDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<AuthResponseDTO<String, String>> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        AuthResponseDTO<String, String> response = userInterface.resetPassword(resetPasswordDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
