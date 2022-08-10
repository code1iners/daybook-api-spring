package cc.codeliners.daybookapi.api.v1.repository;

import cc.codeliners.daybookapi.api.v1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserEmail(String userEmail);
}
