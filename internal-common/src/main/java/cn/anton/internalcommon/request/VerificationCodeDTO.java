package cn.anton.internalcommon.request;

import lombok.Data;
import lombok.ToString;

/*
 * @author: Anton
 * @create_date: 2023/9/6 16:59
 */
@Data
@ToString
public class VerificationCodeDTO {
 
    private String passengerPhone;
    private String verificationCode;
    private String driverPhone;

}
