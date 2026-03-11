package com.medislot.service;

import com.medislot.dto.DoctorProfileRequest;
import com.medislot.entity.Doctor;
import com.medislot.entity.User;
import com.medislot.repository.DoctorRepository;
import com.medislot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public Doctor createProfile(DoctorProfileRequest request) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (doctorRepository.findByUserId(user.getId()).isPresent()) {
            throw new RuntimeException("Doctor profile already exists!");
        }

        Doctor doctor = Doctor.builder()
                .user(user)
                .specialization(request.getSpecialization())
                .qualification(request.getQualification())
                .experienceYears(request.getExperienceYears())
                .availableDays(request.getAvailableDays())
                .availableTimeStart(request.getAvailableTimeStart())
                .availableTimeEnd(request.getAvailableTimeEnd())
                .consultationFee(request.getConsultationFee())
                .build();

        return doctorRepository.save(doctor);
    }

    public Doctor getMyProfile() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return doctorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Doctor profile not found!"));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
    }
}