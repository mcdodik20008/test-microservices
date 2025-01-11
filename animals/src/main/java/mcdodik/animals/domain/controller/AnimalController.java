package mcdodik.animals.domain.controller;

import lombok.RequiredArgsConstructor;
import mcdodik.animals.domain.model.Animal;
import mcdodik.animals.domain.service.AnimalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    @GetMapping
    public Map<String, String> getAppName(){
        return Map.of("name", appName, "kafkaBootstrapServers", kafkaBootstrapServers);
    }

    @PostMapping(value = {"/create", "/"})
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        try {
            return ResponseEntity.ok(animalService.createAnimal(animal));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
