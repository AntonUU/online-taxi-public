package cn.anton.apipassenger.service;

import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/10 14:26
 */
public interface UserService {

    public ResponseResult getUser(String accessToken);

}
