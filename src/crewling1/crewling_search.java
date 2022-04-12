package crewling1;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crewling_search {

	public static void main(String[] args) throws Exception {
		// 메인페이지
		String url = "https://finance.naver.com/item/main.nhn?code=207940";

		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 종목 뉴스
		String url1 = "https://finance.naver.com/item/news_news.nhn?code=207940&sm=title_entity_id.basic&clusterId=&page=";
		Document newsDoc = null;
		
		
		for(int i = 1; i<=10;i++) {
			url1 = url1+Integer.toString(i);
			newsDoc = Jsoup.connect(url1).get();
			Elements element = newsDoc.select("div.tb_cont > table.type5");
			Iterator<Element> newstitle = element.select("tbody tr td.title").iterator();
			Iterator<Element> newsinfo = element.select("tbody tr td.info").iterator();
			Iterator<Element> newsdate = element.select("tbody tr td.date").iterator();
			while (newstitle.hasNext()) {
				Element titleInfo = newstitle.next();
				Element info = newsinfo.next();
				Element date = newsdate.next();
				
				String link = titleInfo.select("a").attr("href");
				String news_title = titleInfo.select("a").text();
				String writer = info.text();
				String time = date.text();
				
				System.out.println(i+"번 페이지");
				System.out.println("link : "+link);
				System.out.println("news_title : "+news_title);
				System.out.println("writer : "+writer);
				System.out.println("time : "+time);
			}
			
		}
		

		// 종목 토론실
		String url2 = "https://finance.naver.com/item/board.nhn?code=207940";

		Document doc2 = null;
		doc2 = Jsoup.connect(url2).get();

		// 투자자별 매매동향
		String url3 = "https://finance.naver.com/item/frgn.nhn?code=207940";

		Document doc3 = null;
		doc3 = Jsoup.connect(url3).get();

		// -------------------------------------------------------------------------------------------------
		// 코스피 인기종목
		Elements kospi_element = doc.select("div#fav_kospi");
		// System.out.println("test:"+kospi_element);
		// 코스닥 인기종목
		Elements kosdaq_element = doc.select("div#fav_kosdaq");

		// 동일 업종 비교
		// class =section trade_compare 에서 section은 클레스 이름이 아님
		Elements same_company_element = doc.select("div.trade_compare");

		// 동일 업종 데이터
		Elements same_data_element = doc.select("div.trade_compare");

		// 종목 토론실
		Elements stock_talk = doc2.select("table.type2");
		// System.out.println("stock_talk:"+stock_talk);

		// 투자자별 매매동향
		Elements stock_deal = doc3.select("table.type2");
		// System.out.println("stock_deal:"+stock_deal);

		// -------------------------------------------------------------------------------------------------
		// 코스피 인기종목
		Iterator<Element> kospi_List = kospi_element.select("table tbody tr").iterator();

		// 코스닥 인기종목
		Iterator<Element> kosdaq_List = kosdaq_element.select("table tbody tr").iterator();

		// 동일 업종 이름
		Iterator<Element> same_company_List = same_company_element.select("table thead tr th").iterator();

		// 동일 업종 데이터
		Iterator<Element> same_data_List = same_data_element.select("table tbody tr").iterator();

		// 종목 토론실
		Iterator<Element> stock_talk_List = stock_talk.select("tbody tr").iterator();

		// 투자자별 매매동향
		Iterator<Element> stock_deal_List = stock_deal.select("tbody tr").iterator();

		// -------------------------------------------------------------------------------------------------

		// 코스피 인기종목
		// System.out.println("-코스피 인기종목-");
		while (kospi_List.hasNext()) {
			Element info = kospi_List.next();
			String num = info.select("th > a").text();

			// System.out.println("코스피 종목 : "+num);
		}

		// 코스닥 인기종목
		// System.out.println("-코스닥 인기종목-");
		while (kosdaq_List.hasNext()) {
			Element info = kosdaq_List.next();
			String num = info.select("th a").text();

			// System.out.println("코스닥 종목 :"+num);
		}

		// 동일 업종 비교
		// System.out.println("-동일 업종 비교-");
		while (same_company_List.hasNext()) {
			Element info = same_company_List.next();
			String num = info.select("a").text();

			// System.out.println("동일 업종 :"+num);
		}

		// 동일 업종 데이터
		// System.out.println("-동일 업종 데이터-");
		while (same_data_List.hasNext()) {
			Element info = same_data_List.next();
			// get()과 eq()는 동일한 기능을 한다.

			String num1 = info.select("td").eq(0).text();
			String num2 = info.select("td").eq(1).text();
			String num3 = info.select("td").eq(2).text();
			String num4 = info.select("td").eq(3).text();
			String num5 = info.select("td").eq(4).text();

			/*
			 * System.out.println("----------------------------");
			 * System.out.println("동일업종 데이터 1번 컬럼:"+num1);
			 * System.out.println("동일업종 데이터 2번 컬럼:"+num2);
			 * System.out.println("동일업종 데이터 3번 컬럼:"+num3);
			 * System.out.println("동일업종 데이터 4번 컬럼:"+num4);
			 * System.out.println("동일업종 데이터 5번 컬럼:"+num5);
			 */

		}


		// 종목 토론실
		//System.out.println("-종목 토론실-");
		while (stock_talk_List.hasNext()) {
			Element info = stock_talk_List.next();
			String num = info.select("td").eq(0).select("span").text();
			String num2 = info.select("td").eq(1).select("a").attr("href");
			String num3 = info.select("td").eq(1).select("a").text();
			String num4 = info.select("td").eq(2).text();

			// System.out.println("날짜 : "+num);
			// System.out.println("링크 : "+num2);
			// System.out.println("제목 : "+num3);
			// System.out.println("아이디 : "+num4);
		}
		
		
		
		// 투자자별 매매동향
		//System.out.println("-투자자별 매매동향-");
		while (stock_deal_List.hasNext()) {
			Element info = stock_deal_List.next();
			String num = info.select("td").eq(0).select("span").text();
			String num2 = info.select("td").eq(4).select("span").text();
			String num3 = info.select("td").eq(5).select("span").text();
			String num4 = info.select("td").eq(6).select("span").text();

			// System.out.println("날짜 : "+num);
			// System.out.println("거래량 : "+num2);
			// System.out.println("기관 순매매량 : "+num3);
			// System.out.println("외국인 순매매량 : "+num4);
		}

	}
}
