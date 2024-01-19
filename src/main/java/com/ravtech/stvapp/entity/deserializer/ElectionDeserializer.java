package com.ravtech.stvapp.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ravtech.stvapp.entity.*;

import java.io.IOException;

public class ElectionDeserializer extends StdDeserializer<Election> {

    private final ObjectMapper mapper;

    public ElectionDeserializer() {
        this(null, new ObjectMapper());
    }

    public ElectionDeserializer(Class<?> vc, ObjectMapper mapper) {
        super(vc);
        this.mapper = mapper;
    }

    @Override
    public Election deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode electionNode = jp.getCodec().readTree(jp);
        Election election = new Election();
        // set other fields

        JsonNode ballotNode = electionNode.get("ballot");
        Ballot ballot = new Ballot();
        // set other fields

        JsonNode ballotSelectionsNode = ballotNode.get("ballotSelections");
        for (JsonNode ballotSelectionNode : ballotSelectionsNode) {
            BallotSelection ballotSelection = mapper.treeToValue(ballotSelectionNode, BallotSelection.class);
            // set other fields

            JsonNode candidatesNode = ballotSelectionNode.get("candidates");
            for (JsonNode candidateNode : candidatesNode) {
                Candidate candidate = mapper.treeToValue(candidateNode, Candidate.class);
                // set other fields
                candidate.setBallotSelection(ballotSelection);
                ballotSelection.getCandidates().add(candidate);
            }

            JsonNode issuesNode = ballotSelectionNode.get("issues");
            for (JsonNode issueNode : issuesNode) {
                Issue issue = mapper.treeToValue(issueNode, Issue.class);
                // set other fields
                issue.setBallotSelection(ballotSelection);
                ballotSelection.getIssues().add(issue);
            }

            ballotSelection.setBallot(ballot);
            ballot.getBallotSelections().add(ballotSelection);
        }

        election.setBallot(ballot);

        return election;
    }
}
