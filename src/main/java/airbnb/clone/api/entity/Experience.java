package airbnb.clone.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String feedback;
    @OneToMany(mappedBy = "experience")
    private List<ExperienceImage> images;
    @OneToOne(mappedBy = "experience")
    private Booking booking;
}

