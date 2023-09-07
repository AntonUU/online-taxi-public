package cn.anton.servicepassengeruser.service;

import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/7 13:09
 */
public interface UserService {
 
    ResponseResult loginOrRegister(String passengerPhone);
}
