package com.medislot.controller;

import com.medislot.dto.PatientProfileRequest;
import com.medislot.entity.Patient;
import com.medislot.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/profile")
    public ResponseEntity<Patient> createProfile(
            @Valid @RequestBody PatientProfileRequest request) {
        return ResponseEntity.ok(patientService.createProfile(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<Patient> getMyProfile() {
        return ResponseEntity.ok(patientService.getMyProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
}