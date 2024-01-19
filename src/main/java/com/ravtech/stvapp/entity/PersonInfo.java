package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person_info")
@Data
@NoArgsConstructor
public class PersonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sex")
    private String sex;

    @Column(name = "ethnicity")
    private String ethnicity;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "placeOfBirth")
    private String placeOfBirth;

    @Column(name = "maritalStatus")
    private String maritalStatus;

    @Column(name = "veteranStatus")
    private String veteranStatus;

    public PersonInfo(String sex, String ethnicity, String occupation, String placeOfBirth, String maritalStatus, String veteranStatus) {
        this.sex = sex;
        this.ethnicity = ethnicity;
        this.occupation = occupation;
        this.placeOfBirth = placeOfBirth;
        this.maritalStatus = maritalStatus;
        this.veteranStatus = veteranStatus;
    }
}
