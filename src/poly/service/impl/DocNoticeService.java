package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.DocNoticeDTO;
import poly.dto.SearchDTO;
import poly.persistance.mapper.DocNoticeMapper;
import poly.service.IDocNoticeService;


@Service("DocNoticeService")
public class DocNoticeService implements IDocNoticeService {

	
	@Resource(name="DocNoticeMapper")
	private DocNoticeMapper docnoticemapper;

	//공지사항리스트
	@Override
	public List<DocNoticeDTO> getNoticeList(DocNoticeDTO bDTO) throws Exception {
			
		return docnoticemapper.getNoticeList(bDTO);
	}

	//글등록
	@Override
	public int insertNoticeInfo(DocNoticeDTO bDTO) throws Exception {
		
		return docnoticemapper.insertNoticeInfo(bDTO);
	}

	//상세보기
	@Override
	public DocNoticeDTO getNoticeDetail(String notice_no) throws Exception {
		
		return docnoticemapper.getNoticeDetail(notice_no);
	}

	//글 수정
	@Override
	public int updateNotice(DocNoticeDTO bDTO) throws Exception{
		
		return docnoticemapper.updateNotice(bDTO);
	}
	
	//글 삭제
	@Override
	public int deleteNotice(DocNoticeDTO bDTO) throws Exception {
	
		return docnoticemapper.deleteNotice(bDTO);
	}
	
	
	//글 조회수
	@Override
	public int NoticeCount(String notice_no) throws Exception {
	
		return docnoticemapper.NoticeCount(notice_no);
	}

	//공지사항 리스트 개수
	@Override
	public int AllNoticeData() throws Exception {
		
		return docnoticemapper.AllNoticeData();
	}

	
	//공지사항 리스트 테스트
	@Override
	public List<DocNoticeDTO> getNoticeListSearch(DocNoticeDTO bDTO) throws Exception {

		return docnoticemapper.getNoticeListSearch(bDTO);
	}
	//공지사항 리스트 개수 테스트
	@Override
	public int AllNoticeDataSearch(SearchDTO sDTO) throws Exception {

		return docnoticemapper.AllNoticeDataSearch(sDTO);
	}
	
	
	
	
	
	


}
