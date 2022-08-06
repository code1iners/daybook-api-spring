package cc.codeliners.daybookapi.service;

import cc.codeliners.daybookapi.common.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DiaryService {

    public ApiResponse diaryMain(){
        Map result  = new HashMap<String, Object>();

        result.put("year",2022);
        result.put("month",8);
        Map data = new HashMap<Integer, Integer>();
        for (int i=1;i<32;i++){
            data.put(i, (int)((Math.random()*10000)%10));
        }

        result.put("diaries",data);
        return new ApiResponse(200,result);

    }

}
