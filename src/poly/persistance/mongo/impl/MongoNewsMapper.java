package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.NewsDTO;
import poly.persistance.mongo.IMongoNewsMapper;
import poly.util.CmmUtil;

@Component("MongoNewsMapper")
public class MongoNewsMapper implements IMongoNewsMapper {
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	//mongodb 컬렌션 생성
	@Override
	public boolean createCollection(String colNm) throws Exception {
		
		log.info(this.getClass().getName()+"createCollection start");
		
		boolean res= false;
		
		//기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if(mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);//기존 컬렉션 삭제
		}
		
		//컬렉션 생성 및 인덱스 생성 MongoDB에서 데이터 가여오는 방식에 맞게 인덱스를 반드시 생성 해야함
		//데이터 양이 많지 않으면 문제 되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time",1).append("rank", 1),"rankIdx");
		
		
		res=true;
		
		log.info(this.getClass().getName()+"createCollection end");
		
		return res;
	}	
	
	
	//주식 뉴스 삽입	
	@Override
	public int insertNews(List<NewsDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertNews start!");
		
		int res=0;
		
		if(pList ==null) {
			pList = new ArrayList<NewsDTO>();
		}
		
		Iterator<NewsDTO> it = pList.iterator();
		
		while(it.hasNext()) {
			NewsDTO pDTO = (NewsDTO) it.next();
		
		
			if(pDTO == null) {
				pDTO = new NewsDTO();
			}
			
			mongodb.insert(pDTO,colNm);
			res=1;
		
		}
		
		log.info(this.getClass().getName() + ".insertNews end!");
		return res;
	}

	
	
	//주식뉴스 select
	@Override
	public List<NewsDTO> selectNews(String colNm) throws Exception {
		
		log.info(this.getClass().getName()+".selectNews start");
		
		//데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		
		//컬렉션으로 부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		
		
		List<NewsDTO> rList = new ArrayList<NewsDTO>();
		
		NewsDTO rDTO = null;
		
		while(cursor.hasNext()) {
			rDTO = new NewsDTO();
			final DBObject current = cursor.next();
			
			String news_link = CmmUtil.nvl((String) current.get("news_link"));
			String news_title = CmmUtil.nvl((String) current.get("news_title"));
			String news_contents = CmmUtil.nvl((String) current.get("news_contents"));
			String news_summary = CmmUtil.nvl((String) current.get("news_summary"));
			String news_reporter = CmmUtil.nvl((String) current.get("news_reporter"));
			String news_regdate = CmmUtil.nvl((String) current.get("news_regdate"));
			
			log.info("news_link:"+news_link);
			log.info("news_title:"+news_title);
			log.info("news_contents:"+news_contents);
			log.info("news_summary:"+news_summary);
			log.info("news_reporter:"+news_reporter);
			log.info("news_regdate:"+news_regdate);
			
			rDTO.setNews_link(news_link);
			rDTO.setNews_title(news_title);
			rDTO.setNews_contents(news_contents);
			rDTO.setNews_summary(news_summary);
			rDTO.setNews_reporter(news_reporter);
			rDTO.setNews_regdate(news_regdate);
			
			rList.add(rDTO);
			rDTO=null;
			
		}
		
		log.info(this.getClass().getName()+".selectNews end");
		
		
		return rList;
	}

}
