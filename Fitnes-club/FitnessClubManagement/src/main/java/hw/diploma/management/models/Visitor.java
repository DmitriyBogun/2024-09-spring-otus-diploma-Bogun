package hw.diploma.management.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "visitors")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private int age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitor", fetch = FetchType.EAGER)
    private List<VisitorClipCard> cards;
}
