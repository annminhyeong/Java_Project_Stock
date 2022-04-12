package poly.persistance.mongo;

import java.util.List;

import poly.dto.MelonDTO;

public interface IMelonMapper {

	/**
	 * MongoDB 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션이름
	 */
	
	public boolean createCollection(String colNm) throws Exception;
	
	/**
	 * 
	 * MongoDB 데이터저장
	 * 
	 * @param pDTO 저장될정보
	 */
	
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception;
	
	/**
	 * 
	 * MongoDB 멜론 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션이름
	 */
	public List<MelonDTO> getRank(String colNm) throws Exception;
}
