package cn.anton.apipassenger.feign;

import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author: Anton
 * @create_date: 2023/9/5 20:56
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationcodeCilent {
 
 @GetMapping( value = "/numberCode/4")
 ResponseResult numberCode();
 
}
