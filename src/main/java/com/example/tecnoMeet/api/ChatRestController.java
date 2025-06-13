package com.example.tecnoMeet.api;

import com.example.tecnoMeet.aplication.ChatService;
import com.example.tecnoMeet.aplication.DTO.ChatMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatRestController {

    private final ChatService chatService;

    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    // POST /chats/{matchId}?senderId=xxx&content=yyy
    @PostMapping("/{matchId}")
    @ResponseStatus(HttpStatus.OK) // <- Cambiado de CREATED (201) a OK (200)
    public ChatMessageDTO sendMessage(
            @PathVariable String matchId,
            @RequestParam String senderId,
            @RequestParam String content) {
        return chatService.sendMessage(matchId, senderId, content);
    }

    // GET /chats/{matchId}
    @GetMapping("/{matchId}")
    public List<ChatMessageDTO> getMessagesByMatchId(@PathVariable String matchId) {
        return chatService.getMessages(matchId);
    }
}
