package poly.service;

import java.util.List;

import poly.dto.CrewlingDTO;

public interface ICrewlingService {
		
	//주식 종목 코드 크롤링
	int stock_code() throws Exception;

	//주식 종목 코드 검색해서 비교
	String stock_code_search(String stock_name) throws Exception;
	
	//코스피 인기종목 크롤링
	List<String> kospiList(String stock_code) throws Exception;
	
	//코스닥 인기종목 크롤링
	List<String> kosdaqList(String stock_code) throws Exception;
	
	//인기종목
	List<CrewlingDTO>popularList() throws Exception;
	
	// 투자자별 매매동향
	List<CrewlingDTO> stock_deal_List(String stock_code) throws Exception;
	
	// 종목 뉴스
	List<CrewlingDTO> newsList(String stock_code) throws Exception;
	
	// 동일 업종 회사
	List<String> same_company_List(String stock_code) throws Exception;
	
	// 동일 업종 현재가
	List<String> same_present_price_List(String stock_code) throws Exception;
	
	// 동일 업종 전일대비
	List<String> same_net_change_List(String stock_code) throws Exception;
	
	// 동일 업종 등락률
	List<String> same_price_change_List(String stock_code) throws Exception;
	
	// 종목 토론실
	List<CrewlingDTO> stock_talk_List(String stock_code) throws Exception;
	
	//코스피 그래프
	List<CrewlingDTO> kospigrap() throws Exception;
	
	//코스닥 그래프
	List<CrewlingDTO> kosdaqgrap() throws Exception;
	
	//단어 빈도수 분석
	List<CrewlingDTO> wordFrequency(String strToExtrtKwrd) throws Exception;
	
	//주식 현재가
	String currentstock(String stock_code) throws Exception;
	
	//mongoDB에서 news select
	public List<CrewlingDTO> getMongonews(String stock_code) throws Exception;
	
	//mongoDB에서 talk select
	public List<CrewlingDTO> getMongotalk(String stock_code) throws Exception;
	
	//최근 조회 삽입
	int stock_recent(CrewlingDTO pDTO) throws Exception;
	
	//최근 조회 셀렉트
	List<CrewlingDTO> stock_recent_select(String id) throws Exception;
	
	//최근 조회 업데이트
	int stock_recent_update(CrewlingDTO pDTO) throws Exception;
	
	//최근 조회 셀렉트 상위 10개
	List<CrewlingDTO> stock_recent_select_5(String id) throws Exception;
	
	//종목 토론실 최근 조회 삽입
	int talk_recent(CrewlingDTO pDTO) throws Exception;
	
	//종목 토론실 최근 조회 셀렉트
	List<CrewlingDTO> talk_recent_select(String id) throws Exception;
	
	//종목 토론실 최근 조회 업데이트
	int talk_recent_update(CrewlingDTO pDTO) throws Exception;
	
	//종목 토론실 최근 조회 셀렉트 상위 10개
	List<CrewlingDTO> talk_recent_select_5(String id) throws Exception;
	
	//유가 크롤링
	List<CrewlingDTO> stock_oil() throws Exception;
	
	//거래정지 종목 크롤링
	List<CrewlingDTO> stock_stop() throws Exception;
	
	//주식 테마 크롤링
	List<CrewlingDTO> stock_theme() throws Exception;
	
	//해외 주요 증시 크롤링
	List<CrewlingDTO> stock_world() throws Exception;
	
	

}
