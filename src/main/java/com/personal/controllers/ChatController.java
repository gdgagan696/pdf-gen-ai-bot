package com.personal.controllers;

import com.personal.service.ChatService;
import com.personal.service.SearchService;
import dev.langchain4j.model.output.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<String> ask(
            @RequestBody String request) {

        String answer =
                chatService.ask(request);

        return ResponseEntity.ok(answer);
    }
}
