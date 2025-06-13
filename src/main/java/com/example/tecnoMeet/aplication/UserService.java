package com.example.tecnoMeet.aplication;

import com.example.tecnoMeet.aplication.DTO.UserDTO;
import com.example.tecnoMeet.domain.User;
import com.example.tecnoMeet.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO register(UserDTO dto) {
        User s = new User(dto.name(), dto.email(), dto.bio(), dto.photoUrl());
        repository.addStudent(s);
        return toDTO(s);
    }

    public UserDTO update(String id, UserDTO dto) {
        User s = repository.getStudentById(id);
        s.setName(dto.name());
        s.setEmail(dto.email());
        s.setBio(dto.bio());
        s.setPhoto(dto.photoUrl());
        repository.updateStudent(s);
        return toDTO(s);
    }

    public UserDTO getById(String id) {
        return toDTO(repository.getStudentById(id));
    }

    public List<UserDTO> getAll() {
        return repository.getAllStudents().stream().map(this::toDTO).toList();
    }

    private UserDTO toDTO(User s) {
        return new UserDTO(s.getId(), s.getName(), s.getEmail(), s.getBio(), s.getPhoto());
    }
}
