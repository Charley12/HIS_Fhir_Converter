package com.fhir.mapping.service;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2QuantizedEmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAGService {

    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public RAGService(
            @Value("${qdrant.host}") String qdrantHost,
            @Value("${qdrant.port}") int qdrantPort) {

        this.embeddingStore = QdrantEmbeddingStore.builder()
                .host(qdrantHost)
                .port(qdrantPort)
                .collectionName("fhir_profiles")
                .build();

        // Fallback to local ONNX model for embeddings if cloud is not active
        this.embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();
    }

    public String retrieveContext(String query) {
        try {
            List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(embeddingModel.embed(query).content(), 3);
            if (relevant.isEmpty()) {
                return "No specific FHIR IG context found. Relying on baseline knowledge.";
            }
            return relevant.stream()
                    .map(match -> match.embedded().text())
                    .collect(Collectors.joining("\n---\n"));
        } catch (Exception e) {
            return "Simulated Context (Database connection failed): " + query + "\n- Standard: HL7 FHIR R4\n- Profile: TW Core IG";
        }
    }
}