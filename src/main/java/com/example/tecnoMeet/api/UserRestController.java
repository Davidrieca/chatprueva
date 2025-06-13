package com.example.tecnoMeet.api;

import com.example.tecnoMeet.aplication.DTO.UserDTO;
import com.example.tecnoMeet.aplication.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // ← esto es lo que el test espera
public class UserRestController {
    private final UserService service;

    public UserRestController(UserService service ) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody UserDTO dto) {
        return service.register(dto);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable String id, @RequestBody UserDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return service.getAll();
    }
}

