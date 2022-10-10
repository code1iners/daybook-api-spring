package cc.codeliners.daybookapi.api.v1.controller;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.dto.DiaryCreateRequestDto;
import cc.codeliners.daybookapi.api.v1.service.DiaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/diarie") //TODO 이거 마지막에 s 붙혀야댐
    public ApiResponse diaryMain(String year, String month){
        return diaryService.diaryMain(year,month);
    }

    @GetMapping("/diaries/retrieve")
    public ApiResponse getDiaryList(@RequestParam String year, @RequestParam String month, @RequestParam String day){
        return diaryService.findDiaryByDate(year,month,day);
    }

    @PostMapping("/diaries")
    public ApiResponse createDiary(@RequestBody DiaryCreateRequestDto diaryCreateRequestDto){
        return diaryService.createDiary(diaryCreateRequestDto);
    }
}
