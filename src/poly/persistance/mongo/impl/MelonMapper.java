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

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;
import poly.util.CmmUtil;

@Component("MelonMapper")
public class MelonMapper implements IMelonMapper{
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createCollection(String colNm) throws Exception{
		
		log.info(this.getClass().getName() + ".create Start");
		
		boolean res = false;
		
		// 기존 등록된 컬렉션 이름 존재하는지 체크, 존재하면 기존 컬렉션 삭제
		if(mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);//기존삭제
		}
	
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("rank", 1), "rankIdx");
		
		res = true;
		
		log.info(this.getClass().getName() + ".createCollection end");
	
		return res;
	}

	@Override
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertrank start!");
		int res = 0;
		
		if (pList == null) {
			pList = new ArrayList<MelonDTO>();		
		}
		
		Iterator<MelonDTO> it = pList.iterator();
		
		while (it.hasNext()) {
			MelonDTO pDTO = (MelonDTO) it.next();
			
			if(pDTO == null ) {
				pDTO = new MelonDTO();
			}
			mongodb.insert(pDTO, colNm);
		}
		res = 1;
		
		log.info(this.getClass().getName() + ".insertrank end!");
		
		return res;
	}
	
	@Override
	public List<MelonDTO> getRank(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getRank STart!");
		
		DBCollection rCol = mongodb.getCollection(colNm);
		
		Iterator<DBObject> cursor = rCol.find();
		
		List<MelonDTO> rList = new ArrayList<MelonDTO>();
		
		MelonDTO rDTO = null;
		
		while (cursor.hasNext()) {
			
			rDTO = new MelonDTO();
			
			final DBObject current = cursor.next();
			
			String collect_time = CmmUtil.nvl((String) current.get("collect_time"));
			String rank = CmmUtil.nvl((String) current.get("rank"));
			String song = CmmUtil.nvl((String) current.get("song"));
			String singer = CmmUtil.nvl((String) current.get("singer"));
			String album = CmmUtil.nvl((String) current.get("album"));
			
			rDTO.setCollect_time(collect_time);
			rDTO.setRank(rank);
			rDTO.setSong(song);
			rDTO.setSinger(singer);
			rDTO.setAlbum(album);
			
			rList.add(rDTO);
			
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getRank END!");
		
		return rList;
	}
	
	
	
	
	
	
	
}
