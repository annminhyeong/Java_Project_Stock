package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.NewsDTO;
import poly.service.IMongoNewsService;

@Controller
public class MongoNewsControlller {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource(name = "MongoNewsService")
	private IMongoNewsService mongonewsService;
	
	
	//
	@RequestMapping(value = "/project/news/newsList")
	public String newsList(ModelMap model) throws Exception {
		log.info(this.getClass().getName()+"collectNewsRank start");
		
		mongonewsService.collectNewsRank();
		
		List<NewsDTO>rList = mongonewsService.getNews();
		
		if(rList == null) {
			rList = new ArrayList<NewsDTO>();
		}
		
		model.addAttribute("rList", rList);
		
		log.info(this.getClass().getName()+"collectNewsRank end");

		return "/project/news/newsList";
	}
	
	
	
	@RequestMapping(value = "news/collectNewsRank")
	@ResponseBody
	public String collectNewsRank(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName()+"collectNewsRank start");
		
		mongonewsService.collectNewsRank();
		
		log.info(this.getClass().getName()+"collectNewsRank end");
		
		return "success";
	}
	
	
	
	@RequestMapping(value = "news/getNews")
	@ResponseBody
	public List<NewsDTO>getNews(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		log.info(this.getClass().getName()+".selectNews start");
		
		List<NewsDTO>rList = mongonewsService.getNews();
		
		if(rList == null) {
			rList = new ArrayList<NewsDTO>();
		}
		
		log.info(this.getClass().getName()+".selectNews end");
		
		return rList;
	}
}
