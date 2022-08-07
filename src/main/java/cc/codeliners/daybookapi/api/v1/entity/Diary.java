package cc.codeliners.daybookapi.api.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "diary")
public class Diary {
    @Id
    @Column(name = "diary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diaryId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "content")
    private String content;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "day")
    private String day;

    @Column(name = "register_date")
    private Timestamp registerDate;



}
