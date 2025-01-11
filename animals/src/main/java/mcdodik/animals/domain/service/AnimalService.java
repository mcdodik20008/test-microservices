package mcdodik.animals.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mcdodik.animals.domain.model.Animal;
import mcdodik.animals.domain.model.AnimalEvent;
import mcdodik.animals.domain.repository.AnimalEventRepository;
import mcdodik.animals.domain.repository.AnimalRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalEventRepository animalEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public Animal createAnimal(Animal animal) throws Exception {
        Animal savedAnimal = animalRepository.save(animal);
        publishEvent("AnimalCreated", savedAnimal);
        return savedAnimal;
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    private void publishEvent(String type, Animal animal) throws Exception {
        AnimalEvent event = new AnimalEvent();
        event.setType(type);
        event.setData(objectMapper.writeValueAsString(animal));
        event.setTimestamp(LocalDateTime.now());
        animalEventRepository.save(event);
        kafkaTemplate.send("animal-events", objectMapper.writeValueAsString(event));
    }
}
