package cc.codeliners.daybookapi.api.v1.repository;

import cc.codeliners.daybookapi.api.v1.entity.Diary;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,String> {
    @Query("select OBJECT(d) from Diary d where d.year = :year and d.month = :month and d.day = :day")
    List<Diary> findDiaryByDate(@Param("year") String year, @Param("month") String month, @Param("day") String day);

    int countByYearAndMonthAndDay(String year, String month, String day);
 }
