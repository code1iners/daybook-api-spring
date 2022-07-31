package cc.codeliners.daybookapi.controller;

import cc.codeliners.daybookapi.common.ApiResponse;
import cc.codeliners.daybookapi.dto.UserJoinRequestDto;
import cc.codeliners.daybookapi.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("")
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/main")
    public ApiResponse chseckEmail(@RequestParam @Email String email){
        return userService.checkEmail(email);
    }

    @PostMapping("/join")
    public ApiResponse join(@RequestBody @Valid UserJoinRequestDto userJoinRequestDto){
        return userService.join(userJoinRequestDto);
    }
}
