package dev.cesar.RecipeHubAI.Repository;

import dev.cesar.RecipeHubAI.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
