package airbnb.clone.api.repository;

import airbnb.clone.api.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepo extends JpaRepository<Rooms, Long> {
}
