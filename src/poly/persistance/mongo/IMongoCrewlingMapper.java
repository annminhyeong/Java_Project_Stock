package poly.persistance.mongo;

import java.util.List;

import poly.dto.CrewlingDTO;

public interface IMongoCrewlingMapper {
	
	//몽고 디비 컬렉션 생성 및 인덱스 생성
	public boolean CreateCollection(String colNm)throws Exception;
	
	//종목 뉴스 insert
	public int newsList(List<CrewlingDTO> pList, String colNm) throws Exception;
		
	//종목토론실 insert
	public int stock_talk_List(List<CrewlingDTO> pList, String colNm) throws Exception;
	
	//종목 뉴스 select
	public List<CrewlingDTO> NewsSelect(String colNm, String stock_code) throws Exception;
	
	//종목 토론실 select
	public List<CrewlingDTO> TalkSelect(String colNm, String stock_code) throws Exception;
	
	
}
