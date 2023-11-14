package com.ravtech.stvapp;

import com.ravtech.stvapp.dao.BallotDAO;
import com.ravtech.stvapp.entity.Ballot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class StvAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StvAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BallotDAO ballotDAO) {
        return runner -> {
            createBallot(ballotDAO);
        };
    }

    private void createBallot(BallotDAO ballotDAO) {
//		List<Integer> voterIds = new ArrayList<>();
//		voterIds.add(1);
//		voterIds.add(2);
        int voterId = 1;

//		List<BallotSelection> ballotSelections = new ArrayList<>();
//		BallotSelection ballotSelection = new BallotSelection();
//		ballotSelections.add(ballotSelection);

        LocalDateTime submittedDate = LocalDateTime.now();

        boolean submitted = true;

        Ballot ballot = new Ballot(1, voterId, submittedDate, submitted);

        ballotDAO.createBallot(ballot);

        System.out.println("ballot saved!");
    }

}
