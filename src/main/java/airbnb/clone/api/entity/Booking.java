package airbnb.clone.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK will be created in this table
    @ManyToOne
    private Users user;

    @ManyToMany
    private List<Rooms> rooms;

    private Date checkinTime;
    private Date checkoutTime;
    private Integer numberOfGuest;

    @OneToOne(cascade = CascadeType.ALL)
    private Experience experience;

}
