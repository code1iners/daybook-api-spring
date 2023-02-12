package cc.codeliners.daybookapi.api.v1.service;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.common.CustomException;
import cc.codeliners.daybookapi.api.v1.dto.DiaryCreateRequestDto;
import cc.codeliners.daybookapi.api.v1.entity.Diary;
import cc.codeliners.daybookapi.api.v1.repository.DiaryRepository;
import cc.codeliners.daybookapi.api.v1.util.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Log4j2
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public DiaryService(DiaryRepository diaryRepository, JwtTokenProvider jwtTokenProvider) {
        this.diaryRepository = diaryRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ApiResponse diaryMain(String year, String month){
        Map result  = new HashMap<String, Object>();

        result.put("year",year);
        result.put("month",month);
        List diary = new ArrayList();

        for (int i=1;i<32;i++){
            Map data = new HashMap<String, Integer>();

            String day;
            if(i<10){
                day = "0"+i;
            }
            else {
                day = String.valueOf(i);
            }
            data.put("day",i);
            data.put("count",diaryRepository.countByYearAndMonthAndDay(year, month, day));

            diary.add(data);

        }

        result.put("diaries",diary);
        return new ApiResponse(200,result);
    }

    public ApiResponse findDiaryByDate(String year, String month, String day){
        List<Diary> diaries = diaryRepository.findDiaryByDate(year, month, day);

        if (diaries.isEmpty()||diaries.size()==0){

        }

        return new ApiResponse(200,"조회", diaries);
    }

    public ApiResponse createDiary(DiaryCreateRequestDto diaryCreateRequestDto){
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
//        jwtTokenProvider.getUserEmailFromToken();

        Diary diary = Diary.builder()
                .registerDate(now)
                .year(diaryCreateRequestDto.getYear())
                .month(diaryCreateRequestDto.getMonth())
                .day(diaryCreateRequestDto.getDay())
                .content(diaryCreateRequestDto.getContent())
                .build();

        diaryRepository.save(diary);

        return new ApiResponse(200,"일기 작성이 완료되었습니다.");
    }

    public ApiResponse deleteDiary(int diaryId) {
        try {
            Diary deleteDiary = diaryRepository.findByDiaryId(diaryId);

            diaryRepository.delete(deleteDiary);

            return new ApiResponse(200,diaryId + "번 일기 삭제가 완료되었습니다.");

        } catch (RuntimeException e){
            throw new CustomException(0, "존재하지 않는 일기입니다.");
        }
    }

}
