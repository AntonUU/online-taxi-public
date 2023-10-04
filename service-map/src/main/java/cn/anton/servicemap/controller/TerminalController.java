package cn.anton.servicemap.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.AroundsearchRequest;
import cn.anton.servicemap.service.TerminalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 08:39
 */
@RestController
@RequestMapping("/terminal")
public class TerminalController {
	
	@Resource
	private TerminalService terminalService;
	
	@PostMapping("/add")
	public ResponseResult add(@RequestParam("name") String name, @RequestParam("desc") String desc) {
		System.out.println("收到的name: " + name);
		return terminalService.add(name, desc);
	}
	
	@PostMapping("/aroundsearch")
	public ResponseResult aroundsearch(@RequestBody AroundsearchRequest aroundsearchRequest){
		return terminalService.aroundsearch(aroundsearchRequest);
	}
	
	
}
