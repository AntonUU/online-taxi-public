package cn.anton.servicedriveruser.mapper;

import cn.anton.internalcommon.dao.DriverUserWorkStatus;
import cn.anton.internalcommon.dao.ResponseResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-03
 */
@Mapper
public interface DriverUserWorkStatusMapper extends BaseMapper<DriverUserWorkStatus> {
	
	Integer getStatusByCarId(@Param("carId") String carId);
	
	
}
