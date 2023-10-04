package cn.anton.internalcommon.request;

import lombok.Data;

import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/4 19:02
 */
@Data
public class ApiDriverPointDTO {
 
  private Long carId;
  private List<PointUpLoadDTO.Point> points;
 
}
