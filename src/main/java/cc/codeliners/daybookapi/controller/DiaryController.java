package cc.codeliners.daybookapi.controller;

import cc.codeliners.daybookapi.common.ApiResponse;
import cc.codeliners.daybookapi.service.DiaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("")
    public ApiResponse diaryMain(){

        return diaryService.diaryMain();

    }
}
