package com.fhir.mapping.controller;

import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.data.message.AiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class FHIRMappingController {

    private final StreamingChatLanguageModel streamingChatLanguageModel;

    @Autowired
    public FHIRMappingController(StreamingChatLanguageModel streamingChatLanguageModel) {
        this.streamingChatLanguageModel = streamingChatLanguageModel;
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestBody String message) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        streamingChatLanguageModel.generate(message, new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                sink.tryEmitNext(token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                sink.tryEmitComplete();
            }

            @Override
            public void onError(Throwable error) {
                sink.tryEmitError(error);
            }
        });

        return sink.asFlux();
    }
}
