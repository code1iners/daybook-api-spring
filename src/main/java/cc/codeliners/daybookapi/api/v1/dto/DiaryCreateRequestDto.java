package cc.codeliners.daybookapi.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiaryCreateRequestDto {
    public String year;
    public String month;
    public String day;
    public String content;
}
