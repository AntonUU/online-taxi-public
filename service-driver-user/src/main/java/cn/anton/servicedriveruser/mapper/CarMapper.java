package cn.anton.servicedriveruser.mapper;

import cn.anton.internalcommon.dao.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/*
 * TODO aoi-boss 添加车辆绑定和解绑代码实现
 * @author: Anton
 * @create_date: 2023/10/1 07:52
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {
}
