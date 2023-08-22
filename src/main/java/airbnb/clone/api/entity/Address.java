package airbnb.clone.api.entity;

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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String Country;
    private String zipCode;

    // this will use the FK in the rooms table
    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
    private Rooms rooms;
}
