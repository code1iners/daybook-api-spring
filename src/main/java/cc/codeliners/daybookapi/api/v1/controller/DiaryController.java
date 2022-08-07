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

    @GetMapping("/diaries")
    public ApiResponse diaryMain(int year, int month){

        return diaryService.diaryMain();

    }

    @PostMapping("/diaries")
    public ApiResponse createDiary(@RequestBody DiaryCreateRequestDto diaryCreateRequestDto){
        return diaryService.createDiary(diaryCreateRequestDto);
    }
}
