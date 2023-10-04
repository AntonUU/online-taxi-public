package cn.anton.servicedriveruser.service.impl;

import cn.anton.internalcommon.constant.DriverCarStatusConstant;
import cn.anton.internalcommon.dao.DriverUserWorkStatus;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import cn.anton.servicedriveruser.service.IDriverUserWorkStatusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-03
 */
@Service
public class DriverUserWorkStatusServiceImpl extends ServiceImpl<DriverUserWorkStatusMapper, DriverUserWorkStatus> implements IDriverUserWorkStatusService {
	
	
	@Override
	public ResponseResult changeWorkStatus(DriverUserWorkStatus driverUserWorkStatus) {
		
		QueryWrapper<DriverUserWorkStatus> query = new QueryWrapper<DriverUserWorkStatus>().eq("driver_id", driverUserWorkStatus.getDriverId());
		DriverUserWorkStatus entity = this.getOne(query);
		
		entity.setGmtModified(LocalDateTime.now());
		entity.setWorkStatus(driverUserWorkStatus.getWorkStatus());
		
		this.updateById(entity);
		
		return ResponseResult.success();
	}
	
	@Override
	public ResponseResult addWorkStatus(DriverUserWorkStatus driverUserWorkStatus) {
		
		driverUserWorkStatus.setWorkStatus(DriverCarStatusConstant.DRIVER_CAR_STATUS_STOP);
		driverUserWorkStatus.setGmtCreate(LocalDateTime.now());
		
		this.save(driverUserWorkStatus);
		
		return ResponseResult.success();
	}
}
