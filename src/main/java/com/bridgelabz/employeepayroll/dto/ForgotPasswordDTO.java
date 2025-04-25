package com.bridgelabz.employeepayroll.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotPasswordDTO {
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
