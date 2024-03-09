package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "issue")
@Getter @Setter @ToString
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    private BallotSelection ballotSelection;

    @OneToMany(mappedBy = "issue",
               cascade = {CascadeType.ALL})
    private Set<Vote> votes;

    public Issue(String title) {
        this.title = title;
    }

    public Issue(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Copy Constructor
    public Issue(Issue otherInstance) {
        this.id = otherInstance.getId();
        this.title = otherInstance.getTitle();
        this.description = otherInstance.getDescription();
        this.ballotSelection = otherInstance.getBallotSelection();
        this.votes = otherInstance.getVotes();
    }
}
