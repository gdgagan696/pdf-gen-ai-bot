package com.personal;

import com.personal.service.DocumentService;
import com.personal.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class TestController {

    private final DocumentService documentService;
    private final SearchService searchService;

    public TestController(
            DocumentService documentService,
            SearchService searchService) {
        this.documentService = documentService;
        this.searchService = searchService;
    }

    @PostMapping
    public String add(@RequestBody String text) {
        documentService.save(text);
        return "stored";
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam String query) {
        return searchService.search(query);
    }
}
