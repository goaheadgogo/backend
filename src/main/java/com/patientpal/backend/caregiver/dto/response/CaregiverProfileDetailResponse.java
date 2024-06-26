package com.patientpal.backend.caregiver.dto.response;

import com.patientpal.backend.caregiver.domain.Caregiver;
import com.patientpal.backend.member.domain.Address;
import com.patientpal.backend.member.domain.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaregiverProfileDetailResponse {

    private Long memberId;

    private String name;

    private String residentRegistrationNumber;

    private int age;

    private String contact;

    private Gender gender;

    private Address address;

    private float rating;

    private int experienceYears;

    private String specialization;

    private String caregiverSignificant;

    private Boolean isInMatchList;

    private String image;

    @Builder
    public CaregiverProfileDetailResponse(Long memberId, String name, String residentRegistrationNumber, int age, String contact, Gender gender, Address address, float rating, int experienceYears,
                                          String specialization, String caregiverSignificant, Boolean isInMatchList, String image) {
        this.memberId = memberId;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
        this.address = address;
        this.rating = rating;
        this.experienceYears = experienceYears;
        this.specialization = specialization;
        this.caregiverSignificant = caregiverSignificant;
        this.isInMatchList = isInMatchList;
        this.image = image;
    }

    public static CaregiverProfileDetailResponse of(Caregiver caregiver) {
        return CaregiverProfileDetailResponse.builder()
                .memberId(caregiver.getId())
                .name(caregiver.getName())
                .residentRegistrationNumber(caregiver.getResidentRegistrationNumber())
                .age(caregiver.getAge())
                .contact(caregiver.getContact())
                .gender(caregiver.getGender())
                .address(caregiver.getAddress())
                .rating(caregiver.getRating())
                .experienceYears(caregiver.getExperienceYears())
                .specialization(caregiver.getSpecialization())
                .caregiverSignificant(caregiver.getCaregiverSignificant())
                .isInMatchList(caregiver.getIsProfilePublic())
                .image(caregiver.getProfileImageUrl())
                .build();
    }
}
