package cc.codeliners.daybookapi.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiaryCreateRequestDto {
    public int year;
    public int month;
    public int day;
    public String content;
}
