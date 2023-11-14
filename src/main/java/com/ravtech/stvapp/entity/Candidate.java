package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "candidate")
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ballot_selection_id")
    private BallotSelection ballotSelection;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                         CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "candidate_id")
    private PersonInfo personInfo;

    @OneToMany(mappedBy = "candidate",
               cascade = {CascadeType.ALL})
    private List<Vote> votes;
}
