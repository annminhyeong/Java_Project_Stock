package crewling1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import poly.dto.NewsDTO;

public class stock_code {

	public static void main(String[] args) throws Exception {
		
		String url = "https://www.ktb.co.kr/trading/popup/itemPop.jspx";
		Document doc = null;
		List<NewsDTO> pList = new ArrayList<NewsDTO>();
		
		doc = Jsoup.connect(url).get();
		
		Elements element = doc.select("tbody.tbody_content");
		System.out.println(element);
		Iterator<Element> eList = element.select("tr").iterator();
		
		while(eList.hasNext()) {
			NewsDTO pDTO = new NewsDTO();
			Element codeInfo = eList.next();
			
			String stock_code = codeInfo.select("td").get(0).text();
			String stock_name = codeInfo.select("td a").text();
			
			pDTO.setStock_code(stock_code);
			pDTO.setStock_name(stock_name);
			
			//System.out.println("stock_name:"+stock_name);
			//System.out.println("stock_code:"+stock_code);
			
			pList.add(pDTO);
			pDTO=null;
		}
		
		for(int i=0; i<pList.size(); i++) {
			NewsDTO pDTO = new NewsDTO();
			
			String a = pList.get(i).getStock_code();
			String b = pList.get(i).getStock_name();
			
			//String a=pDTO.getStock_code();
			//String b=pDTO.getStock_name();
			
			System.out.println(pList.get(i));
			System.out.println(a);
			System.out.println(b);
		}
		

		
		

	}

}
