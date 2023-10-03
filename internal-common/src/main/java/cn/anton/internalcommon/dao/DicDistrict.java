package cn.anton.internalcommon.dao;

import lombok.Data;

/*
 * @author: Anton
 * @create_date: 2023/9/25 11:11
 */
@Data
public class DicDistrict {

 private String addressCode;
 private String addressName;
 private String parentAddressCode;
 private Integer level;

}
