package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "ballot")
@Data
@NoArgsConstructor
public class Ballot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "ballot",
              cascade = {CascadeType.ALL})
    private Election election;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ballot_id", referencedColumnName = "id")
    private Set<BallotSelection> ballotSelections;

    public Ballot(Set<BallotSelection> ballotSelections) {
        this.ballotSelections = ballotSelections;
    }
}