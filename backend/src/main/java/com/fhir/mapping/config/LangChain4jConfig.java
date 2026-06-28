package com.fhir.mapping.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;

@Configuration
public class LangChain4jConfig {

    // --- OLLAMA (Local) ---
    @Bean
    @Profile("local")
    public ChatLanguageModel ollamaChatModel(
            @Value("${ai.ollama.base-url}") String baseUrl,
            @Value("${ai.ollama.model-name}") String modelName) {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .timeout(Duration.ofMinutes(5))
                .build();
    }

    @Bean
    @Profile("local")
    public StreamingChatLanguageModel ollamaStreamingChatModel(
            @Value("${ai.ollama.base-url}") String baseUrl,
            @Value("${ai.ollama.model-name}") String modelName) {
        return OllamaStreamingChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .timeout(Duration.ofMinutes(5))
                .build();
    }

    // --- VERTEX AI (Cloud) ---
    @Bean
    @Profile("cloud")
    public ChatLanguageModel vertexAiChatModel(
            @Value("${ai.vertex-ai.project-id}") String projectId,
            @Value("${ai.vertex-ai.location}") String location,
            @Value("${ai.vertex-ai.model-name}") String modelName) {
        return VertexAiGeminiChatModel.builder()
                .project(projectId)
                .location(location)
                .modelName(modelName)
                .build();
    }

    @Bean
    @Profile("cloud")
    public StreamingChatLanguageModel vertexAiStreamingChatModel(
            @Value("${ai.vertex-ai.project-id}") String projectId,
            @Value("${ai.vertex-ai.location}") String location,
            @Value("${ai.vertex-ai.model-name}") String modelName) {
        return VertexAiGeminiStreamingChatModel.builder()
                .project(projectId)
                .location(location)
                .modelName(modelName)
                .build();
    }
}
