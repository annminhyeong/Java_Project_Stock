package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.print.attribute.standard.PDLOverrideSupported;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.NewsDTO;
import poly.persistance.mongo.IMongoNewsMapper;
import poly.service.IMongoNewsService;
import poly.util.DateUtil;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



@Service("MongoNewsService")
public class MongoNewsService  implements IMongoNewsService{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "MongoNewsMapper")
	private IMongoNewsMapper mongonewsMapper;

	//주식뉴스 삽입
	@Override
	public int collectNewsRank() throws Exception {
		
		log.info(this.getClass().getName()+"collectNewsRank start");
		
		List<NewsDTO> pList = new ArrayList<NewsDTO>();
		int res =0;
		int page = 1;
		int endpage=2;
		while(page<=endpage) {
		String url = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid2=258&page="+String.valueOf(page);
		Document doc = null;
		
		
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("ul.type06_headline");
		Iterator<Element> eList = element.select("li").iterator();
		//List<NewsDTO> pList = new ArrayList<NewsDTO>();
		
		while (eList.hasNext()) {
			Element newsInfo = eList.next();
			NewsDTO pDTO = new NewsDTO();
			String news_link = newsInfo.select("dt a").attr("href");
			String news_title = newsInfo.select("dt a").text();
			String news_summary = newsInfo.select("dd span.lede").text();
			String news_reporter = newsInfo.select("dd span.writing").text();
			String news_regdate = newsInfo.select("dd span.date").text();
			
			log.info("news_link:"+news_link);
			log.info("news_title:"+news_title);
			log.info("news_summary:"+news_summary);
			log.info("news_reporter:"+news_reporter);
			log.info("news_regdate:"+news_regdate);
			
			
			
			pDTO.setNews_summary(news_summary);
			pDTO.setNews_link(news_link);
			pDTO.setNews_reporter(news_reporter);
			pDTO.setNews_title(news_title);
			pDTO.setNews_regdate(news_regdate);
			



			
			url = news_link;
			doc = Jsoup.connect(url).get();
				
			Elements content = doc.select("div#articleBodyContents");
				
			String news_contents = content.text();
			log.info("news_contents:"+news_contents);
				
				
			pDTO.setNews_contents(news_contents);
				
			pList.add(pDTO);
			pDTO = null;
				
			
		}

		page++;
		}
		
		//collection 이름
		String colNm = "News_"+DateUtil.getDateTime("yyyyMMdd");
		
		//mongodb 컬렉션 생성
		mongonewsMapper.createCollection(colNm);
		
		//mongodb 데이터저장하기
		mongonewsMapper.insertNews(pList, colNm);
		
		return res;
	}
	
	
	//주식 뉴스 select
	@Override
	public List<NewsDTO> getNews() throws Exception {
		
		log.info(this.getClass().getName()+".getNews start");
		
		String colNm = "News_"+DateUtil.getDateTime("yyyyMMdd");
		
		List<NewsDTO> rList = mongonewsMapper.selectNews(colNm);
		
		if(rList == null) {
			rList = new ArrayList<NewsDTO>();
		}
		
		log.info(this.getClass().getName()+".getNews end");
		
		return rList;
	}
	

}
