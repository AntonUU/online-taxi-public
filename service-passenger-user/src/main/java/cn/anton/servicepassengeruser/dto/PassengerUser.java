package cn.anton.servicepassengeruser.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/*
 * @author: Anton
 * @create_date: 2023/9/7 14:25
 */
@Data
@TableName("passenger_user")
public class PassengerUser {
	
	@TableId(value = "id", type = IdType.AUTO)
	private Long id; // 乘客ID
	private String passengerPhone; // 乘客手机号
	private String passengerName; // 乘客昵称
	private boolean passenger_gender; // 乘客性别 1男 0女
	@TableLogic
	private boolean state;  // 乘客状态  0有效 1失效
	private LocalDateTime gmt_create; // 创建时间
	private LocalDateTime gmt_modified; // 更新时间
	
}
