package dev.cesar.RecipeHubAI.Controller;

import dev.cesar.RecipeHubAI.Model.FoodItem;
import dev.cesar.RecipeHubAI.Service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FoodItem foodItem) {
        FoodItem created = service.save(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Item created with ID: " + created.getId());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<String > read(@PathVariable Long id) {
        if (service.idExists(id)) {
            service.listById(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Item of ID: " + id + " listed.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item of ID: " + id + " not found`.");
        }
    }

    @GetMapping("/read")
    public ResponseEntity<List<FoodItem>> readAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        if (service.idExists(id)) {
            service.update(id, foodItem);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Item of ID: " + id + " updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item of ID: " + id + " not found`.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (service.idExists(id)) {
            service.delete(id);
            return ResponseEntity.ok("Item of ID: " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item of ID: " + id + " not found.");
        }
    }


}
