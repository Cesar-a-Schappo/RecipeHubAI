package dev.cesar.RecipeHubAI.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeminiService {

    private final WebClient webClient;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    private final String apiKey = System.getenv("API_KEY");

    public Mono<String> generateRecipe() {
        String prompt = "Create a 'Hello World!' in 5 different languages": //Placeholder
    }

}
