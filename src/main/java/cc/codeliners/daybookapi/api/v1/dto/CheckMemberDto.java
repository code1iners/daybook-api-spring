package cc.codeliners.daybookapi.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckMemberDto {
    @Email
    private String email;
}
