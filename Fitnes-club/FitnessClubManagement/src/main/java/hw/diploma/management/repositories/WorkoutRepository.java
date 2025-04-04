package hw.diploma.management.repositories;

import hw.diploma.management.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Optional<Workout> findByWorkoutName(String name);
}
