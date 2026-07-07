package com.personal.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaApiVersion;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChromaConfig {

    @Bean
    EmbeddingStore<TextSegment> embeddingStore() {

        return ChromaEmbeddingStore.builder()
                .baseUrl("http://localhost:8000/")
                .tenantName("default_tenant")
                .databaseName("default_database")
                .collectionName("pdf-knowledge-base")
                .apiVersion(ChromaApiVersion.V2)
                .build();
    }
}
