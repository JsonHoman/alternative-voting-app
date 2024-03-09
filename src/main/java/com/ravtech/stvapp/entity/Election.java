package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "election")
@Data
@NoArgsConstructor
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ballot_id")
    private Ballot ballot;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                           CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "election_voter",
               joinColumns = @JoinColumn(name = "election_id"),
               inverseJoinColumns = @JoinColumn(name = "voter_id"))
    private Set<Voter> voters;

    public Election(ZonedDateTime startDate, ZonedDateTime endDate, Ballot ballot, Set<Voter> voters) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.ballot = ballot;
        this.voters = voters;
    }
}
