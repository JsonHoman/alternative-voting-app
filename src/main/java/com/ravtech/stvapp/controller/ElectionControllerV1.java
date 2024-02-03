package com.ravtech.stvapp.controller;

import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.ElectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ElectionControllerV1 {

    private final ElectionService electionService;

    @Autowired
    public ElectionControllerV1(ElectionService electionService) {
        this.electionService = electionService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/election")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        try {
            log.info("Creating election: " + election);

            Election createdElection = electionService.createElection(election);

            return ResponseEntity.ok(createdElection);

        } finally {
            log.info("Done!");
        }
    }

//    @GetMapping("/election")
//    public ResponseEntity<List<Election>> readVoterElections() {
//
//        // TODO: Change assignment value.
//        int voterId = 1;
//
//        try {
//            log.info("Reading all elections with Voter ID: " + voterId);
//
//            // for specific voter
//            List<Election> readVoterElections = electionService.getVoterElections();
//
//            // TODO: Handle empty list. Do we want a list or a set returned?
//
//            return ResponseEntity.ok(readVoterElections);
//
//        } finally {
//            log.info("Done!");
//        }
//    }
//
//    @GetMapping("/election/{electionId}")
//    public ResponseEntity<Election> readElection(@PathVariable int electionId) {
//        try {
//            log.info("Reading election with ID: " + electionId);
//
//            Optional<Election> readElection = electionService.getElection(electionId);
//
//            if (readElection.isEmpty()) {
//                throw new EntityNotFoundException("No election found with ID: " + electionId);
//            }
//
//            return ResponseEntity.ok(readElection.get());
//
//        } finally {
//            log.info("Done!");
//        }
//    }
}
