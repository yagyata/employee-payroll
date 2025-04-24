package com.bridgelabz.employeepayroll.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "Full name cannot be Empty")
    @Size (min = 3, message = "Full name must be min 3 character long")
    @Pattern(regexp = "[A-Z][a-zA-Z\\s]*", message = "Invalid username")
    private String fullName;

    @NotBlank
    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Invalid password")
    private String password;
}
