package poly.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import poly.dto.CrewlingDTO;
import poly.service.ICrewlingService;

@Controller
public class CrewlingController {

	@Resource(name = "CrewlingService")
	private ICrewlingService crewlingService;

	private Logger log = Logger.getLogger(this.getClass().getName());

	// 주식 정보 검색
	@RequestMapping(value = "/project/crewling/stock")
	public String stock(ModelMap model, HttpSession session) throws Exception {

		log.info("stock start");
		
		String id = (String) session.getAttribute("user_id");
		id = (String) session.getAttribute("SS_ID");
		log.info("id: "+ id);
		
		//인기순위
		List<CrewlingDTO> pList = crewlingService.popularList();
		
		//최근조회
		List<CrewlingDTO> sList = crewlingService.stock_recent_select_5(id);
		model.addAttribute("pList", pList);
		model.addAttribute("sList", sList);

		return "/project/crewling/stock";

	}

	// 주식 정보 검색 결과
	@RequestMapping(value = "/project/crewling/stockResult")
	public String stockResult() {

		log.info("stockResult start");

		return "/project/crewling/stockResult";

	}

	// 주식 정보 검색 결과 기능
	@RequestMapping(value = "/StockSearch")
	public String stockSearch(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		String id = (String) session.getAttribute("SS_ID");
		log.info("id: "+ id);
		
		String stock_name = request.getParameter("stock_name");
		log.info("stock_name:" + stock_name);

		String stock_code = crewlingService.stock_code_search(stock_name);
		log.info("stock_code:" + stock_code);

		if (stock_code == null) {
			model.addAttribute("msg", "검색결과가 없습니다.");
			model.addAttribute("url", "/project/crewling/stock.do");
			return "/project/notice/redirect";
		} else {
			// 최근조회 기능
			List<CrewlingDTO> pList = crewlingService.stock_recent_select(id);
			if (pList.contains(stock_name)) {
				log.info("이미 존재하는 종목.");
				CrewlingDTO pDTO = new CrewlingDTO();
				pDTO.setStock_name(stock_name);
				pDTO.setId(id);
				crewlingService.stock_recent_update(pDTO);
				pDTO = null;
			} else {
				log.info("새로운 종목.");
				CrewlingDTO pDTO = new CrewlingDTO();
				pDTO.setStock_name(stock_name);
				pDTO.setId(id);
				crewlingService.stock_recent(pDTO);
				pDTO=null;
			}
		}

		List<String> same_company_List = crewlingService.same_company_List(stock_code);
		List<String> same_present_price_List = crewlingService.same_present_price_List(stock_code);
		List<String> same_price_change_List = crewlingService.same_price_change_List(stock_code);
		List<CrewlingDTO> stock_deal_List = crewlingService.stock_deal_List(stock_code);
		List<CrewlingDTO> nList = crewlingService.newsList(stock_code);
		List<CrewlingDTO> mList = crewlingService.getMongonews(stock_code);
		String currentstock = crewlingService.currentstock(stock_code);

		// 코모란 시작
		// 분석 결과값이 나온 단어 리스트
		List<String> komoranList = new ArrayList<String>();

		// 코모란 분석기 돌리고 나온 단어 리스트 를 카운팅 한 리스트
		List<String> kWordList = new ArrayList<String>();
		List<Integer> kCntList = new ArrayList<Integer>();

		Iterator<CrewlingDTO> InList = nList.iterator();

		while (InList.hasNext()) {
			log.info("문장 분석중입니다.");
			CrewlingDTO pDTO = new CrewlingDTO();
			pDTO = InList.next();
			Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
			KomoranResult analyKomoranResult = komoran.analyze(pDTO.getNews_title() + " " + pDTO.getNews_contents());
			komoranList.addAll(analyKomoranResult.getNouns());
			komoran = null;
			analyKomoranResult = null;
			pDTO = null;
		}
		for (int i = 0; i < komoranList.size(); i++) {
			if (komoranList.get(i).length() > 1) {
				if (!kWordList.contains(komoranList.get(i))) {
					kWordList.add(komoranList.get(i));
					int tmp = 0;
					for (int j = 0; j < komoranList.size(); j++) {
						if (komoranList.get(i).equals(komoranList.get(j)))
							tmp++;
					}
					kCntList.add(tmp);
				}
			}
		}

		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		for (int i = 0; i < kWordList.size(); i++) {
			CrewlingDTO pDTO = new CrewlingDTO();
			pDTO.setWordname(kWordList.get(i));
			pDTO.setWordcount(Integer.toString(kCntList.get(i)));
			pList.add(pDTO);
			pDTO = null;
		}
		kWordList = null;
		kCntList = null;

		// 코모란 끝

		// List<CrewlingDTO> wList = crewlingService.wordFrequency(strToExtrtKwrd);
		// Iterator<CrewlingDTO> IpList = wList.iterator();

		/*
		 * while (IpList.hasNext()) { CrewlingDTO pDTO = new CrewlingDTO(); pDTO =
		 * IpList.next();
		 * 
		 * if (pDTO.getWordname().length() > 1 && Integer.parseInt(pDTO.getWordcount())
		 * > 3) { pList.add(pDTO); } pDTO = null; }
		 */
		model.addAttribute("same_company_List", same_company_List);
		model.addAttribute("same_present_price_List", same_present_price_List);
		model.addAttribute("same_price_change_List", same_price_change_List);
		model.addAttribute("stock_deal_List", stock_deal_List);
		model.addAttribute("pList", pList);
		model.addAttribute("mList", mList);
		model.addAttribute("stock_name", stock_name);
		model.addAttribute("currentstock", currentstock);
		return "/project/crewling/stockResult";

	}

	// 코스피 코스닥 그래프
	@RequestMapping(value = "/project/crewling/kosgrap")
	public String kospidaq(ModelMap model) throws Exception {
		log.info("kospidaq start");

		List<CrewlingDTO> kospiList = crewlingService.kospigrap();
		List<CrewlingDTO> kosdaqList = crewlingService.kosdaqgrap();

		model.addAttribute("kospiList", kospiList);
		model.addAttribute("kosdaqList", kosdaqList);

		return "/project/crewling/kosgrap";

	}

	// 종목 토론실 검색
	@RequestMapping(value = "/project/crewling/talk")
	public String talk(ModelMap model, HttpSession session) throws Exception {
		log.info("talk start");
		
		String id = (String) session.getAttribute("SS_ID");
		log.info("id: "+ id);
		
		List<CrewlingDTO> pList = crewlingService.popularList();
		
		//최근조회
		List<CrewlingDTO> sList = crewlingService.talk_recent_select_5(id);

		model.addAttribute("pList", pList);
		model.addAttribute("sList", sList);

		return "/project/crewling/talk";
	}

	// 종목 토론실 검색 결과
	@RequestMapping(value = "/project/crewling/talkResult")
	public String talkResult() {
		log.info("talkResult start");

		return "/project/crewling/talkResult";
	}

	// 종목 토론실 기능
	@RequestMapping(value = "/Talksearch")
	public String talksearch(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
				
		log.info("talksearch start");
		
		String id = (String) session.getAttribute("SS_ID");
		log.info("id: "+ id);
		
		String stock_name = request.getParameter("stock_name");
		log.info("stock_name:" + stock_name);

		String stock_code = crewlingService.stock_code_search(stock_name);
		log.info("stock_code:" + stock_code);

		if (stock_code == null) {
			model.addAttribute("msg", "검색결과가 없습니다.");
			model.addAttribute("url", "/project/crewling/stock.do");
			return "/project/notice/redirect";
		}  else {
			// 최근조회 기능
			List<CrewlingDTO> pList = crewlingService.talk_recent_select(id);
			if (pList.contains(stock_name)) {
				log.info("이미 존재하는 종목.");
				CrewlingDTO pDTO = new CrewlingDTO();
				pDTO.setStock_name(stock_name);
				pDTO.setId(id);
				crewlingService.talk_recent_update(pDTO);
				pDTO=null;
			} else {
				log.info("새로운 종목.");
				CrewlingDTO pDTO = new CrewlingDTO();
				pDTO.setStock_name(stock_name);
				pDTO.setId(id);
				crewlingService.talk_recent(pDTO);
				pDTO=null;
			}
		}

		List<CrewlingDTO> tList = crewlingService.stock_talk_List(stock_code);
		List<CrewlingDTO> mList = crewlingService.getMongotalk(stock_code);

		// 코모란 시작
		// 분석 결과값이 나온 단어 리스트
		List<String> komoranList = new ArrayList<String>();

		// 코모란 분석기 돌리고 나온 단어 리스트 를 카운팅 한 리스트
		List<String> kWordList = new ArrayList<String>();
		List<Integer> kCntList = new ArrayList<Integer>();

		Iterator<CrewlingDTO> InList = tList.iterator();

		while (InList.hasNext()) {
			log.info("문장 분석중입니다.");
			CrewlingDTO pDTO = new CrewlingDTO();
			pDTO = InList.next();
			Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
			KomoranResult analyKomoranResult = komoran.analyze(pDTO.getTalk_title() + " " + pDTO.getTalk_contents());
			komoranList.addAll(analyKomoranResult.getNouns());
			komoran = null;
			analyKomoranResult = null;
			pDTO = null;
		}
		for (int i = 0; i < komoranList.size(); i++) {
			if (komoranList.get(i).length() > 1) {
				if (!kWordList.contains(komoranList.get(i))) {
					kWordList.add(komoranList.get(i));
					int tmp = 0;
					for (int j = 0; j < komoranList.size(); j++) {
						if (komoranList.get(i).equals(komoranList.get(j)))
							tmp++;
					}
					kCntList.add(tmp);
				}
			}
		}

		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		for (int i = 0; i < kWordList.size(); i++) {
			CrewlingDTO pDTO = new CrewlingDTO();
			pDTO.setWordname(kWordList.get(i));
			pDTO.setWordcount(Integer.toString(kCntList.get(i)));
			pList.add(pDTO);
			pDTO = null;
		}
		kWordList = null;
		kCntList = null;

		// 코모란 끝
		model.addAttribute("mList", mList);
		model.addAttribute("pList", pList);

		return "/project/crewling/talkResult";
	}

	// 크롤링 종목 코드 mysql에 넣기
	@RequestMapping(value = "/stockcode")
	@ResponseBody
	public String stockcode() throws Exception {

		log.info("stockcode start");

		crewlingService.stock_code();

		log.info("stockcode end");

		return "success";
	}
	
	
	// 거래정지 품목
	@RequestMapping(value = "/project/crewling/stop")
	public String stockstop(ModelMap model) throws Exception {

		log.info("stop start");
		
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		List<CrewlingDTO> wList = new ArrayList<CrewlingDTO>();
		List<CrewlingDTO> oList = new ArrayList<CrewlingDTO>();
		List<CrewlingDTO> tList = new ArrayList<CrewlingDTO>();
		
		pList = crewlingService.stock_stop();
		wList = crewlingService.stock_world();
		oList = crewlingService.stock_oil();
		tList = crewlingService.stock_theme();
		
		model.addAttribute("pList", pList);
		model.addAttribute( "wList", wList);
		model.addAttribute( "oList", oList);
		model.addAttribute( "tList", tList);

		return "/project/crewling/stop";

	}
	
	// 해외지수와 유가
	@RequestMapping(value = "/project/crewling/WorldOil")
	public String WorldOil(ModelMap model) throws Exception {
		log.info("WorldOil start");
		
		List<CrewlingDTO> wList = new ArrayList<CrewlingDTO>();
		List<CrewlingDTO> oList = new ArrayList<CrewlingDTO>();
		List<CrewlingDTO> tList = new ArrayList<CrewlingDTO>();
		
		wList = crewlingService.stock_world();
		oList = crewlingService.stock_oil();
		tList = crewlingService.stock_theme();
		
		model.addAttribute( "wList", wList);
		model.addAttribute( "oList", oList);
		model.addAttribute( "tList", tList);
		
		return "/project/crewling/WorldOil";

	}

}
