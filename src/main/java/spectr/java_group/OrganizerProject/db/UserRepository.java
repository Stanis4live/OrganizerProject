package spectr.java_group.OrganizerProject.db;

import org.springframework.data.jpa.repository.JpaRepository;
import spectr.java_group.OrganizerProject.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
