package com.ravtech.stvapp.entity;

import com.ravtech.stvapp.entity.pojo.ElectionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "election")
@Data
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "election_type")
    private ElectionType electionType;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "round")
    private int round;

    @OneToOne(cascade = {CascadeType.ALL})
    private Ballot ballot;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                           CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "election_voters",
               joinColumns = @JoinColumn(name = "election_id"),
               inverseJoinColumns = @JoinColumn(name = "voter_id"))
    private List<Voter> voters;
}
