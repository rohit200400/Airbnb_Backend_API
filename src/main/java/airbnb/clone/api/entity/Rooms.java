package airbnb.clone.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Address address;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> images;

    private Double rate;
    private Integer numberOfBedroom;
    private Integer numberOfBathroom;
    private String propertyType;
    //house//flat//guest house//hotel

    @ManyToMany
    @JoinTable(
            name = "rooms_amenities",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "amenityId")
    )
    private List<Amenities> amenities; //    Wi-Fi, washing machine, AC, kitchen, etc

    private Time checkinTime;
    private Time checkoutTime;
    private Integer capacity;
}
