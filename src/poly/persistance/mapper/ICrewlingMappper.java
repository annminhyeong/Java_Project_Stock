package poly.persistance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import config.Mapper;
import poly.dto.CrewlingDTO;
import poly.dto.NewsDTO;

@Mapper("CrewlingMapper")
public interface ICrewlingMappper {
	
	//주식 종목 코드 삽입
	int stock_code(NewsDTO pDTO) throws Exception;
	
	//주식 종목 코드 조회
	List<String> stock_code_chk() throws Exception;
	
	//주식 종목 검색
	String stock_code_search(String stock_name) throws Exception;
	
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
}
