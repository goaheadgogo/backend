package com.patientpal.backend.patient.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.patientpal.backend.patient.dto.request.PatientProfileCreateRequest;
import com.patientpal.backend.patient.dto.request.PatientProfileUpdateRequest;
import com.patientpal.backend.patient.dto.response.PatientProfileResponse;
import com.patientpal.backend.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patient")
public class PatientControllerV1 {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientProfileResponse> createPatientProfile(@AuthenticationPrincipal User currentMember,
                                                     @RequestBody @Valid PatientProfileCreateRequest patientProfileCreateRequest) {
        PatientProfileResponse patientProfileResponse = patientService.savePatientProfile(currentMember.getUsername(), patientProfileCreateRequest);
        return ResponseEntity.status(CREATED).body(patientProfileResponse);
    }

    @GetMapping
    public ResponseEntity<PatientProfileResponse> getPatientProfile(@AuthenticationPrincipal User currentMember) {
        return ResponseEntity.status(OK).body(patientService.getProfile(currentMember.getUsername()));
    }

    @PatchMapping
    public ResponseEntity<Void> updatePatientProfile(@AuthenticationPrincipal User currentMember,
                                                     @RequestBody @Valid PatientProfileUpdateRequest patientProfileUpdateRequest) {
        patientService.updatePatientProfile(currentMember.getUsername(), patientProfileUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePatientProfile(@AuthenticationPrincipal User currentMember) {
        patientService.deletePatientProfile(currentMember.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("register/toMatchList")
    public ResponseEntity<Void> registerPatientProfileToMatchList(@AuthenticationPrincipal User currentMember) {
        patientService.registerPatientProfileToMatchList(currentMember.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("unregister/toMatchList")
    public ResponseEntity<Void> unregisterPatientProfileToMatchList(@AuthenticationPrincipal User currentMember) {
        patientService.unregisterPatientProfileToMatchList(currentMember.getUsername());
        return ResponseEntity.noContent().build();
    }
}
