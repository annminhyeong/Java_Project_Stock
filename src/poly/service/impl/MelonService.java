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
import org.springframework.stereotype.Service;

import poly.dto.MelonDTO;
import poly.persistance.mongo.IMelonMapper;
import poly.service.IMelonService;
import poly.util.DateUtil;

@Service("MelonService")
public class MelonService implements IMelonService {

	@Resource(name = "MelonMapper")
	private IMelonMapper melonMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public int collectMelonRank() throws Exception{
		log.info(this.getClass().getName() + ".collectMelonRank Start");
		
		int res = 0;
		
		List<MelonDTO> pList = new ArrayList<MelonDTO>();
		
		//멜론 정보 가져오는 페이지
		String url = "https://www.melon.com/chart/index.htm";
		
		//JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스 저장할 변수
		Document doc = null;
		
		doc = Jsoup.connect(url).get();
		
		//<div class="service_list_song">이 태그 내에서 있는 HTML소스만 element에 저장됨
		Elements element = doc.select("div.service_list_song");
		
		//Iterator를 사용해서 top100의 50위까지 순위정보 가져오기
		Iterator<Element> rank50List = element.select("tr.lst50").iterator();
		
		///순위는 1~50위까지 수집되니까 반복문으로 저장
		while (rank50List.hasNext()) {
			
			Element songInfo = rank50List.next();
			
			//크롤링
			String rank = songInfo.select("span.rank").text(); // 순위
			String song = songInfo.select("div.ellipsis a").eq(0).text(); // 노래 
			String singer = songInfo.select("div.ellipsis a").eq(1).text(); // 가수
			String album = songInfo.select("div.ellipsis a").eq(3).text(); // 앨범
		
			log.info("rank : " + rank);
			log.info("song : " + song);
			
			songInfo = null;
			
			//MongoDB에 저장할 List 형태의 맞는 DTO 데이터 저장
			MelonDTO pDTO = new MelonDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setRank(rank);
			pDTO.setSong(song);
			pDTO.setSinger(singer);
			pDTO.setAlbum(album);
		
			pList.add(pDTO);
			

		}
		String colNm = "MelonTOP100_" + DateUtil.getDateTime("yyyyMMdd"); //생성할 컬렉션 명
		
		melonMapper.createCollection(colNm);
		
		melonMapper.insertRank(pList, colNm);
		
		log.info(this.getClass().getName() + ".collectMelonRank End!");
		
		return res;
		
	}
	@Override
	public List<MelonDTO> getRank() throws Exception{
		log.info(this.getClass().getName() + ".getRank STart!");
		
		String colNm = "MelonTOP100_" + DateUtil.getDateTime("yyyyMMdd");
		
		List<MelonDTO> rList = melonMapper.getRank(colNm);
		
		if(rList == null) {
			rList = new ArrayList<MelonDTO>();
			
		}
		
		log.info(this.getClass().getName() + ".getRank End!");
		
		return rList;
	}
	
	
}
