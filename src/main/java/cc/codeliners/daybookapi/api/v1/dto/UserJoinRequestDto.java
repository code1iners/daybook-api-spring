package cc.codeliners.daybookapi.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Data
public class UserJoinRequestDto {

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 영문, 숫자, 특수문자를 최소 1개 이상 포함한 8~20자 비밀번호여야 합니다. ")
    private String password;
    @NotBlank(message = "이름은 필수값입니다.")
    private String name;
    private Timestamp birthday;
}
