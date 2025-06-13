package com.example.tecnoMeet.persistence;

import com.example.tecnoMeet.domain.Match;
import com.example.tecnoMeet.domain.Status;
import com.example.tecnoMeet.utilities.NotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile("db-h2")
public class MatchRepository {
    private final JdbcClient jdbcClient;


    public MatchRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private Match mapMatch(ResultSet rs) throws SQLException {
        Match m = new Match(rs.getString("liker_id"), rs.getString("liked_id"));
        m.setStatus(Status.valueOf(rs.getString("status")));
        m.setId(rs.getString("id"));

        return m;
    }

    public void addMatch(Match match) {
        jdbcClient.sql("INSERT INTO MATCHES (id,liker_id,liked_id,status) VALUES (:id,:liker,:liked,:status)")
                .param("id", match.getId())
                .param("liker", match.getUser1Id())
                .param("liked", match.getUser2Id())
                .param("status", match.getStatus().name())
                .update();
    }

    public Match getMatchById(String id) {
        return jdbcClient.sql("SELECT * FROM MATCHES WHERE id=:id")
                .param("id", id)
                .query((rs,rownum)->mapMatch(rs)).stream().findFirst()
                .orElseThrow(() -> new NotFoundException("Match not found"));
    }

    public Match findPendingMatch(String likerId, String likedId) {
        return jdbcClient.sql("SELECT * FROM MATCHES WHERE liker_id=:l2 AND liked_id=:l1 AND status='PENDING'")
                .param("l2", likedId)
                .param("l1", likerId)
                .query((rs,rownum)-> mapMatch(rs))
                .stream().findFirst().orElse(null);
    }

    public void updateMatch(Match match) {
        int rows = jdbcClient.sql("UPDATE MATCHES SET status=:status WHERE id=:id")
                .param("status", match.getStatus().name())
                .param("id", match.getId()).update();
        if (rows==0) throw new NotFoundException("Match not found");
    }


    public List<Match> getMatchesByUser(String userId) {
        return jdbcClient.sql("SELECT * FROM MATCHES WHERE (liker_id=:id OR liked_id=:id) AND status='MATCHED'")
                .param("id", userId)
                .query((rs,rownum)-> mapMatch(rs)).list();
    }
}
