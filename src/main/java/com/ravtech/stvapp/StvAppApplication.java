package com.ravtech.stvapp;

import com.ravtech.stvapp.dao.BallotDAO;
import com.ravtech.stvapp.entity.Ballot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
        Ballot ballot = new Ballot();

        System.out.println("Creating ballot: " + ballot);

        ballotDAO.createBallot(ballot);

        System.out.println("Done!");
    }

}
