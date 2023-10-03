package cn.anton.internalcommon.dao;

import lombok.Data;

/*
 * @author: Anton
 * @create_date: 2023/10/2 12:22
 */
@Data
public class DriverPhoneExists {
	
	private String driverPhone;
	private Integer ifExists;
	
	public DriverPhoneExists() {
	
	}
	
	public DriverPhoneExists(String driverPhone, Integer ifExists) {
		this.driverPhone = driverPhone;
		this.ifExists = ifExists;
	}
}
