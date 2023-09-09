package cn.anton.apipassenger.service;

import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/9 14:29
 */
public interface TokenService {
 
  public ResponseResult refreshToken(String refreshTokenStr);
 
}
