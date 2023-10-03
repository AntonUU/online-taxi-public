package cn.anton.internalcommon.dao;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * @author: Anton
 * @create_date: 2023/9/29 07:05
 */
@Data
@ToString
public class DriverUser {
	
	private Long id;
	/**
	 * 注册地行政区域代码
	 */
	private String address;
	private String driverName;
	private String driverPhone;
	private Integer driverGender;
	/**
	 * YYYY-MM-DD
	 */
	private LocalDate driverBirthday;
	/**
	 * 民族代码
	 */
	private String driverNation;
	/**
	 * 驾驶员通信地址
	 */
	private String driverContactAddress;
	/**
	 * 机动车驾驶证号
	 */
	private String licensePhotoId;
	/**
	 * 初次领取驾驶证日期 YYYY-MM-DD
	 */
	private LocalDate getDriverLicenseDate;
	/**
	 * 驾驶证有效期限起
	 */
	private LocalDate driverLicenseOn;
	/**
	 * 驾驶证有效期限止
	 */
	private LocalDate driverLicenseOff;
	/**
	 * 是否巡游出租汽车驾驶员 0否 1是
	 */
	private Integer taxiDriver;
	/**
	 * 网络预约出租汽车驾驶员资格证号
	 */
	private String certificateNo;
	/**
	 * 网络预约出租汽车驾驶员证发证机构全称
	 */
	private String networkCarIssueOrganization;
	/**
	 * 资格证发证日期
	 */
	private LocalDate networkCarIssueDate;
	/**
	 * 初次领取资格证日期
	 */
	private LocalDate getNetworkCarProofDate;
	/**
	 * 资格证有效起始日期
	 */
	private LocalDate networkCarProofOn;
	/**
	 * 资格证有效截止日期
	 */
	private LocalDate networkCarProofOff;
	/**
	 * 驾驶员信息向服务所在地出租汽车行政主管部门报备日期 YYYY-MM-DD
	 */
	private LocalDate registerDate;
	/**
	 * 1:网络预约出租汽车 2.巡游出租汽车 3.私人小客车合乘
	 */
	private Integer commercialType;
	/**
	 * 驾驶员合同或协议签署公司全称
	 */
	private String contractCompany;
	/**
	 * 合同(或协议）有效期起
	 */
	private LocalDate contractOn;
	/**
	 * 合同(或协议）有效期止
	 */
	private LocalDate contractOff;
	/**
	 0有效 1失效
	 */
	private Boolean state;
	
	private LocalDateTime gmtModified;
	private LocalDateTime gmtCreate;
	
}
