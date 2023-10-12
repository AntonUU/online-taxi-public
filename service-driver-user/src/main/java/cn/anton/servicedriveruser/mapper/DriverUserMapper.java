package cn.anton.servicedriveruser.mapper;

import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.response.OrderDriverResponse;
import cn.anton.internalcommon.select.DriverCarSelect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * @author: Anton
 * @create_date: 2023/9/29 07:26
 */
@Mapper
public interface DriverUserMapper extends BaseMapper<DriverUser> {

	Integer selectCityDriverUserByCityCode(@Param("cityCode") String cityCode);
	
	OrderDriverResponse getAvailableDriver(DriverCarSelect driverCarSelect);
}
