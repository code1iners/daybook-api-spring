package cc.codeliners.daybookapi.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String message;
    private Object data;

    public ApiResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, Object data){
        this.code = code;
        this.data = data;
    }
}
