package cn.anton.apipassenger.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/*
 * @author: Anton
 * @create_date: 2023/9/5 14:44
 */
@Data
public class VerificationCodeDTO {
	
	String passengerPhone;
	
}
