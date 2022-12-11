package cc.codeliners.daybookapi.api.v1.service;

import cc.codeliners.daybookapi.api.v1.common.ApiResponse;
import cc.codeliners.daybookapi.api.v1.common.CustomException;
import cc.codeliners.daybookapi.api.v1.dto.UserJoinRequestDto;
import cc.codeliners.daybookapi.api.v1.dto.UserLoginRequestDto;
import cc.codeliners.daybookapi.api.v1.dto.UserLoginResponseDto;
import cc.codeliners.daybookapi.api.v1.entity.User;
import cc.codeliners.daybookapi.api.v1.repository.UserRepository;
import cc.codeliners.daybookapi.api.v1.util.JwtTokenProvider;
import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public boolean checkEmail(String email){
        boolean result = true;

        if (!userRepository.findByEmail(email).isEmpty()){
            result = false;
        }
        return result;
    }

    public ApiResponse join(UserJoinRequestDto userJoinRequestDto){
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());

        if (!checkEmail(userJoinRequestDto.getEmail())){
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        User user = User.builder()
                .email(userJoinRequestDto.getEmail())
                .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                .name(userJoinRequestDto.getName())
                .roles(Collections.singletonList("ROLE_USER"))
                .birthday(userJoinRequestDto.getBirthday())
                .registerDate(now)
                .build();
        userRepository.save(user);
        return new ApiResponse(200,"회원가입이 완료되었습니다.");

    }

    public ApiResponse deleteUser(String token){
        String userId = jwtTokenProvider.getUserIdByToken(token);
        String email = jwtTokenProvider.getUserEmailFromToken(token);
        if(checkEmail(email)) {
            throw new CustomException(0, "존재하지 않는 회원입니다.");
        }
        userRepository.deleteByEmail(email);
        return new ApiResponse(200, email + " 회원의 탈퇴가 완료되었습니다.");
    }

    public ApiResponse login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(0, "가입되어 있지 않은 회원입니다."));
        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())){
            throw new CustomException(0, "잘못된 회원정보 입니다.");
        }
        String token = jwtTokenProvider.createToken(user.getUserId(), userLoginRequestDto.getEmail());

        return new ApiResponse(200,"로그인 완료",UserLoginResponseDto
                .builder()
                .email(userLoginRequestDto.getEmail())
                .token(token).build()
        );
    }
}
