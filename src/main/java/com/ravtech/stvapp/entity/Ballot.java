package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "ballot")
@Data
public class Ballot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // TODO: Don't want local date time.
    @Column(name = "submission_date")
    private String submissionDate;

    @OneToOne(mappedBy = "ballot",
              cascade = {CascadeType.ALL})
    @JoinColumn(name = "election_id")
    private Election election;

    @OneToMany(mappedBy = "ballot",
               cascade = {CascadeType.ALL})
    private List<BallotSelection> ballotSelection;
}