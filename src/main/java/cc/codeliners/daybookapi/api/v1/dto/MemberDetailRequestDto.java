package cc.codeliners.daybookapi.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberDetailRequestDto {
    public String name;
    public String bitrhday;
    public String email;
}
