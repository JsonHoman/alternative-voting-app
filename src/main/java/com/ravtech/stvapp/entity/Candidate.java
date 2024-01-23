package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "candidate")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ballot_selection_id", referencedColumnName = "id")
    private BallotSelection ballotSelection;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                         CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "person_info_id")
    private PersonInfo personInfo;

    @OneToMany(mappedBy = "candidate",
               cascade = {CascadeType.ALL})
    private List<Vote> votes;

    public Candidate(@NonNull String firstName, @NonNull String lastName, String email, String phoneNumber, String address, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    // Copy Constructor
    public Candidate(Candidate candidate) {
        this.id = candidate.getId();
        this.firstName = candidate.getFirstName();
        this.lastName = candidate.getLastName();
        this.email = candidate.getEmail();
        this.phoneNumber = candidate.getPhoneNumber();
        this.address = candidate.getAddress();
        this.dateOfBirth = candidate.getDateOfBirth();
        this.ballotSelection = candidate.getBallotSelection();
        this.personInfo = candidate.getPersonInfo();
        this.votes = candidate.getVotes();
    }
}
