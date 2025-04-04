package hw.diploma.management.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class VisitorClipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="visitor_id", nullable=false)
    private Visitor visitor;

    private String trainerName;

    private String workoutName;

    private int numberOfWorkouts;
}
