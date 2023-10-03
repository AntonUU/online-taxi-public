package cn.anton.servicedriveruser.service;

import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-01
 */
public interface IDriverCarBindingRelationshipService extends IService<DriverCarBindingRelationship> {
	
	/**
	 * 司机车辆绑定
	 * @param driverCarBindingRelationship
	 * @return
	 */
	ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship);
	
	/**
	 * 司机车辆解绑
	 * @param driverCarBindingRelationship
	 * @return
	 */
	ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
