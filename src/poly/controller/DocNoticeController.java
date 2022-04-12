package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.dto.DocNoticeDTO;
import poly.dto.PagingDTO;
import poly.dto.SearchDTO;
import poly.service.IDocNoticeService;

@Controller
public class DocNoticeController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "DocNoticeService")
	private IDocNoticeService docnoticeservice;

	// 검색 기능
	@RequestMapping(value = "project/noticeTest")
	public String noticeTest(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(defaultValue = "1") int curPage) throws Exception {

		log.info(this.getClass().getName() + "noticeTest start");

		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");

		log.info("sDTOsearchType:  " + searchType);
		log.info("sDTOkeyword:  " + keyword);

		SearchDTO sDTO = new SearchDTO();
		sDTO.setSearchType(searchType);
		sDTO.setKeyword(keyword);

		int listCnt = docnoticeservice.AllNoticeDataSearch(sDTO);

		log.info("listCnt:  " + listCnt);
		log.info("curPage:  " + curPage);

		PagingDTO pagination = new PagingDTO(listCnt, curPage);

		int startIndex = pagination.getStartIndex();
		int endIndex = pagination.getEndIndex();

		log.info("startIndex:  " + startIndex);
		log.info("endIndex:  " + endIndex);

		log.info("bDTOsearchType:  " + searchType);
		log.info("bDTOkeyword:  " + keyword);

		DocNoticeDTO bDTO = new DocNoticeDTO();
		bDTO.setStartIndex(startIndex);
		bDTO.setEndIndex(endIndex);
		bDTO.setSearchType(searchType);
		bDTO.setKeyword(keyword);

		List<DocNoticeDTO> rList = docnoticeservice.getNoticeListSearch(bDTO);

		if (rList == null) {
			rList = new ArrayList<DocNoticeDTO>();
		}else {
	         for(int i=0; i<rList.size();i++) {
		            BoardFilter(rList.get(i));
		         }
		      }
		// 죄회된 결과값 넣어줌
		model.addAttribute("rList", rList);
		model.addAttribute("listCnt", listCnt);
		model.addAttribute("pagination", pagination);

		// 변수 초기화
		rList = null;

		log.info(this.getClass().getName() + ".NoticeList end!");

		return "project/notice/NoticeList";

	}

	// 공지사항리스트
	@RequestMapping(value = "project/notice/NoticeList")
	public String getNoticeList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(defaultValue = "1") int curPage) throws Exception {
		log.info(this.getClass().getName() + ".NoticeList start!");

		// 전체 게시글 개수
		int listCnt = docnoticeservice.AllNoticeData();
		log.info("listCnt:  " + listCnt);
		//전체 리스트
		log.info("curPage:  " + curPage);
		//현재 페이지
		PagingDTO pagination = new PagingDTO(listCnt, curPage);

		int startIndex = pagination.getStartIndex();
		int endIndex = pagination.getEndIndex();

		DocNoticeDTO bDTO = new DocNoticeDTO();
		bDTO.setStartIndex(startIndex);
		bDTO.setEndIndex(endIndex);

		log.info("startIndex:  " + startIndex);
		log.info("endIndex:  " + endIndex);

		// 공지사항 리스트 가져오기

		List<DocNoticeDTO> rList = docnoticeservice.getNoticeList(bDTO);

		if (rList == null) {
			rList = new ArrayList<DocNoticeDTO>();
		}else {
	         for(int i=0; i<rList.size();i++) {
		            BoardFilter(rList.get(i));
		         }
		      } 
		/*
		 * else { for(int i = 0;i<rList.size();i++) {
		 * rList.get(i).setNotice_title(rList.get(i).getNotice_title().
		 * replaceAll("& lt;","<" ).replaceAll("& gt;", ">"));
		 * rList.get(i).setNotice_title(rList.get(i).getNotice_title().
		 * replaceAll("& #40;","\\(" ).replaceAll("& #41;", "\\)"));
		 * rList.get(i).setNotice_title(rList.get(i).getNotice_title().
		 * replaceAll("& #39;","'" ));
		 * rList.get(i).setNotice_title(rList.get(i).getNotice_title().
		 * replaceAll("& #256;","script" ));
		 * 
		 * rList.get(i).setNotice_content(rList.get(i).getNotice_content().
		 * replaceAll("& lt;","<" ).replaceAll("& gt;", ">"));
		 * rList.get(i).setNotice_content(rList.get(i).getNotice_content().
		 * replaceAll("& #40;","\\(" ).replaceAll("& #41;", "\\)"));
		 * rList.get(i).setNotice_content(rList.get(i).getNotice_content().
		 * replaceAll("& #39;","'" ));
		 * rList.get(i).setNotice_content(rList.get(i).getNotice_content().
		 * replaceAll("& #256;","script")); }
		 * 
		 * 
		 * }
		 */

		
		
		// 죄회된 결과값 넣어줌
		model.addAttribute("rList", rList);
		model.addAttribute("listCnt", listCnt);
		model.addAttribute("pagination", pagination);

		// 변수 초기화
		rList = null;

		log.info(this.getClass().getName() + ".NoticeList end!");

		return "/project/notice/NoticeList";
	}

	// 상세보기
	@RequestMapping(value = "project/notice/NoticeDetail")
	public String getNoticeDetail(HttpServletRequest request, Model model) throws Exception {

		log.info(this.getClass().getName() + "getNoticeDetail start");

		// jsp의 notice_no를 받아옴
		//
		String notice_no = request.getParameter("notice_no");

		// mapper의 결과값을 pDTO에 자장
		// 결과값을 출력할려면 request getparameter로 값을 받아와야함
		DocNoticeDTO pDTO = docnoticeservice.getNoticeDetail(notice_no);

		if (pDTO == null) {
			pDTO = new DocNoticeDTO();
		}else {
			BoardFilter(pDTO);
		}

		int result = docnoticeservice.NoticeCount(notice_no);

		if (result > 0) {
			log.info("조회수 증가 성공");
		} else {
			log.info("조회수 증가 실패");
		}

		model.addAttribute("pDTO", pDTO);

		pDTO = null;

		return "/project/notice/NoticeDetail";

	}

	// 글 등록
	@RequestMapping(value = "project/notice/NoticeReg")
	public String NoticeReg() {
		log.info(this.getClass().getName());

		return "/project/notice/NoticeReg";
	}

	// 글 등록 확인
	@RequestMapping(value = "project/NoticeRegProc")
	public String NoticeRegProc(HttpServletRequest request, Model model, HttpSession session) {
		log.info(this.getClass().getName());

		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		String notice_writer = (String) session.getAttribute("SS_ID");

		log.info("notice_title:" + notice_title);
		log.info("notice_content:" + notice_content);
		log.info("notice_writer:" + notice_writer);

		DocNoticeDTO bDTO = new DocNoticeDTO();
		bDTO.setNotice_title(notice_title);
		bDTO.setNotice_content(notice_content);
		bDTO.setNotice_writer(notice_writer);

		int result = 0;

		try {
			result = docnoticeservice.insertNoticeInfo(bDTO);
			log.info(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			model.addAttribute("url", "/project/notice/NoticeList.do");
			model.addAttribute("msg", "등록 되었습니다.");
		} else {
			model.addAttribute("url", "/project/notice/NoticeList.do");
			model.addAttribute("msg", "등록 실패했습니다.");
		}

		return "/project/notice/redirect";
	}

	// 글 수정
	@RequestMapping(value = "project/notice/NoticeUpdate")
	public String getNoticeDetail2(HttpServletRequest request, Model model) throws Exception {

		log.info(this.getClass().getName() + "getNoticeDetail start");

		String notice_no = request.getParameter("notice_no");

		DocNoticeDTO pDTO = docnoticeservice.getNoticeDetail(notice_no);

		if (pDTO == null) {
			pDTO = new DocNoticeDTO();
		}else {
			BoardFilter(pDTO);
		}

		model.addAttribute("pDTO", pDTO);

		pDTO = null;

		return "project/notice/NoticeUpdate";

	}

	// 글수정 확인
	@RequestMapping(value = "project/NoticeUpdateProc")
	public String NoticeUpdateProc(HttpServletRequest request, Model model) {
		log.info(this.getClass().getName());

		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		String notice_no = request.getParameter("notice_no");

		log.info(notice_title);
		log.info(notice_content);
		log.info(notice_no);

		DocNoticeDTO bDTO = new DocNoticeDTO();
		bDTO.setNotice_title(notice_title);
		bDTO.setNotice_content(notice_content);
		bDTO.setNotice_no(notice_no);

		int result = 0;

		try {
			result = docnoticeservice.updateNotice(bDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result > 0) {
			model.addAttribute("url", "/project/notice/NoticeList.do");
			model.addAttribute("msg", "수정 되었습니다.");
		} else {
			model.addAttribute("url", "/project/notice/NoticeList.do");
			model.addAttribute("msg", "수정 실패했습니다.");
		}

		return "/project/notice/redirect";
	}

	// 글 삭제
	@RequestMapping(value = "project/notice/NoticeDelete")
	public String NoticeDelete(HttpServletRequest request, ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".NoticeDelete start!");

		DocNoticeDTO bDTO = new DocNoticeDTO();

		int result = 0;

		String notice_no = request.getParameter("notice_no");
		log.info(notice_no);

		bDTO.setNotice_no(notice_no);

		try {
			result = docnoticeservice.deleteNotice(bDTO);

			if (result > 0) {
				model.addAttribute("msg", "삭제되었습니다.");
				model.addAttribute("url", "/project/notice/NoticeList.do");
			} else {
				model.addAttribute("msg", "삭제실패되었습니다.");
				model.addAttribute("url", "/project/notice/NoticeList.do");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + "NoticeDelete end!");
			bDTO = null;
		}

		return "/project/notice/redirect";
	}
	
	public DocNoticeDTO BoardFilter(DocNoticeDTO pDTO) {
	      pDTO.setNotice_title(pDTO.getNotice_title().replaceAll("& lt;","&lt;" ).replaceAll("& gt;", "&gt;"));
	      pDTO.setNotice_title(pDTO.getNotice_title().replaceAll("& #40;","\\(" ).replaceAll("& #41;", "\\)"));
	      pDTO.setNotice_title(pDTO.getNotice_title().replaceAll("& #39;","'" ));
	      pDTO.setNotice_title(pDTO.getNotice_title().replaceAll("& #256;","script" ));
	      if(pDTO.getNotice_content()!=null) {
	         pDTO.setNotice_content(pDTO.getNotice_content().replaceAll("& lt;","<" ).replaceAll("& gt;", ">"));
	         pDTO.setNotice_content(pDTO.getNotice_content().replaceAll("& #40;","\\(" ).replaceAll("& #41;", "\\)"));
	         pDTO.setNotice_content(pDTO.getNotice_content().replaceAll("& #39;","'" ));
	         pDTO.setNotice_content(pDTO.getNotice_content().replaceAll("& #256;","script" ));
	      }
	      return pDTO;
	   }

}
