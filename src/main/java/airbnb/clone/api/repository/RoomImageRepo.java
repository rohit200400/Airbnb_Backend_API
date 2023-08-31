package airbnb.clone.api.repository;

import airbnb.clone.api.entity.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomImageRepo extends JpaRepository<RoomImage, Long> {
}
