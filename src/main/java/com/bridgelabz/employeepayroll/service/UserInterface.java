package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.*;
import com.bridgelabz.employeepayroll.model.User;

import java.util.Optional;

public interface UserInterface {
    AuthResponseDTO<String,String> registerUser(RegisterDTO registerDTO);

    AuthResponseDTO<String,String> loginUser(LoginDTO loginDTO);

    AuthResponseDTO<String, String> forgotPassword(ForgotPasswordDTO forgotPasswordDTO);

    AuthResponseDTO<String, String> resetPassword(ResetPasswordDTO resetPasswordDTO);

    boolean matchPassword(String rawPassword, String encodedPassword);

    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);

    String generateOtp();
}
