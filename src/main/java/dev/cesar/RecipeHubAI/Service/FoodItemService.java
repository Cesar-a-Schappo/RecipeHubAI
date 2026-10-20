package dev.cesar.RecipeHubAI.Service;

import dev.cesar.RecipeHubAI.Model.FoodItem;
import dev.cesar.RecipeHubAI.Repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem save(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    public List<FoodItem> listAll() {
        return repository.findAll();
    }

    public FoodItem listById(Long id) {
        Optional<FoodItem> foodItem = repository.findById(id);
        return foodItem.orElse(null);
    }

    public Boolean idExists(Long id) {
        return repository.existsById(id);
    }

    public void update(Long id, FoodItem foodItem) {
        foodItem.setId(id);
        repository.save(foodItem);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
