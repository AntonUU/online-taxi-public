package cn.anton.internalcommon.dao;

import lombok.Data;
import lombok.ToString;

/*
 * @author: Anton
 * @create_date: 2023/9/24 15:37
 */
@Data
@ToString
public class PriceRule {
  
  /**
   * 行政区域代码
   */
  private String cityCode;
  /**
   * 车辆类型
   */
  private String vehicleType;
  /**
   * 起步价
   */
  private Double startFare;
  /**
   * 起步里程
   */
  private Integer startMile;
  /**
   * 每公里单价
   */
  private Double unitPricePerMile;
  /**
   * 每分钟计价
   */
  private Double unitPricePerMinute;
  /**
   * 计价版本
   */
  private Integer fareVersion;
  /**
   * 计价规则唯一标识  cityCode$startFare
   */
  private String fareType;

}
