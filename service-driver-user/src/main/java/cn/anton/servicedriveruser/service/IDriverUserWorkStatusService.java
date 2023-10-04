package cn.anton.servicedriveruser.service;

import cn.anton.internalcommon.dao.DriverUserWorkStatus;
import cn.anton.internalcommon.dao.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-03
 */
public interface IDriverUserWorkStatusService extends IService<DriverUserWorkStatus> {
	
	/**
	 * 通过ID更改司机工作状态
	 * @param driverUserWorkStatus
	 * @return
	 */
	ResponseResult changeWorkStatus(DriverUserWorkStatus driverUserWorkStatus);
	
	/**
	 * 添加司机工作状态
	 * @param driverUserWorkStatus
	 * @return
	 */
	ResponseResult addWorkStatus(DriverUserWorkStatus driverUserWorkStatus);
}
