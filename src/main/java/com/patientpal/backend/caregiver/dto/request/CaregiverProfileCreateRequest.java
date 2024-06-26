package com.patientpal.backend.caregiver.dto.request;

import com.patientpal.backend.member.domain.Address;
import com.patientpal.backend.member.domain.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaregiverProfileCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String residentRegistrationNumber;

    // TODO 인증
    @NotNull
    private String contact;

    @NotNull
    private Gender gender;

    @NotNull
    private Address address;

    private float rating;

    private int experienceYears;

    private String specialization;

    private String caregiverSignificant;

    @Builder
    public CaregiverProfileCreateRequest(String name, String residentRegistrationNumber, String contact, Gender gender,
                                         Address address, float rating, int experienceYears, String specialization,
                                         String caregiverSignificant) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.contact = contact;
        this.gender = gender;
        this.address = address;
        this.rating = rating;
        this.experienceYears = experienceYears;
        this.specialization = specialization;
        this.caregiverSignificant = caregiverSignificant;
    }
}
