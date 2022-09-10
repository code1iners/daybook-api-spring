package cc.codeliners.daybookapi.api.v1.controller;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.dto.CheckMemberDto;
import cc.codeliners.daybookapi.api.v1.service.UserService;
import cc.codeliners.daybookapi.api.v1.dto.UserJoinRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/v1")
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/check")
    public ApiResponse checkEmail(@RequestBody @Valid CheckMemberDto checkMemberDto){
        return userService.checkEmail(checkMemberDto.getEmail());
    }

    @PostMapping("/auth/join")
    public ApiResponse join(@RequestBody @Valid UserJoinRequestDto userJoinRequestDto){
        return userService.join(userJoinRequestDto);
    }
    @Transactional
    @DeleteMapping("/auth/{email}")
    public ApiResponse deleteUser(@PathVariable @Email String email){
        return userService.deleteUser(email);
    }
}
