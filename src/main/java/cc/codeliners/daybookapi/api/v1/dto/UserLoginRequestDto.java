package cc.codeliners.daybookapi.api.v1.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserLoginRequestDto {
    @Email
    public String email;
    public String password;
}
