package cc.codeliners.daybookapi.repository;

import cc.codeliners.daybookapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserEmail(String userEmail);
}
