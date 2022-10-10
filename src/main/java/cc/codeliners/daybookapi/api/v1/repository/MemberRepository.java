package cc.codeliners.daybookapi.api.v1.repository;

import cc.codeliners.daybookapi.api.v1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);
    void deleteByEmail(String email);

}
