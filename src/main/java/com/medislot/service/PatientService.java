package com.medislot.service;

import com.medislot.dto.PatientProfileRequest;
import com.medislot.entity.Patient;
import com.medislot.entity.User;
import com.medislot.repository.PatientRepository;
import com.medislot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public Patient createProfile(PatientProfileRequest request) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (patientRepository.findByUserId(user.getId()).isPresent()) {
            throw new RuntimeException("Patient profile already exists!");
        }

        Patient patient = Patient.builder()
                .user(user)
                .age(request.getAge())
                .gender(request.getGender())
                .bloodGroup(request.getBloodGroup())
                .medicalHistory(request.getMedicalHistory())
                .allergies(request.getAllergies())
                .address(request.getAddress())
                .build();

        return patientRepository.save(patient);
    }

    public Patient getMyProfile() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return patientRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Patient profile not found!"));
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found!"));
    }
}