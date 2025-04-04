package hw.diploma.management.repositories;

import hw.diploma.management.models.VisitorClipCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorClipCardRepository extends JpaRepository<VisitorClipCard, Long> {

}
