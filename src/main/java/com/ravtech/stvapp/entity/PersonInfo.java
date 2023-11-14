package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "person_info")
@Data
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
}
