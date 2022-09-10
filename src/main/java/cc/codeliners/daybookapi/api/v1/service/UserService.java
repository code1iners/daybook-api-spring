package cc.codeliners.daybookapi.api.v1.service;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.dto.UserJoinRequestDto;
import cc.codeliners.daybookapi.api.v1.entity.Member;
import cc.codeliners.daybookapi.api.v1.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse checkEmail(String email){
        boolean result = true;

        if (memberRepository.findByEmail(email)!=null){
            result = false;
        }
        return new ApiResponse(200, result);
    }

    public ApiResponse join(UserJoinRequestDto userJoinRequestDto){
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());


        Member member = Member.builder()
                .email(userJoinRequestDto.getUserEmail())
                .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                .name(userJoinRequestDto.getUserName())
                .birthday(userJoinRequestDto.getBirthday())
                .registerDate(now)
                .build();
        memberRepository.save(member);
        return new ApiResponse(200,"회원가입이 완료되었습니다.");
    }

    public ApiResponse deleteUser(String email){
        memberRepository.deleteByEmail(email);
        return new ApiResponse(200,email+" 회원의 탈퇴가 완료되었습니다.");
    }
}
