package cn.anton.apiboss.service;

/*
 * @author: Anton
 * @create_date: 2023/10/2 09:50
 */

import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface DriverCarBindingRelationshipService {
 
 public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
 
 public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
 
 
}
