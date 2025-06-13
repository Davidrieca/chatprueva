package com.example.tecnoMeet.persistence;

import com.example.tecnoMeet.domain.User;
import com.example.tecnoMeet.utilities.NotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;

@Repository
@Profile("db-h2")
public class UserRepository  {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private User mapStudent(ResultSet rs) throws SQLException {
        User s = new User();
        s.setId(rs.getString("id"));
        s.setDeleted(rs.getBoolean("deleted"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setBio(rs.getString("bio"));
        s.setPhoto(rs.getString("photo"));
        s.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return s;
    }


    public void addStudent(User user) {

        var params = Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "bio", user.getBio(),
                "photo", user.getPhoto(),
                "createdAt", Timestamp.valueOf(user.getCreatedAt()),
                "deleted", user.isDeleted()
        );
        jdbcClient.sql("INSERT INTO USERS (id,name,email,bio,photo,created_at,deleted) VALUES (:id,:name,:email,:bio,:photo,:createdAt,:deleted)")
                .params(params).update();
    }


    public User getStudentById(String id) {
        return jdbcClient.sql("SELECT * FROM USERS WHERE id = :id")
                .param("id", id)
                .query((rs,rownum)->mapStudent(rs))
                .stream().findFirst()
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }


    public List<User> getAllStudents() {
        return jdbcClient.sql("SELECT * FROM USERS WHERE deleted = FALSE")
                .query((rs,rownum)->mapStudent(rs)).list();
    }


    public void updateStudent(User user) {
        int rows = jdbcClient.sql("UPDATE USERS SET name=:name,email=:email,bio=:bio,photo=:photo,deleted=:deleted WHERE id=:id")
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("bio", user.getBio())
                .param("photo", user.getPhoto())
                .param("deleted", user.isDeleted())
                .param("id", user.getId())
                .update();
        if (rows == 0) throw new NotFoundException("Student not found");
    }


    public void removeAll() {
        jdbcClient.sql("DELETE FROM STUDENTS").update();
    }
}
