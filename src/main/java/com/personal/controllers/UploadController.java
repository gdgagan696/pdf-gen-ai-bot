package com.personal.controllers;

import com.personal.service.PdfIngestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class UploadController {

    private final PdfIngestionService pdfIngestionService;

    public UploadController(PdfIngestionService pdfIngestionService) {
        this.pdfIngestionService = pdfIngestionService;
    }

    @PostMapping("/upload")
    public String upload(
            @RequestParam("file")
            MultipartFile file) throws Exception {

        pdfIngestionService.ingest(file);

        return "PDF processed successfully";
    }
}