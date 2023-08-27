package airbnb.clone.api.repository;

import airbnb.clone.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
