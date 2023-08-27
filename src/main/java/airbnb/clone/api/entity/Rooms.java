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
@Table(name = "Rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // foreign key will be created in this table
    @OneToOne
    private Address address;

    // this is mapping with the FK in the roomImage table... FK won't be created in this table
    @OneToMany(mappedBy = "room") // didn't cascade intentionally
    private List<RoomImage> images;

    private Double rate;
    private Integer numberOfBedroom;
    private Integer numberOfBathroom;
    private String propertyType;
    //house//flat//guest house//hotel

    // when we use mappedBy it uses the mapping created by the other table
    // in this case only one table will be created because of ManyToMany used in Amenities
    @ManyToMany(mappedBy = "rooms", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Amenities> amenities; //Wi-Fi, washing machine, AC, kitchen, etc

    private Time checkinTime;
    private Time checkoutTime;
    private Integer capacity;
}
