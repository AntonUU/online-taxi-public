package cn.anton.apipassenger.controller;

import cn.anton.internalcommon.request.VerificationCodeDTO;
import cn.anton.apipassenger.service.VerificationCodeService;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private VerificationCodeService verificationCodeService;
    
    /**
     * 乘客手机号获取验证码
     * @param dto
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO dto){
        // 验证手机号
        
        // 获取验证码
        verificationCodeService.generateCode(dto.getPassengerPhone());
        return ResponseResult.success("success");
 }
    
    @PostMapping("/verification-code-check")
    public ResponseResult verificationCodeCheck(@RequestBody VerificationCodeDTO dto){
    
        ResponseResult result = verificationCodeService.checkCode(dto.getPassengerPhone(), dto.getVerificationCode());
    
    
        return result;
    }
 
}
