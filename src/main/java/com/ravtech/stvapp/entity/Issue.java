package com.ravtech.stvapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ballot_selection_id")
    private BallotSelection ballotSelection;

    @OneToMany(mappedBy = "issue",
               cascade = {CascadeType.ALL})
    private List<Vote> votes;
}
