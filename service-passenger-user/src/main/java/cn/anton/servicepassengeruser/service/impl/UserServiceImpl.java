package cn.anton.servicepassengeruser.service.impl;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicepassengeruser.dto.PassengerUser;
import cn.anton.servicepassengeruser.mapper.PassengerUserMapper;
import cn.anton.servicepassengeruser.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author: Anton
 * @create_date: 2023/9/7 13:09
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Resource
    private PassengerUserMapper passengerUserMapper;
 
    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("userService调用， 手机号: " + passengerPhone);
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers =
                passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers == null || passengerUsers.size() == 0? "无记录！" : passengerUsers.get(0));
    
        // 判断用户是否存在
        if (passengerUsers == null || passengerUsers.size() == 0) {
            // 如果不存在插入用户信息
            PassengerUser entity = new PassengerUser();
            entity.setPassengerName("王五");
            entity.setPassengerPhone(passengerPhone);
            entity.setState(false);
            entity.setPassenger_gender(true);
            LocalDateTime now = LocalDateTime.now();
            entity.setGmt_create(now);
            entity.setGmt_modified(now);
            
            passengerUserMapper.insert(entity);
        }
        // 颁发Token
            
        
     
        return null;
    }
    
}
