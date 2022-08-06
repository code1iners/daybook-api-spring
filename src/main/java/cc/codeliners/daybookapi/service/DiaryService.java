package cc.codeliners.daybookapi.service;

import cc.codeliners.daybookapi.common.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiaryService {

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

}
