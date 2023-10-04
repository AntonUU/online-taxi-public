package cn.anton.servicemap.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.service.TraceIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 10:15
 */
@RestController
@RequestMapping("/trace")
public class TraceController {
    
    @Resource
    private TraceIService traceIService;

    @PostMapping("/add")
    public ResponseResult add(@RequestParam String tid){
     return traceIService.add(tid);
    }

}
