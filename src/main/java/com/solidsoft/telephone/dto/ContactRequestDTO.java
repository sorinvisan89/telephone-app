package com.solidsoft.telephone.dto;


import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {

    @NotEmpty(message = "Name is mandatory")
    @Size(max = 255, message = "Name must be less than {max} length")
    @Pattern(regexp = "(^([^0-9]*)$)", message = "Contact name must contain any digits")
    private String name;

    @NotEmpty(message = "Number is mandatory")
    @Size(max = 255, message = "Number must be less than {max} length")
    @Pattern(regexp = "(^[0-9]*$)", message = "Phone number must contain only digits")
    private String number;

    @Email(message = "Invalid email provided")
    @Size(max = 255, message = "Email must be less than {max} length")
    @NotEmpty(message = "Email is mandatory")
    private String email;
}
