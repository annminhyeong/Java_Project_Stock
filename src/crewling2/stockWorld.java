package crewling2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import poly.dto.CrewlingDTO;
import poly.dto.NewsDTO;

public class stockWorld {
	public static void main(String[] args) throws Exception {

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
				
				pDTO.setWorld_index(world_index);
				pDTO.setWorld_final(world_final);
				pDTO.setWorld_high(world_high);
				pDTO.setWorld_low(world_low);
				
				
			}

		}

	}
}