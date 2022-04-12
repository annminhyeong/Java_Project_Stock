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

import poly.dto.CrewlingDTO;
import poly.persistance.mongo.IMongoCrewlingMapper;
import poly.util.CmmUtil;

@Component("MongoCrewlingMapper")
public class MongoCrewlingMapper implements IMongoCrewlingMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	// 뉴스 토론실 컬렉션 생성 및 인덱스 생성
	@Override
	public boolean CreateCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + "CreateCollection start");

		boolean res = false;

		if (mongodb.collectionExists(colNm)) {

			// 기존에 등록된 컬렉션 이름이 있는지 체크하고 있으면 삭제함
			mongodb.dropCollection(colNm);
			

		}
		
		// 컬렉션 생성 및 인덱스 생성 MongoDB에서 데이터 가여오는 방식에 맞게 인덱스를 반드시 생성 해야함
		// 데이터 양이 많지 않으면 문제 되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("rank", 1), "rankIdx");

		res = true;

		log.info(this.getClass().getName() + "CreateCollection end");

		return false;
	}

	// 종목 뉴스
	@Override
	public int newsList(List<CrewlingDTO> pList, String colNm) throws Exception {
		
		log.info(this.getClass().getName()+"newsList start");
		
		int res =0;
		
		if(pList == null) {
			pList = new ArrayList<CrewlingDTO>();
		}
		
		Iterator<CrewlingDTO> it = pList.iterator();
		
		while(it.hasNext()) {
			CrewlingDTO pDTO = (CrewlingDTO)it.next();
			
			if(pDTO == null) {
				pDTO = new CrewlingDTO();
			}
			
			
			
			mongodb.insert(pDTO,colNm);
			res=1;
		}
		log.info(this.getClass().getName()+"newsList end");
		return res;
	}

	// 종목 토론실
	@Override
	public int stock_talk_List(List<CrewlingDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName()+"stock_talk_List start");
		
		int res =0;
		
		if(pList == null) {
			pList = new ArrayList<CrewlingDTO>();
		}
		
		Iterator<CrewlingDTO> it = pList.iterator();
		
		while(it.hasNext()) {
			CrewlingDTO pDTO = (CrewlingDTO)it.next();
			
			if(pDTO == null) {
				pDTO = new CrewlingDTO();
			}
			
			mongodb.insert(pDTO,colNm);
			res=1;
		}
		log.info(this.getClass().getName()+"stock_talk_List end");
		return res;
	}

	@Override
	public List<CrewlingDTO> NewsSelect(String colNm, String stock_code) throws Exception {
		
		log.info("NewsSelect start");
		
		DBCollection rCol = mongodb.getCollection(colNm);
		Iterator<DBObject> cursor = rCol.find();
		
		List<CrewlingDTO> rList = new ArrayList<CrewlingDTO>();
		
		
        BasicDBObject query = new BasicDBObject();
        query.put("stock_code", stock_code);
        
        cursor = rCol.find(query);
        while (cursor.hasNext()) {
			CrewlingDTO rDTO = new CrewlingDTO();
			
			final DBObject current = cursor.next();
			
			String news_link = CmmUtil.nvl((String) current.get("news_link"));
			String news_title = CmmUtil.nvl((String) current.get("news_title"));
			String news_contents = CmmUtil.nvl((String) current.get("news_contents"));
			String news_writer = CmmUtil.nvl((String) current.get("news_writer"));
			String news_time = CmmUtil.nvl((String) current.get("news_time"));
			
			rDTO.setNews_link(news_link);
			rDTO.setNews_title(news_title);
			rDTO.setNews_contents(news_contents);
			rDTO.setNews_writer(news_writer);
			rDTO.setNews_time(news_time);
			
			rList.add(rDTO);
        }
		
		log.info("NewsSelect end");
		return rList;
	}

	@Override
	public List<CrewlingDTO> TalkSelect(String colNm, String stock_code) throws Exception {
		
		log.info("TalkSelect start");
		
		DBCollection rCol = mongodb.getCollection(colNm);
		Iterator<DBObject> cursor = rCol.find();
		List<CrewlingDTO> rList = new ArrayList<CrewlingDTO>();
		
		BasicDBObject query = new BasicDBObject();
        query.put("stock_code", stock_code);
        
        cursor = rCol.find(query);
        while (cursor.hasNext()) {
			CrewlingDTO rDTO = new CrewlingDTO();
			
			final DBObject current = cursor.next();
			
			String talk_link = CmmUtil.nvl((String) current.get("talk_link"));
			String talk_title = CmmUtil.nvl((String) current.get("talk_title"));
			String talk_contents = CmmUtil.nvl((String) current.get("talk_contents"));
			String talk_id = CmmUtil.nvl((String) current.get("talk_id"));
			String talk_date = CmmUtil.nvl((String) current.get("talk_date"));
			
			rDTO.setTalk_link(talk_link);
			rDTO.setTalk_title(talk_title);
			rDTO.setTalk_contents(talk_contents);
			rDTO.setTalk_id(talk_id);
			rDTO.setTalk_date(talk_date);
			rDTO.setStock_code(stock_code);
			
			rList.add(rDTO);
        }
        		
		log.info("TalkSelect end");
		return rList;
	}

}
