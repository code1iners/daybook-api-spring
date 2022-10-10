package cc.codeliners.daybookapi.api.v1.dto;

import lombok.Builder;

@Builder
public class UserLoginResponseDto {
    public String email;
    public String token;

}
