package cn.anton.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author: Anton
 * @create_date: 2023/9/6 17:11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponse {
	
	private String accessToken;
	private String refreshToken;

}
