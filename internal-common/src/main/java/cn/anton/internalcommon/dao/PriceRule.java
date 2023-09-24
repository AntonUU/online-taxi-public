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

  private String cityCode;
  private String vehicleType;
  private Double startFare;
  private Integer startMile;
  private Double unitPricePerMile;
  private Double unitPricePerMinute;

}
