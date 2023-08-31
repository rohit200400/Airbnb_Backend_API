package airbnb.clone.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // foreign key will be created in this table

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    // this is mapping with the FK in the roomImage table... FK won't be created in this table
    @OneToMany(cascade ={ CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "rooms_id")
    private List<RoomImage> images;

    private Double rate;
    private Integer numberOfBedroom;
    private Integer numberOfBathroom;
    private String propertyType;//house//flat//guest house//hotel

    // when we use mappedBy it uses the mapping created by the other table
    // in this case only one table will be created because of ManyToMany used in Amenities
    //Wi-Fi, washing machine, AC, kitchen, etc
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Amenities> amenities;
    private Time checkinTime;
    private Time checkoutTime;
    private Integer capacity;
}
