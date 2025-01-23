package com.youcode.sudest_market.dto.auth;

import com.youcode.sudest_market.annotation.UniqueField;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @UniqueField(fieldName = "username", message = "Username already exists")
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @UniqueField(fieldName = "email", message = "Email already exists")
    private String email;

    @NotBlank(message = "Address is required")
    @Pattern(regexp = "^[a-zA-Z0-9, ]*$", message = "Address must be a valid address")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}$", message = "Phone must be a valid Moroccan phone number")
    private String phoneNumber;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "City must be a valid city")
    private String city;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain an uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain a lowercase letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain a number")
    private String password;

    @NotBlank(message = "Confirm password is required")
    // todo add fields match validation
    private String confirmPassword;
}