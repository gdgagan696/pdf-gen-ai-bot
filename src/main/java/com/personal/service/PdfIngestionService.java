package com.personal.service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;


@Service
public class PdfIngestionService {

    private final EmbeddingModel embeddingModel;
    private final ChromaEmbeddingStore embeddingStore;
    private final DocumentService documentService;


    public PdfIngestionService(EmbeddingModel embeddingModel, ChromaEmbeddingStore embeddingStore, DocumentService documentService) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
        this.documentService = documentService;
    }

    public void ingest(MultipartFile file) throws Exception {


        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            List<String> chunks = split(text);

            for (String chunk : chunks) {

                documentService.save(chunk);
            }
        }
    }

    private List<String> split(String text) {

        int chunkSize = 1000;

        List<String> chunks = new ArrayList<>();

        for (int i = 0; i < text.length(); i += chunkSize) {

            chunks.add(
                    text.substring(
                            i,
                            Math.min(i + chunkSize, text.length())
                    )
            );
        }

        return chunks;
    }
}
