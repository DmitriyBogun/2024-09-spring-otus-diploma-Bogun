package hw.diploma.management.repositories;

import hw.diploma.management.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByFullName(String fullName);

}
