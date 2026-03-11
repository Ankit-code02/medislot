package com.medislot.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorProfileRequest {

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    @Min(value = 0, message = "Experience years must be positive")
    private int experienceYears;

    @NotBlank(message = "Available days are required")
    private String availableDays;

    @NotBlank(message = "Available time start is required")
    private String availableTimeStart;

    @NotBlank(message = "Available time end is required")
    private String availableTimeEnd;

    @Min(value = 0, message = "Consultation fee must be positive")
    private double consultationFee;
}