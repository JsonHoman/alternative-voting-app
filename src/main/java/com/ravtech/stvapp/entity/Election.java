package com.ravtech.stvapp.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ravtech.stvapp.entity.deserializer.ElectionDeserializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "election")
@Data
@NoArgsConstructor
//@JsonDeserialize(using = ElectionDeserializer.class)
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ballot_id")
    private Ballot ballot;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                           CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "election_voter",
               joinColumns = @JoinColumn(name = "election_id"),
               inverseJoinColumns = @JoinColumn(name = "voter_id"))
    private Set<Voter> voters;

    public Election(String startDate, String endDate, Ballot ballot, Set<Voter> voters) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.ballot = ballot;
        this.voters = voters;
    }
}
