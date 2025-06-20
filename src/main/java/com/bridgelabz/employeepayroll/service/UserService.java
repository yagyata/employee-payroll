package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.*;
import com.bridgelabz.employeepayroll.model.User;
import com.bridgelabz.employeepayroll.repository.UserRepository;
import com.bridgelabz.employeepayroll.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class UserService implements UserInterface{
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtUtility jwtUtility;

    private final Map<String, String> otpStore = new HashMap<>();


    /*
    param:- registerDTO - Object containing user's full name, email, and password
    return:- AuthResponseDTO - Contains success/failure message and details
     */
    @Override
    public AuthResponseDTO<String, String> registerUser(RegisterDTO registerDTO) {
        log.info("Register User: {}", registerDTO.getEmail());
        AuthResponseDTO<String, String> res = new AuthResponseDTO<>();

        //Checking if email already exists
        if (existsByEmail(registerDTO.getEmail())) {
            log.warn("Registration failed: User already exists");
            res.setMessage("error");
            res.setMessageData("User already exists");
            return res;
        }

        //Registering a new user into the system
        User user = new User();
        user.setFullName(registerDTO.getFullName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("User {} registered successfully!", user.getEmail());

        //Sending registration confirmation mail to the user
        emailService.sendEmail(user.getEmail(),"Registered in Employee Payroll App", "Hi..."+ "\n You have been successfully registered");

        res.setMessage("message");
        res.setMessageData("User registered successfully");
        return res;
    }

    /*
    param:- loginDTO - Object containing user email and password
    return:- AuthResponseDTO - containing success/failure message and token info
     */
    @Override
    public AuthResponseDTO<String, String> loginUser(LoginDTO loginDTO) {
        log.info("Login attempt for User: {}", loginDTO.getEmail());
        AuthResponseDTO<String, String> res = new AuthResponseDTO<>();

        //Check if a user with the provided email exists in the database
        Optional<User> userExists = getUserByEmail(loginDTO.getEmail());

        if(userExists.isPresent()) {
            //If user exists retrieve the User object
            User user = userExists.get();

            //Check if the provided password matches the stored password
            if (matchPassword(loginDTO.getPassword(), user.getPassword())) {

                //Generate a JWT token for the user
                String token = jwtUtility.generateToken(user.getEmail());

                //Saves token to user's record
                user.setToken(token);
                userRepository.save(user);

                //log the successful login and token generation
                log.debug("Login successful for User: {} - Token generated", user.getEmail());

                //Send login email to user along with token
                emailService.sendEmail(
                        user.getEmail(),
                        "Logged in Employee Payroll App",
                        "Hi.." + "\nYou have been Successfully logged in! " + token
                );

                //Set the success message and token in the response
                res.setMessage("message");
                res.setMessageData("user logged in successfully: " + token);
            } else {
                //log and return an error response if the password does not match
                log.warn("Invalid credentials for user: {}", loginDTO.getEmail());
                res.setMessage("error");
                res.setMessageData("Invalid Credentials");
            }
        } else {
            //log and return an error response if the user does not exist
            log.error("User not found with email: {}", loginDTO.getEmail());
            res.setMessage("error");
            res.setMessageData("User Not found");
        }

        //Return the final response object
        return res;
    }


    @Override
    public AuthResponseDTO<String, String> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        log.info("Forgot Password for user: {}", forgotPasswordDTO.getEmail());
        AuthResponseDTO<String, String> res = new AuthResponseDTO<>();
        Optional<User> userExists = getUserByEmail(forgotPasswordDTO.getEmail());

        if (userExists.isPresent()) {
            User user = userExists.get();
            String otp = generateOtp();
            otpStore.put(user.getEmail(), otp);

            emailService.sendEmail(user.getEmail(),
                    "OTP Verification",
                    "OTP to reset your password.\nOTP: " + otp);

            res.setMessage("OTP verification");
            res.setMessageData("OTP sent to user");
        } else {
            log.warn("User not found with email: {}", forgotPasswordDTO.getEmail());
            res.setMessage("error");
            res.setMessageData("Invalid Credentials");
        }
        return res;
    }

    @Override
    public AuthResponseDTO<String, String> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        log.info("Password reset request for email: {}", resetPasswordDTO.getEmail());
        AuthResponseDTO<String, String> res = new AuthResponseDTO<>();

        Optional<User> userExists = getUserByEmail(resetPasswordDTO.getEmail());

        if (userExists.isPresent()) {
            User user = userExists.get();
            String storedOtp = otpStore.get(resetPasswordDTO.getEmail());

            if (storedOtp == null || !storedOtp.equals(resetPasswordDTO.getOtp())) {
                log.warn("Reset password failed: Invalid OTP");
                res.setMessage("error");
                res.setMessageData("Invalid OTP");
                return res;
            }

            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            userRepository.save(user);
            otpStore.remove(resetPasswordDTO.getEmail()); // remove OTP after use

            emailService.sendEmail(user.getEmail(), "Password Changed", "Your password has been changed successfully");
            res.setMessage("message");
            res.setMessageData("Password changed successfully");
        } else {
            log.warn("Reset password failed: User not found");
            res.setMessage("error");
            res.setMessageData("User not found");
        }

        return res;
    }


    /*
    param:- rawPassword - Plain password input by user
    param:- encodedPassword - Encrypted password from database
    return true if passwords match, otherwise false
     */
    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        log.debug("Matching password for login attempt");
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /*
    param email - Email address to check
    return true if user exists, otherwise false
     */
    @Override
    public boolean existsByEmail(String email){
        log.debug("Checking if user exists by email: {}", email);
        return userRepository.findByEmail(email).isPresent();
    }

    /*
    param email - Email address to search for
    return Optional containing the User if found, or empty if not
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}