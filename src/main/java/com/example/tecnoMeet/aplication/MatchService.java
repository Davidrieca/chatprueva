package com.example.tecnoMeet.aplication;

import com.example.tecnoMeet.aplication.DTO.MatchDTO;
import com.example.tecnoMeet.domain.Match;
import com.example.tecnoMeet.domain.Status;
import com.example.tecnoMeet.domain.User;
import com.example.tecnoMeet.persistence.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private final MatchRepository repository;

    public MatchService(MatchRepository repository) {
        this.repository = repository;
    }

    public MatchDTO swipe(String likerId, String likedId) {
        Match pending = repository.findPendingMatch(likerId, likedId);
        if (pending != null) {
            pending.setStatus(Status.MATCHED);
            repository.updateMatch(pending);
            return toDTO(pending);
        }
        Match m = new Match(likerId, likedId);
        repository.addMatch(m);
        return toDTO(m);
    }

    public MatchDTO unmatch(String id) {
        Match m = repository.getMatchById(id);
        m.setStatus(Status.UNMATCHED);
        repository.updateMatch(m);
        return toDTO(m);
    }

    private MatchDTO toDTO(Match m) {
        return new MatchDTO(m.getId(), m.getUser1Id(), m.getUser2Id(), m.getStatus().name());
    }
}
