package cn.anton.apipassenger.service;

/*
 * @author: Anton
 * @create_date: 2023/9/5 15:00
 */
public interface VerificationCodeService {

	String generateCode(String passengerPhon);
	
}
