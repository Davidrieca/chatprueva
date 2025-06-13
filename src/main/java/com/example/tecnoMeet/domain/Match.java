package com.example.tecnoMeet.domain;

import com.example.tecnoMeet.utilities.InvalidDataException;

import java.util.UUID;

public class Match {

    private String id = UUID.randomUUID().toString();
    private String user1Id;
    private String user2Id;
    private Status status = Status.PENDING;

    public Match() {}

    public Match(String user1Id, String user2Id) {
        if (user1Id == null || user2Id == null) {
            throw new InvalidDataException("Users required");
        }
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public String getId() { return id; }
    public String getUser1Id() { return user1Id; }
    public String getUser2Id() { return user2Id; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public void setId(String id) { this.id = id; }
}
