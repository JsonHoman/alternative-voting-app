package com.ravtech.stvapp.entity;

import com.ravtech.stvapp.entity.pojo.ElectionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "ballot_selection")
@Data
public class BallotSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
	private String title;

    @Column(name = "description")
	private String description;

    @Column(name = "type")
    private ElectionType type;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ballot_id")
    private Ballot ballot;

    @OneToMany(mappedBy = "ballotSelection",
               cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "ballotSelection",
               cascade = {CascadeType.ALL})
    private List<Issue> issues;
}


