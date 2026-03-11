package com.medislot.controller;

import com.medislot.dto.DoctorProfileRequest;
import com.medislot.entity.Doctor;
import com.medislot.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/profile")
    public ResponseEntity<Doctor> createProfile(
            @Valid @RequestBody DoctorProfileRequest request) {
        return ResponseEntity.ok(doctorService.createProfile(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<Doctor> getMyProfile() {
        return ResponseEntity.ok(doctorService.getMyProfile());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchBySpecialization(
            @RequestParam String specialization) {
        return ResponseEntity.ok(
                doctorService.getDoctorsBySpecialization(specialization));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
}