package com.eazybytes.jobportal.Dto;

import jakarta.validation.constraints.*;

public record ContactRequestDto(

        @NotBlank(message = "Name cannot be blank")
        @Size(min=5,max=30,message = "Name must be between 5 and 30 characters")
        String name,

        @NotNull(message="Email cannot be blank")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Message cannot be blank")
        @Size(min=5,max=300,message = "Message must be between 5 and 300 characters")
        String message,

        @NotBlank(message="UserType cannot be blank")
        @Pattern(regexp = "Job Seeker|Employer|Others",message = "User Type must be of: Job Seeker or Employer or Others")
        String userType,

        @NotBlank(message = "Subject cannot be blank")
        @Size(min=3,max=30,message = "Subject must be between 5 and 30 characters")
        String subject) {
}
