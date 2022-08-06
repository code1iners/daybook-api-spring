package cc.codeliners.daybookapi.service;

import cc.codeliners.daybookapi.common.ApiResponse;
import cc.codeliners.daybookapi.dto.UserJoinRequestDto;
import cc.codeliners.daybookapi.entity.User;
import cc.codeliners.daybookapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse checkEmail(String userEmail){
        System.out.println(Optional.of(userRepository.findByUserEmail(userEmail)).isPresent());
        return new ApiResponse(200,Optional.of(userRepository.findByUserEmail(userEmail)).isPresent());

    }

    public ApiResponse join(UserJoinRequestDto userJoinRequestDto){
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());

        User user = User.builder()
                .userEmail(userJoinRequestDto.getUserEmail())
                .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                .userName(userJoinRequestDto.getUserName())
                .birthday(userJoinRequestDto.getBirthday())
                .registerDate(now)
                .build();
        userRepository.save(user);
        return new ApiResponse(200,"회원가입이 완료되었습니다.");

    }
}
