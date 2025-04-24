package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.AuthResponseDTO;
import com.bridgelabz.employeepayroll.dto.LoginDTO;
import com.bridgelabz.employeepayroll.dto.RegisterDTO;
import com.bridgelabz.employeepayroll.model.User;

import java.util.Optional;

public interface UserInterface {
    AuthResponseDTO<String,String> registerUser(RegisterDTO registerDTO);

    AuthResponseDTO<String,String> loginUser(LoginDTO loginDTO);

    boolean matchPassword(String rawPassword, String encodedPassword);

    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);
}
