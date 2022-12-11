package cc.codeliners.daybookapi.api.v1.controller;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*", allowedHeaders = "*") >> 추측상 이제 컨트롤러 단에서는 필요 없을 것 같아서 일단은 주석(필터단에서 처리했음)
public class MemberController {

    public final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/me")
    public ApiResponse memberDetail(@RequestHeader("token") String token){
        return new ApiResponse(200,memberService.getMamberDetail(token));
    }

}
