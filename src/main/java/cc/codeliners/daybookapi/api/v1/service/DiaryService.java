package cc.codeliners.daybookapi.api.v1.service;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.dto.DiaryCreateRequestDto;
import cc.codeliners.daybookapi.api.v1.entity.Diary;
import cc.codeliners.daybookapi.api.v1.repository.DiaryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public ApiResponse diaryMain(){
        Map result  = new HashMap<String, Object>();

        result.put("year",2022);
        result.put("month",8);
        List diary = new ArrayList();

        for (int i=1;i<32;i++){
            Map data = new HashMap<String, Integer>();

            data.put("day",i);
            data.put("count",(int)((Math.random()*10000)%10));

            diary.add(data);

        }

        result.put("diaries",diary);
        return new ApiResponse(200,result);

    }

    public ApiResponse createDiary(DiaryCreateRequestDto diaryCreateRequestDto){

        String dateTime = diaryCreateRequestDto.getYear()+"-"+diaryCreateRequestDto.getMonth()+"-"+diaryCreateRequestDto.getDay()+" 00:00:00";
        Diary diary = Diary.builder()
                .registerDate(Timestamp.valueOf(dateTime))
                .content(diaryCreateRequestDto.getContent())
                .build();

        diaryRepository.save(diary);

        return new ApiResponse(200,"일기 작성이 완료되었습니다.");
    }

}
