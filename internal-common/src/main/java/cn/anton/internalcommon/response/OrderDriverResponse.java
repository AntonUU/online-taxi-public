package cn.anton.internalcommon.response;

import lombok.Data;

/*
 * @author: Anton
 * @create_date: 2023/10/12 15:21
 */
@Data
public class OrderDriverResponse {
	
	private Long driverId;
	private Long carId;
	private String driverPhone;
	private String vehicleOn;
	private String licenseId;
	
}
