package dev.cesar.RecipeHubAI.Service;

import dev.cesar.RecipeHubAI.Model.FoodItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    private final String apiKey = System.getenv("API_KEY");

    public Mono<String> generateRecipe(List<FoodItem> foodItems) {

        String ingredients = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantity: %d, Expiration date: %s",
                        item.getName(), item.getFoodCategory(), item.getQuantity(), item.getExpirationDate()))
                .collect(Collectors.joining("\n"));

        String prompt = "Based on my data base, return a recipe with the following ingredients:\n" + ingredients;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-goog-api-key", apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        var content = (Map<String, Object>) candidates.get(0).get("content");
                        var parts = (List<Map<String, Object>>) content.get("parts");
                        if (parts != null && !parts.isEmpty()) {
                            return parts.get(0).get("text").toString();
                        }
                    }
                    return "No recipe generated.";
                });

    }

}
