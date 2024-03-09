package com.ravtech.stvapp.entity;

import com.ravtech.stvapp.entity.enumeration.ElectionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "ballot_selection")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class BallotSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "title", nullable = false)
	private String title;

    @Column(name = "description")
	private String description;

    @NonNull
    @Column(name = "election_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ElectionType electionType;

    @Column(name = "round", nullable = false)
    private int round = 1;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    private Ballot ballot;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ballot_selection_id", referencedColumnName = "id")
    private Set<Candidate> candidates;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ballot_selection_id", referencedColumnName = "id")
    private Set<Issue> issues;

    public BallotSelection(@NonNull String title, String description, @NonNull ElectionType electionType, Set<Candidate> candidates, Set<Issue> issues) {
        this.title = title;
        this.description = description;
        this.electionType = electionType;
        this.candidates = candidates;
        this.issues = issues;
    }

    // TODO: CHECK IF THIS IS ACTUALLY NEEDED.
    public void setElectionType(String electionType) {
        this.electionType = ElectionType.valueOf(electionType);
    }
}


