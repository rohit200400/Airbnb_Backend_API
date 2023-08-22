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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToMany
    @JoinTable(
            name = "booking_rooms",
            joinColumns = @JoinColumn(name = "bookingId"),
            inverseJoinColumns = @JoinColumn(name = "roomId")
    )
    private List<Rooms> rooms;
    private Date checkinTime;
    private Date checkoutTime;
    private Integer numberOfGuest;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Experience experience;

}
