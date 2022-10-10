package cc.codeliners.daybookapi.api.v1.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomException extends RuntimeException{
    int status;
    String message;


}
