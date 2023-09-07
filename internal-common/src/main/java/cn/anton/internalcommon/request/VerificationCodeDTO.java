package cn.anton.internalcommon.request;

import lombok.Data;

/*
 * @author: Anton
 * @create_date: 2023/9/6 16:59
 */
@Data
public class VerificationCodeDTO {
 
    private String passengerPhone;
    private String verificationCode;

}
