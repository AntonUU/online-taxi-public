package cn.anton.apipassenger.controller;

import cn.anton.apipassenger.feign.ServiceVerificationcodeCilent;
import cn.anton.apipassenger.request.VerificationCodeDTO;
import cn.anton.apipassenger.service.VerificationCodeService;
import cn.anton.apipassenger.service.impl.VerificationCodeServiceImpl;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/5 14:41
 */
@RestController
public class VerificationCodeController {

    @Resource
    private VerificationCodeService vcService = new VerificationCodeServiceImpl();
    
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO dto){
        // 验证手机号
        
        // 获取验证码
        String result = vcService.generateCode(dto.getPassengerPhone());

//        String passengerPhone = dto.getPassengerPhone();
//        String result = vcService.generateCode(passengerPhone);
        return ResponseResult.success(result);
 }

}
