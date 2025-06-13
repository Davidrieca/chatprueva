package com.example.tecnoMeet.aplication.DTO;

import com.example.tecnoMeet.domain.User;

public record MatchDTO(String id, String likerId, String likedId, String status) {}
