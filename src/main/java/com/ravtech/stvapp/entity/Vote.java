package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Entity
@Table(name = "vote")
@Value
@Builder
public final class Vote {
    // TODO: NOT SURE I NEED A BUILDER FOR THIS CLASS YET, I JUST DON'T
    //  KNOW HOW WELL IT WILL HANDLE SAVING AN ID SINCE IT'S IMMUTABLE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final int id;

    // TODO: Don't want local date time.
    @NonNull
    @Column(name = "submission_date")
    private final Instant submissionDate;

    // TODO: ballot selection type should be in here too to ease queries -
    //  not really sure how to handle this yet, probably an interface
    //  along with DTO's

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "issue_id")
    private final Issue issue;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "candidate_id")
    private final Candidate candidate;

    // TODO: ADD A RELATIONSHIP ASSOCIATION WITH VOTERS TO IDENTIFY WHO
    //  CAST THE VOTE: USE A SET

    // TODO: ADD DEFENSIVE COPIES TO PARAMS
    Vote(final int id, @NonNull final Instant submissionDate, final Issue issue, final Candidate candidate) {
        this.id = id;
        this.submissionDate = submissionDate;
        this.issue = new Issue(issue);
        this.candidate = new Candidate(candidate);
    }
}
