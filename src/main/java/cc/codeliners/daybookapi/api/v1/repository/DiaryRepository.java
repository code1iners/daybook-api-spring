package cc.codeliners.daybookapi.api.v1.repository;

import cc.codeliners.daybookapi.api.v1.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,String> {
}
