package poly.service;

import java.util.List;

import poly.dto.DocNoticeDTO;
import poly.dto.SearchDTO;

public interface IDocNoticeService {
	
	//게시판 리스트
	List<DocNoticeDTO> getNoticeList(DocNoticeDTO bDTO) throws Exception;
	
	//글쓰기
	int insertNoticeInfo(DocNoticeDTO bDTO) throws Exception;
	
	//글상세
	DocNoticeDTO getNoticeDetail(String notice_no) throws Exception;
	
	//글 수정
	int updateNotice(DocNoticeDTO bDTO) throws Exception;
	
	//글 삭제
	int deleteNotice(DocNoticeDTO bDTO) throws Exception;
	
	//글 조회수
	int NoticeCount(String notice_no) throws Exception;
	
	//공지사항 리스트 개수
	int AllNoticeData() throws Exception;
	
	//공지사항 리스트 테스트
	List<DocNoticeDTO> getNoticeListSearch(DocNoticeDTO bDTO) throws Exception;
	
	//공지사항 리스트 개수 테스트
	int AllNoticeDataSearch(SearchDTO sDTO) throws Exception;
	

	
}
