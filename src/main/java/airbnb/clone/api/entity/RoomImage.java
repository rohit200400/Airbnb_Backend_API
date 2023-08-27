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
@Table(name = "RoomImage")
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image;

    // FK will be created in this table
    @ManyToOne
    @JoinColumn(name = "room_id") // name of the column
    private Rooms room;
}
