package com.ravtech.stvapp.controller;

import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ElectionControllerV1 {

    private final ElectionService electionService;

    @Autowired
    public ElectionControllerV1(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping("/election")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        try {
            log.info("Creating election: " + election);

            Election savedElection = electionService.createElection(election);

            return ResponseEntity.ok(savedElection);

        } finally {
            log.info("Done!");
        }
    }
}
