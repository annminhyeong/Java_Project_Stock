package poly.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.springframework.stereotype.Service;

import poly.dto.CrewlingDTO;
import poly.dto.NewsDTO;
import poly.persistance.mapper.ICrewlingMappper;
import poly.persistance.mongo.IMongoCrewlingMapper;
import poly.service.ICrewlingService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("CrewlingService")
public class CrewlingService implements ICrewlingService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "CrewlingMapper")
	private ICrewlingMappper crewlingMapper;

	@Resource(name = "MongoCrewlingMapper")
	private IMongoCrewlingMapper mongocrewlingmapper;

	// 주식 종목 코드 크롤링
	@Override
	public int stock_code() throws Exception {

		log.info(this.getClass().getName() + "stock_code service start");

		String url = "https://www.ktb.co.kr/trading/popup/itemPop.jspx";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("tbody.tbody_content");
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element codeInfo = eList.next();
			NewsDTO pDTO = new NewsDTO();

			String stock_code = CmmUtil.nvl(codeInfo.select("td").get(0).text());
			String stock_name = CmmUtil.nvl(codeInfo.select("td a").text());

			List<String> pList = crewlingMapper.stock_code_chk();
			if (pList == null) {
				pList = new ArrayList<String>();
			}

			if (pList.contains(stock_code)) {
				log.info("이미 존재하는 코드입니다.");
			} else {
				log.info("새로운 코드입니다.");

				log.info("stock_name : " + stock_code);
				log.info("stock_code : " + stock_name);

				pDTO.setStock_code(stock_code);
				pDTO.setStock_name(stock_name);

				int result = crewlingMapper.stock_code(pDTO);

				if (result < 0) {
					log.info("종목 크롤링 오류입니다.");
					return 0;
				}
			}

			pDTO = null;

		}

		log.info(this.getClass().getName() + "stock_code service end");
		return 1;
	}

	// 주식 종목 검색
	@Override
	public String stock_code_search(String stock_name) throws Exception {

		return crewlingMapper.stock_code_search(stock_name);
	}

	// 코스피 인기종목 크롤링
	@Override
	public List<String> kospiList(String stock_code) throws Exception {

		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=" + stock_code;

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 코스피 인기종목
		Elements kospi_element = doc.select("div#fav_kospi");
		// System.out.println("test:"+kospi_element);

		// 코스피 인기종목
		Iterator<Element> kospi_List = kospi_element.select("table tbody tr").iterator();

		List<String> pList = new ArrayList<String>();
		// 코스피 인기종목
		while (kospi_List.hasNext()) {

			Element info = kospi_List.next();
			String kospi = info.select("th > a").text();

			pList.add(kospi);
			log.info("코스피 종목 : " + kospi);

			kospi = null;

		}

		return pList;

	}

	// 코스닥 인기종목 크롤링
	@Override
	public List<String> kosdaqList(String stock_code) throws Exception {
		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=" + stock_code;

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 코스닥 인기종목
		Elements kosdaq_element = doc.select("div#fav_kosdaq");

		// 코스닥 인기종목
		Iterator<Element> kosdaq_List = kosdaq_element.select("table tbody tr").iterator();

		List<String> dList = new ArrayList<String>();
		// 코스피 인기종목
		while (kosdaq_List.hasNext()) {

			Element info = kosdaq_List.next();
			String kosdaq = info.select("th a").text();

			dList.add(kosdaq);
			log.info("코스닥 종목 : " + kosdaq);

			kosdaq = null;

		}

		return dList;
	}

	// 인기종목
	@Override
	public List<CrewlingDTO> popularList() throws Exception {
		// 메인페이지
		String url = "https://finance.naver.com/sise/lastsearch2.nhn";

		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 검색어 인기검색어
		Elements popular = doc.select("table.type_5");
		// System.out.println(popular);

		// 코스피 인기종목
		Iterator<Element> popular_List = popular.select("tbody tr").iterator();

		// 코스피 인기종목
		System.out.println("-인기종목-");
		while (popular_List.hasNext()) {

			Element info = popular_List.next();
			String rank = info.select("td").eq(0).text();
			String company_name = info.select("td").eq(1).text();
			String searchrate = info.select("td").eq(2).text();
			searchrate = searchrate.replaceAll("%", "");

			if (!rank.equals("")) {
				log.info("순위: " + rank);
				log.info("종목명: " + company_name);
				log.info("검색비율: " + searchrate);

				CrewlingDTO pDTO = new CrewlingDTO();
				pDTO.setRank(rank);
				pDTO.setCompany_name(company_name);
				pDTO.setSearchrate(searchrate);

				pList.add(pDTO);
				pDTO = null;

			}

		}
		return pList;
	}

	// 투자자별 매매동향
	@Override
	public List<CrewlingDTO> stock_deal_List(String stock_code) throws Exception {

		// 투자자별 매매동향
		String url3 = "https://finance.naver.com/item/frgn.nhn?code=" + stock_code;

		Document doc3 = null;
		doc3 = Jsoup.connect(url3).get();

		// 투자자별 매매동향
		Elements stock_deal = doc3.select("table.type2");
		// System.out.println("stock_deal:"+stock_deal);

		// 투자자별 매매동향
		Iterator<Element> stock_deal_List = stock_deal.select("tbody tr").iterator();

		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		// 투자자별 매매동향
		while (stock_deal_List.hasNext()) {
			CrewlingDTO pDTO = new CrewlingDTO();

			Element info = stock_deal_List.next();
			String stock_deal_date = info.select("td").eq(0).select("span").text();
			String stock_deal_data = info.select("td").eq(4).select("span").text();
			stock_deal_data = stock_deal_data.replaceAll(",", "");

			if (!stock_deal_data.equals("")) {
				String stock_deal_institution = info.select("td").eq(5).select("span").text();
				stock_deal_institution = stock_deal_institution.replaceAll(",", "");

				String stock_deal_foreign = info.select("td").eq(6).select("span").text();
				stock_deal_foreign = stock_deal_foreign.replaceAll(",", "");

				pDTO.setStock_deal_date(stock_deal_date);
				pDTO.setStock_deal_data(stock_deal_data);
				pDTO.setStock_deal_institution(stock_deal_institution);
				pDTO.setStock_deal_foreign(stock_deal_foreign);

				pList.add(pDTO);
				pDTO = null;
			}

		}

		return pList;
	}

	// 종목 뉴스
	@Override
	public List<CrewlingDTO> newsList(String stock_code) throws Exception {

		// 종목 뉴스
		String url = "https://finance.naver.com/item/news_news.nhn?code=" + stock_code
				+ "&sm=title_entity_id.basic&clusterId=&page=";
		Document newsDoc = null;

		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();

		for (int i = 1; i <= 1; i++) {

			url = url + Integer.toString(i);
			newsDoc = Jsoup.connect(url).get();

			Elements element = newsDoc.select("div.tb_cont > table.type5");

			Iterator<Element> newstitle = element.select("tbody tr td.title").iterator();
			Iterator<Element> newsinfo = element.select("tbody tr td.info").iterator();
			Iterator<Element> newsdate = element.select("tbody tr td.date").iterator();

			while (newstitle.hasNext()) {
				CrewlingDTO pDTO = new CrewlingDTO();

				Element titleInfo = newstitle.next();
				Element info = newsinfo.next();
				Element date = newsdate.next();

				String news_link = titleInfo.select("a").attr("href");
				String news_title = titleInfo.select("a").text();
				String news_writer = info.text();
				String news_time = date.text();

				log.info(i + "번 페이지");
				log.info("link : " + news_link);
				log.info("news_title : " + news_title);
				log.info("writer : " + news_writer);
				log.info("time : " + news_time);
				log.info("종목코드 : " + stock_code);

				pDTO.setNews_link(news_link);
				pDTO.setNews_title(news_title);
				pDTO.setNews_writer(news_writer);
				pDTO.setNews_time(news_time);
				pDTO.setStock_code(stock_code);

				// 종목 뉴스 내용
				String url1 = "https://finance.naver.com" + news_link;
				System.out.println();

				Document doc3 = null;
				doc3 = Jsoup.connect(url1).get();

				Elements NewContent_element = doc3.select("div#news_read");
				// System.out.println("stock_deal:"+stock_deal);

				// 종목 뉴스 내용
				String news_contents = NewContent_element.text();

				log.info("news_contents:" + news_contents);
				pDTO.setNews_contents(news_contents);

				pList.add(pDTO);

				// collection 이름
				String colNm = "News_" + DateUtil.getDateTime("yyyyMMdd");

				// mongodb 컬렉션 생성
				mongocrewlingmapper.CreateCollection(colNm);

				// mongodb 데이터저장하기
				mongocrewlingmapper.newsList(pList, colNm);

				pDTO = null;

			}

		}

		return pList;
	}

	// 동일업종 비교 회사
	@Override
	public List<String> same_company_List(String stock_code) throws Exception {

		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=" + stock_code;

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 동일 업종 비교
		// class =section trade_compare 에서 section은 클레스 이름이 아님
		Elements same_company_element = doc.select("div.trade_compare");

		// 동일 업종 이름
		Iterator<Element> same_company_List = same_company_element.select("table thead tr th").iterator();

		List<String> pList = new ArrayList<String>();

		// 동일 업종 비교
		while (same_company_List.hasNext()) {
			Element info = same_company_List.next();

			String same_company = info.select("a").text();
			// same_company =same_company.substring(0,same_company.length()-7);
			if (!same_company.equals("")) {

				log.info("동일 업종 :" + same_company);

				pList.add(same_company);

				same_company = null;
			}

		}
		return pList;
	}

	// 동일 업종 현재가
	@Override
	public List<String> same_present_price_List(String stock_code) throws Exception {

		// 현재가
		String url = "https://finance.naver.com/item/main.nhn?code=" + stock_code;

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 현재가
		Elements same_data_element = doc.select("div.trade_compare");

		// 현재가
		Iterator<Element> same_data_List = same_data_element.select("table tbody tr").eq(0).iterator();

		List<String> pList = new ArrayList<String>();
		while (same_data_List.hasNext()) {
			Element info = same_data_List.next();

			for (int i = 0; i < 5; i++) {
				String present_price = info.select("td").eq(i).text();// 현재가
				present_price = present_price.replaceAll(",", "");

				log.info("현재가:" + present_price);

				pList.add(present_price);
			}
		}

		return pList;
	}

	// 동일 업종 전일대비
	@Override
	public List<String> same_net_change_List(String stock_code) throws Exception {

		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=" + stock_code;

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 동일 업종 데이터
		Elements same_data_element = doc.select("div.trade_compare");

		// 동일 업종 데이터
		Iterator<Element> same_data_List = same_data_element.select("table tbody tr").eq(1).iterator();

		List<String> pList = new ArrayList<String>();
		while (same_data_List.hasNext()) {
			Element info = same_data_List.next();

			for (int i = 0; i < 5; i++) {
				String net_change = info.select("td").eq(i).text();// 전일대비
				log.info("전일대비:" + net_change);

				pList.add(net_change);
			}
		}

		return pList;
	}

	// 동일 업종 등락률
	@Override
	public List<String> same_price_change_List(String stock_code) throws Exception {

		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=" + stock_code;

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 동일 업종 데이터
		Elements same_data_element = doc.select("div.trade_compare");

		// 동일 업종 데이터
		Iterator<Element> same_data_List = same_data_element.select("table tbody tr").eq(2).iterator();

		List<String> pList = new ArrayList<String>();
		while (same_data_List.hasNext()) {
			Element info = same_data_List.next();

			for (int i = 0; i < 5; i++) {
				String price_change = info.select("td").eq(i).text();// 등락률
				price_change = price_change.replaceAll("상향", "");
				price_change = price_change.replaceAll("하향", "");
				price_change = price_change.replaceAll("%", "");

				log.info("전일대비:" + price_change);

				pList.add(price_change);
			}
		}

		return pList;
	}

	// 종목 토론실
	@Override
	public List<CrewlingDTO> stock_talk_List(String stock_code) throws Exception {
		// 종목 토론실
		String url2 = "https://finance.naver.com/item/board.nhn?code=" + stock_code;

		Document doc2 = null;
		doc2 = Jsoup.connect(url2).get();

		// 종목 토론실
		Elements stock_talk = doc2.select("table.type2");
		// System.out.println("stock_talk:" + stock_talk);

		// 종목 토론실
		List<Element> stock_talk_List = stock_talk.select("tbody tr");

		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();

		// 종목 토론실
		System.out.println("-종목 토론실-");
		for (int i = 0; i < stock_talk_List.size(); i++) {
			CrewlingDTO pDTO = new CrewlingDTO();

			Element info = stock_talk_List.get(i);
			String talk_date = info.select("td").eq(0).select("span").text();
			String talk_link = info.select("td").eq(1).select("a").attr("href");
			String talk_title = info.select("td").eq(1).select("a").text();
			String talk_id = info.select("td").eq(2).text();
			if (!talk_date.equals("")) {
				log.info("날짜 : " + talk_date);
				log.info("링크 : " + talk_link);
				log.info("제목 : " + talk_title);
				log.info("아이디 : " + talk_id);
				log.info("종목코드 : " + stock_code);

				pDTO.setTalk_date(talk_date);
				pDTO.setTalk_link(talk_link);
				pDTO.setTalk_title(talk_title);
				pDTO.setTalk_id(talk_id);
				pDTO.setStock_code(stock_code);

				// 종목 뉴스 내용
				String url1 = "https://finance.naver.com" + talk_link;

				Document doc3 = null;
				doc3 = Jsoup.connect(url1).get();

				Elements talkContent_element = doc3.select("div.view_se");
				// System.out.println("stock_deal:"+stock_deal);

				// 종목 뉴스 내용
				String talk_contents = talkContent_element.text();

				System.out.println("내용: " + talk_contents);

				pDTO.setTalk_contents(talk_contents);

				pList.add(pDTO);

				// collection 이름
				String colNm = "Talk_" + DateUtil.getDateTime("yyyyMMdd");
				
				// mongodb 컬렉션 생성
				mongocrewlingmapper.CreateCollection(colNm);

				// mongodb 데이터저장하기
				mongocrewlingmapper.stock_talk_List(pList, colNm);

				pDTO = null;
			}

		}

		return pList;
	}

	// 코스피 그래프
	@Override
	public List<CrewlingDTO> kospigrap() throws Exception {
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();

		for (int i = 1; i <= 2; i++) {
			// 투자자별 매매동향
			String url3 = "https://finance.naver.com/sise/sise_index_day.nhn?code=KOSPI&page=" + Integer.toString(i);
			Document doc3 = null;
			doc3 = Jsoup.connect(url3).get();

			// 투자자별 매매동향
			Elements stock_deal = doc3.select("table.type_1");
			// System.out.println("stock_deal:"+stock_deal);

			// 투자자별 매매동향
			Iterator<Element> stock_deal_List = stock_deal.select("tbody tr").iterator();

			// 투자자별 매매동향
			while (stock_deal_List.hasNext()) {

				CrewlingDTO pDTO = new CrewlingDTO();

				Element info = stock_deal_List.next();
				String kosdate = info.select("td").eq(0).text();
				String kosdata = info.select("td").eq(1).text();

				kosdata = kosdata.replaceAll(",", "");

				if (!kosdate.equals("")) {

					pDTO.setKosdate(kosdate);
					pDTO.setKosdata(kosdata);

					pList.add(pDTO);

					pDTO = null;
				}
			}
		}

		return pList;
	}

	// 코스닥 그래프
	@Override
	public List<CrewlingDTO> kosdaqgrap() throws Exception {
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();

		for (int i = 1; i <= 2; i++) {
			String url3 = "https://finance.naver.com/sise/sise_index_day.nhn?code=KOSDAQ&page=" + Integer.toString(i);
			Document doc3 = null;
			doc3 = Jsoup.connect(url3).get();

			Elements stock_deal = doc3.select("table.type_1");
			// System.out.println("stock_deal:"+stock_deal);

			Iterator<Element> stock_deal_List = stock_deal.select("tbody tr").iterator();

			while (stock_deal_List.hasNext()) {

				CrewlingDTO pDTO = new CrewlingDTO();

				Element info = stock_deal_List.next();
				String kosdate = info.select("td").eq(0).text();
				String kosdata = info.select("td").eq(1).text();

				if (!kosdate.equals("")) {
					pDTO.setKosdate(kosdate);
					pDTO.setKosdata(kosdata);

					pList.add(pDTO);

					pDTO = null;
				}
			}
		}
		return pList;
	}

	// 단어 빈도수
	@Override
	public List<CrewlingDTO> wordFrequency(String strToExtrtKwrd) throws Exception {

		log.info("wordFrequency start");

		KeywordExtractor ke = new KeywordExtractor();
		log.info("wordFrequency start");

		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);

		// 단어 빈도수 저장 List
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();

		for (int i = 0; i < kl.size(); i++) {
			CrewlingDTO pDTO = new CrewlingDTO();
			Keyword kwrd = kl.get(i);

			log.info("단어:" + kwrd.getString());
			log.info("단어 빈도수:" + kwrd.getCnt());

			String wordname = kwrd.getString();
			String wordcount = Integer.toString(kwrd.getCnt());

			pDTO.setWordname(wordname);
			pDTO.setWordcount(wordcount);

			pList.add(pDTO);
			pDTO = null;
		}
		log.info("wordFrequency end");
		return pList;
	}

	// 주식 현재가
	@Override
	public String currentstock(String stock_code) throws Exception {

		// 투자자별 매매동향
		String url3 = "https://finance.naver.com/item/sise.nhn?code=" + stock_code;
		Document doc3 = null;
		doc3 = Jsoup.connect(url3).get();

		// 투자자별 매매동향
		Elements stock_deal = doc3.select("table.type2");
		// System.out.println("stock_deal:"+stock_deal);

		// 투자자별 매매동향
		String crrentstock = stock_deal.select("tbody").select("tr").eq(0).select("td").eq(0).select("strong").text();

		log.info(crrentstock);

		return crrentstock;
	}

	@Override
	public List<CrewlingDTO> getMongonews(String stock_code) throws Exception {
		log.info("getMongonews start");
		String colNm = "News_" + DateUtil.getDateTime("yyyyMMdd");

		List<CrewlingDTO> rList = mongocrewlingmapper.NewsSelect(colNm, stock_code);

		if (rList == null) {
			rList = new ArrayList<CrewlingDTO>();
		}
		log.info("getMongonews end");
		return rList;
	}

	@Override
	public List<CrewlingDTO> getMongotalk(String stock_code) throws Exception {
		log.info("getMongotalk start");
		String colNm = "Talk_" + DateUtil.getDateTime("yyyyMMdd");

		List<CrewlingDTO> rList = mongocrewlingmapper.TalkSelect(colNm, stock_code);

		if (rList == null) {
			rList = new ArrayList<CrewlingDTO>();
		}
		log.info("getMongotalk end");
		return rList;
	}
	
	
	//최근 조회 삽입
	@Override
	public int stock_recent(CrewlingDTO pDTO) throws Exception {
		
		return crewlingMapper.stock_recent(pDTO);
	}
	
	//최근 조회 검색
	@Override
	public 	List<CrewlingDTO> stock_recent_select(String id) throws Exception {

		return crewlingMapper.stock_recent_select(id);
	}

	//최근 조회 업데이트
	@Override
	public int stock_recent_update(CrewlingDTO pDTO) throws Exception {

		return crewlingMapper.stock_recent_update(pDTO);
	}

	//최근 조회 검색 10개
	@Override
	public List<CrewlingDTO> stock_recent_select_5(String id) throws Exception {

		return crewlingMapper.stock_recent_select_5(id);
	}

	
	//종목 토론실 최근 조회 삽입
	@Override
	public int talk_recent(CrewlingDTO pDTO) throws Exception {

		return crewlingMapper.talk_recent(pDTO);
	}

	//종목 토론실 최근 조회 검색
	@Override
	public List<CrewlingDTO> talk_recent_select(String id) throws Exception {

		return crewlingMapper.talk_recent_select(id);
	}

	//종목 토론실 최근 조회 업데이트
	@Override
	public int talk_recent_update(CrewlingDTO pDTO) throws Exception {

		return crewlingMapper.talk_recent_update(pDTO);
	}

	//종목 토론실 최근 조회 검색 10개
	@Override
	public List<CrewlingDTO> talk_recent_select_5(String id) throws Exception {

		return crewlingMapper.talk_recent_select_5(id);
	}
	
	//유가 크롤링
	@Override
	public List<CrewlingDTO> stock_oil() throws Exception {
		
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		
		String url = "https://finance.naver.com/marketindex/?tabSel=gold#tab_section";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("table.tbl_exchange");
		//System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element stopstock = eList.next();
			CrewlingDTO pDTO = new CrewlingDTO();
			
			String oil_name = stopstock.select("td").eq(0).select("a").text();
			String oil_unit = stopstock.select("td").eq(1).text();
			String oil_present_price = stopstock.select("td").eq(2).text();
			String oil_price_change = stopstock.select("td").eq(4).text();
			String oil_standard_date = stopstock.select("td").eq(5).text();
			
			oil_present_price = oil_present_price.replaceAll(",", "");
			

			if (!oil_name.equals("")) {
				log.info("----------------------");
				log.info("유가 이름 : " + oil_name);
				log.info("단위: " + oil_unit);
				log.info("현재가: " + oil_present_price);
				log.info("등락율: " + oil_price_change);
				log.info("기준일: " + oil_standard_date);
				
				pDTO.setOil_name(oil_name);
				pDTO.setOil_unit(oil_unit);
				pDTO.setOil_present_price(oil_present_price);
				pDTO.setOil_price_change(oil_price_change);
				pDTO.setOil_standard_date(oil_standard_date);
				
				pList.add(pDTO);
				pDTO =null;
			}

		}
		return pList;
	}

	
	//거래정지 종목 크롤링
	@Override
	public List<CrewlingDTO> stock_stop() throws Exception {
		
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		
		String url = "https://finance.naver.com/sise/trading_halt.nhn";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("tbody");
		//System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element stopstock = eList.next();
			CrewlingDTO pDTO = new CrewlingDTO();
			String stop_num = stopstock.select("td").eq(0).text();
			String stop_name = stopstock.select("td").eq(1).select("a").text();
			String stop_date = stopstock.select("td").eq(2).text();
			String stop_cause = stopstock.select("td").eq(3).text();

			if (!stop_num.equals("")) {
				System.out.println("----------------------");
				System.out.println("거래정지 순번: " + stop_num);
				System.out.println("거래정지 종목: " + stop_name);
				System.out.println("거래정지 날짜: " + stop_date);
				System.out.println("거래정지 이유: " + stop_cause);
				
				pDTO.setStop_num(stop_num);
				pDTO.setStop_name(stop_name);
				pDTO.setStop_date(stop_date);
				pDTO.setStop_cause(stop_cause);
				
				pList.add(pDTO);
				pDTO=null;
			}

		}
		
		return pList;
	}

	//주식 테마 크롤링
	@Override
	public List<CrewlingDTO> stock_theme() throws Exception {
		
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		
		String url = "https://finance.naver.com/sise/theme.nhn";

		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("table.theme");
		//System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			Element stopstock = eList.next();
			CrewlingDTO pDTO = new CrewlingDTO();
			String theme_num = stopstock.select("td").eq(0).text();
			String theme_name = stopstock.select("td").eq(1).select("span").text();
			String theme_date = stopstock.select("td").eq(2).text();
			String theme_ju1= stopstock.select("td").eq(6).text();
			String theme_ju2 = stopstock.select("td").eq(7).text();

			if (!theme_name.equals("")) {
				System.out.println("----------------------");
				System.out.println("테마명: " + theme_num);
				System.out.println("전일대비: " + theme_name);
				System.out.println("등락율(3일 평균): " + theme_date);
				System.out.println("주도주 1: " + theme_ju1);
				System.out.println("주도주 2 : " + theme_ju2);
				
				pDTO.setTheme_num(theme_num);
				pDTO.setTheme_name(theme_name);
				pDTO.setTheme_date(theme_date);
				pDTO.setTheme_ju1(theme_ju1);
				pDTO.setTheme_ju2(theme_ju2);
				
				pList.add(pDTO);
				pDTO=null;
			}

		}
		
		return pList;
	}

	//해외 주요 증시 크롤링
	@Override
	public List<CrewlingDTO> stock_world() throws Exception {
		
		List<CrewlingDTO> pList = new ArrayList<CrewlingDTO>();
		
		String url = "https://kr.investing.com/indices/major-indices";
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("table.genTbl.closedTbl.elpTbl.elp20.crossRatesTbl");
		//System.out.println(element); 
		Iterator<Element> eList = element.select("tr").iterator();

		while (eList.hasNext()) {
			CrewlingDTO pDTO = new CrewlingDTO();
			
			Element stopstock = eList.next();
			String world_index = stopstock.select("td").eq(1).select("a").text();
			String world_final = stopstock.select("td").eq(2).text();
			String world_high = stopstock.select("td").eq(3).text();
			String world_low = stopstock.select("td").eq(4).text();
			if (!world_index.equals("")) {
				System.out.println("----------------------");
				System.out.println("지수 : " + world_index);
				System.out.println("종가 : " + world_final);
				System.out.println("고가 : " + world_high);
				System.out.println("저가 : " + world_low);
				
				world_final = world_final.replaceAll(",", "");
				world_high = world_high.replaceAll(",", "");
				world_low = world_low.replaceAll(",", "");
				
				pDTO.setWorld_index(world_index);
				pDTO.setWorld_final(world_final);
				pDTO.setWorld_high(world_high);
				pDTO.setWorld_low(world_low);
				
				pList.add(pDTO);
				pDTO=null;
				
			}

		}
		return pList;
	}
	
	
}
