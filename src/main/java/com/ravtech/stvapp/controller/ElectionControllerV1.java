package com.ravtech.stvapp.controller;

import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.ElectionService;
import com.ravtech.stvapp.service.VoterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ElectionControllerV1 {

    private final ElectionService electionService;
    private final VoterService voterService;

    @Autowired
    public ElectionControllerV1(ElectionService electionService, VoterService voterService) {
        this.electionService = electionService;
        this.voterService = voterService;
    }

    @GetMapping("/hello")
//    @PreAuthorize("hasRole('ROLE_VOTER')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/elections")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        try {
            log.info("Creating election: " + election);

            Election createdElection = electionService.createElection(election);

            return ResponseEntity.ok(createdElection);

        } finally {
            log.info("Done!");
        }
    }

    @GetMapping("/elections")
    public ResponseEntity<List<Election>> readVoterElections() {

        try {
            // Get the currently authenticated user
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {
                String username = authentication.getName();

                // Use the username or identifier to obtain the voter ID from your user repository
                int voterId = voterService.getVoterIdByUsername(username);

                log.info("Reading all elections with Voter ID: " + voterId);

                // for the specific voter
                List<Election> readVoterElections = electionService.getVoterElections(voterId);

                // TODO: Handle empty list. Do we want a list or a set returned?

                return ResponseEntity.ok(readVoterElections);
            } else {
                // No user is authenticated
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch(Exception ex) {
//            return ResponseEntity.
        } finally {
            log.info("Done!");
        }
    }

    @GetMapping("/elections/{electionId}")
    public ResponseEntity<Election> readElection(@PathVariable int electionId) {
        try {
            log.info("Reading election with ID: " + electionId);

            Optional<Election> readElection = electionService.getElection(electionId);

            if (readElection.isEmpty()) {
                throw new EntityNotFoundException("No election found with ID: " + electionId);
            }

            return ResponseEntity.ok(readElection.get());

        } catch(Exception ex) {

        } finally {
            log.info("Done!");
        }
    }
}
