package mcdodik.animals.domain.repository;

import mcdodik.animals.domain.model.AnimalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalEventRepository extends JpaRepository<AnimalEvent, Long> {
}
