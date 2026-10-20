package dev.cesar.RecipeHubAI.Model;

import dev.cesar.RecipeHubAI.Enums.FoodCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_food_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    private FoodCategory foodCategory;
    private LocalDate expirationDate;

}
