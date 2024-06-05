package com.patientpal.backend.patient.domain;

import com.patientpal.backend.matching.domain.Match;
import com.patientpal.backend.member.domain.Address;
import com.patientpal.backend.member.domain.Member;
import com.patientpal.backend.patient.dto.request.PatientProfileUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Match> matches = new ArrayList<>();

    private String name;

    @Column(unique = true)
    private String residentRegistrationNumber; // 주민등록번호, 중복 검사용, (암호화 로직 추가 필요)

    @Embedded
    private Address address;

    private String nokName; //보호자 이름

    private String nokContact;

    @Lob
    private String patientSignificant; //환자 특이사항

    @Lob
    private String careRequirements; //간병 요구사항

    @Builder
    public Patient(Member member, String name, String residentRegistrationNumber, Address address,
                   String nokName, String nokContact, String patientSignificant, String careRequirements) {
        this.member = member;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.address = address;
        this.nokName = nokName;
        this.nokContact = nokContact;
        this.patientSignificant = patientSignificant;
        this.careRequirements = careRequirements;
    }

    public void updateDetailProfile(final PatientProfileUpdateRequest patientProfileUpdateRequest) {
        //validate추가
        this.address = patientProfileUpdateRequest.getAddress();
        this.nokName = patientProfileUpdateRequest.getNokName();
        this.nokContact = patientProfileUpdateRequest.getNokContact();
        this.patientSignificant = patientProfileUpdateRequest.getPatientSignificant();
        this.careRequirements = patientProfileUpdateRequest.getCareRequirements();
    }
}
