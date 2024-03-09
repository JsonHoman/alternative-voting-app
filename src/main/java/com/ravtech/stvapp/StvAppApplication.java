package com.ravtech.stvapp;

import com.ravtech.stvapp.entity.*;
import com.ravtech.stvapp.entity.enumeration.ElectionType;
import com.ravtech.stvapp.service.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class StvAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StvAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ElectionService electionService) {
        return runner -> {
            log.info("Starting up");
//            createElection(electionService);

            // TODO: NEXT
//            castVote();
//            endElection();
        };
    }

    private void castVote() {
    }

    private void createElection(ElectionService electionService) {

        // CREATING VOTERS
        Set<Voter> voters = new HashSet<>();
        Voter uniqueVoter = new Voter("Andy", "Alans");

            // CREATING PERSON INFO
            PersonInfo personInfo = new PersonInfo("Male", "White", "Mechanic", "Pittsburgh", "Single", "Former Veteran");
            uniqueVoter.setPersonInfo(personInfo);

        voters.add(uniqueVoter);
        voters.add(new Voter("Brandon", "Bunch"));
        voters.add(new Voter("Charlie", "Chaplin"));

        // CREATING BALLOT SELECTIONS
        Set<BallotSelection> ballotSelections = new HashSet<>();

        BallotSelection ballotSelection1 = new BallotSelection("What is your favorite ice cream?", ElectionType.STAR);

            // CREATING BALLOT 1 ISSUES
            Set<Issue> ballot1Issues = new HashSet<>();
            ballot1Issues.add(new Issue("Chocolate"));
            ballot1Issues.add(new Issue("Vanilla"));
            ballot1Issues.add(new Issue("Strawberry"));

        ballotSelection1.setIssues(ballot1Issues);
        ballotSelections.add(ballotSelection1);

        BallotSelection ballotSelection2 = new BallotSelection("CEO Selection", ElectionType.STAR);

            // CREATING BALLOT 2 CANDIDATES
            Set<Candidate> candidates = new HashSet<>();
            candidates.add(new Candidate("Donald", "Trump"));
            candidates.add(new Candidate("Michael", "Jordan"));
            candidates.add(new Candidate("Will", "Ferrell"));
            candidates.add(new Candidate("Martha", "Stewart"));

        ballotSelection2.setCandidates(candidates);
        ballotSelections.add(ballotSelection2);

        // CREATING BALLOT
        Ballot ballot = new Ballot(ballotSelections);

        // CREATING ELECTION
        Election election = new Election(
                ZonedDateTime.of(2024, 3, 10, 12, 0, 0, 0, ZoneId.of("EST")),
                ZonedDateTime.of(2024, 3, 15, 12, 0, 0, 0, ZoneId.of("EST")),
                ballot, voters);

        log.info("Creating election: " + election);
        electionService.createElection(election);

        log.info("Done!");
    }
}
