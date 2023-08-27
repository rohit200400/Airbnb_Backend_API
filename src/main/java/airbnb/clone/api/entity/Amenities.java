package airbnb.clone.api.entity;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Amenities")
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "RoomAmenities",
            joinColumns = @JoinColumn(name = "amenities_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Rooms> rooms;
}