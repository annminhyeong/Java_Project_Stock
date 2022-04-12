package poly.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.CrewlingDTO;
import poly.service.ICrewlingService;

@Controller
public class TestController {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Resource(name = "CrewlingService")
	private ICrewlingService crewlingservice;
	
	//테스트
	@RequestMapping(value = "/test/test")
	public String test() {
		
		return "/test/test";
		
	}
	
	//테스트
	@RequestMapping(value = "/test/test2")
	public String test2() {
		
		log.info("워드 클라우드 시작하애");
		
		return "/test/test2";
		
	}
	
	//테스트
	@RequestMapping(value = "/test/test3")
	public String test3() {
		
		log.info("워드 클라우드 시작하애");
		
		return "/test/test3";
		
	}
	
	//테스트4
	@RequestMapping(value = "/test/test4")
	public String test4() {
		
		log.info("test4 start");
		
		return "/test/test4";
		
	}
	
	//테스트5
	@RequestMapping(value = "/test/test5")
	public String test5(ModelMap model) throws Exception {
		
		log.info("test5 start");
		String stock_code ="005930";
		List<CrewlingDTO> pList =crewlingservice.getMongotalk(stock_code);
		
		model.addAttribute("pList", pList);
		
		return "/test/test5";
		
	}
	
	//테스트6
	@RequestMapping(value = "/test/test6")
	public String test6() {
		
		log.info("test6 start");
		
		return "/test/test6";
		
	}
	
	//테스트6
	@RequestMapping(value = "/test/test7")
	public String test7() {
		
		log.info("test7 start");
		
		return "/test/test7";
		
	}
}
