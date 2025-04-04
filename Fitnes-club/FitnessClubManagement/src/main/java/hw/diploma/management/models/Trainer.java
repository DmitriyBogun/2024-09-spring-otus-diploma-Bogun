package hw.diploma.management.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String fullName;

    private int age;

    private int coachingExperience;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    private List<Visitor> visitors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workouts_catalogue",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<Workout> competence;
}