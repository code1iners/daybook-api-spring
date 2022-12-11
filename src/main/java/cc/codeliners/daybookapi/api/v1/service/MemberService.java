package cc.codeliners.daybookapi.api.v1.service;

import cc.codeliners.daybookapi.api.v1.common.CustomException;
import cc.codeliners.daybookapi.api.v1.dto.MemberDetailRequestDto;
import cc.codeliners.daybookapi.api.v1.entity.User;
import cc.codeliners.daybookapi.api.v1.repository.UserRepository;
import cc.codeliners.daybookapi.api.v1.util.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class MemberService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public MemberDetailRequestDto getMamberDetail(String token){
        int userId = jwtTokenProvider.getUserIdByToken(token);
        User user = userRepository.findByUserId(userId).orElseThrow(()->new CustomException(0, "존재하지 않는 회원입니다."));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return MemberDetailRequestDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .bitrhday(dateFormat.format(user.getBirthday()))
                .build();
    }

}
