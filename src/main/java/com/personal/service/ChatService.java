package com.personal.service;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final SearchService searchService;
    private final ChatModel chatModel;

    public ChatService(SearchService searchService, ChatModel chatModel) {
        this.searchService = searchService;
        this.chatModel = chatModel;
    }

    public String ask(String question) {

        List<String> contextChunks =
                searchService.search(question);

        String context =
                String.join("\n\n", contextChunks);

        String prompt =
                """
                You are a helpful assistant.

                Answer only using the provided context.

                If the answer is not present,
                say you could not find it.

                Context:
                %s

                Question:
                %s
                """
                        .formatted(context, question);

        return chatModel.chat(prompt);
    }
}