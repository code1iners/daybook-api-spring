package cc.codeliners.daybookapi.api.v1.service;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.common.CustomException;
import cc.codeliners.daybookapi.api.v1.dto.UserJoinRequestDto;
import cc.codeliners.daybookapi.api.v1.entity.Member;
import cc.codeliners.daybookapi.api.v1.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkEmail(String email){
        boolean result = true;

        if (memberRepository.findByEmail(email)!=null){
            result = false;
        }
        return result;
    }

    public ApiResponse join(UserJoinRequestDto userJoinRequestDto){
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());

        if (!checkEmail(userJoinRequestDto.getEmail())){
            throw new CustomException(0,"이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .email(userJoinRequestDto.getEmail())
                .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                .name(userJoinRequestDto.getName())
                .birthday(userJoinRequestDto.getBirthday())
                .registerDate(now)
                .build();
        memberRepository.save(member);
        return new ApiResponse(200,"회원가입이 완료되었습니다.");
    }

    public ApiResponse deleteUser(String email){
        if(checkEmail(email)) {
            throw new CustomException(0, "존재하지 않는 회원입니다.");
        }
        memberRepository.deleteByEmail(email);
        return new ApiResponse(200, email + " 회원의 탈퇴가 완료되었습니다.");
    }
}
