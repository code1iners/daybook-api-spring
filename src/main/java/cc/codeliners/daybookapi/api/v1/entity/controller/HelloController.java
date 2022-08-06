package cc.codeliners.daybookapi.api.v1.entity.controller;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloController {

    @GetMapping("")
    public ApiResponse hello(){
        return new ApiResponse(200,"Hello!");
    }
}
