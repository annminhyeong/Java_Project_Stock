package poly.persistance.mongo;

import java.util.List;

import poly.dto.NewsDTO;

public interface IMongoNewsMapper {
	
	
	//mongodb 컬렉션 생성
	public boolean createCollection(String colNm)throws Exception;
	
	
	//주식 뉴스 insert
	public int insertNews(List<NewsDTO> pList, String colNm) throws Exception;
	
	//주식 뉴스 select
	public List<NewsDTO>selectNews(String colNm) throws Exception;
	
}
