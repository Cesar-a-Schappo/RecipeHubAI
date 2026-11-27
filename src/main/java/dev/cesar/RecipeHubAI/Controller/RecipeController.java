package dev.cesar.RecipeHubAI.Controller;

import dev.cesar.RecipeHubAI.Model.FoodItem;
import dev.cesar.RecipeHubAI.Service.FoodItemService;
import dev.cesar.RecipeHubAI.Service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private FoodItemService service;
    private final GeminiService geminiService;

    public RecipeController(GeminiService geminiService, FoodItemService service) {
        this.geminiService = geminiService;
        this.service = service;
    }

    public RecipeController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItem> foodItems = service.listAll();
        return geminiService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
