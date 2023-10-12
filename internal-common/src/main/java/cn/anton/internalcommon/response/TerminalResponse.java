package cn.anton.internalcommon.response;

import lombok.Data;
import lombok.ToString;

/*
 * @author: Anton
 * @create_date: 2023/10/5 00:00
 */
@Data
@ToString
public class TerminalResponse {
	
	private String carId;
	private String tid;
	private String latitude;
	private String longitude;
	
}
