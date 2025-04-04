package hw.diploma.management.repositories;

import hw.diploma.management.models.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository <Visitor, Long> {

    Optional<Visitor> findByFullName(String fullName);
}
