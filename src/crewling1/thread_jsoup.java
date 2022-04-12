package crewling1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class thread_jsoup extends Thread {
	public void run()
	{
		try 
		{
/////////////////////////////////////////////////////////////////////////////////// ���� ũ�Ѹ��� �ؽ�
			
			hash_title ha = new hash_title();
			String url = "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=001&aid=0011556091";
			
			Document doc = Jsoup.connect(url).header("Accept", "text/html, application/xhtml+xml,*/*").header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.header("Accept-Encoding", "gzip,delate").header("Accept-Language", "ko").header("Connection", "Keep-Alive").get();
			
			//System.out.println(doc);
			Elements head = doc.select("h3#articleTitle");
			String str_head = head.text();
			System.out.println(str_head);
			System.out.println(ha.hash_start(str_head));
			
			Elements body = doc.select("div#articleBodyContents");
			String str_body = body.text();
			System.out.println(str_body);
			
			/////////////////////////////////////////////////////////////////////////////////// KBO ��ŷ ũ�Ѹ�
			
			String url2 = "https://www.koreabaseball.com/TeamRank/TeamRank.aspx";
			Document doc2 = Jsoup.connect(url2).get();
			
			Elements rowElements = doc2.select("div#cphContents_cphContents_cphContents_udpRecord > table.tData > tbody > tr");
			
			for(Element row : rowElements)
			{
				Elements tdElements = row.getElementsByTag("td");
				System.out.format("%d %s", Integer.valueOf(tdElements.get(0).text()), tdElements.get(1).text());
				System.out.println();
			}
			
			
			///////////////////////////////////////////////////////////////////////////////// ��α� ũ�Ѹ�
			
			//프레임 부분을 어떻게 가져올 것이냐가 중점적
			
			String blog_url = "https://blog.naver.com/with_msip/221846679759"; //접속할 곳
			
			Document blog_doc = Jsoup.connect(blog_url).get(); // 전체를 가져옴
			
			/*
			 * iframe을 접근하기위한 준비단계. 접근위해 body에 먼저 들어가고, body안에 iframe두개 중 id가 mainFrame인거 까지 접근함.
			 * .first는 제일 첫번째에 나오는 요소. body.first()면 바디가 여러개면 그중에 첫번째로 접근할거고
			 * select().first()는 셀렉트 안의 요소가 여러개 나오면 그중에 첫번째를 접근하는것.
			 */
			Element iframeElement = blog_doc.select("body").first().select("iframe[id=mainFrame]").first(); 
			
			//iframe의 url중 src만 가져온다. attr을 그대로 활용.
			System.out.println(iframeElement.attr("src")); 
			String src = iframeElement.attr("src");		
			blog_doc = Jsoup.connect("https://blog.naver.com"+src).get();
			
			String blognum[] = src.split("&");
			String num[] = blognum[1].split("="); //로그넘버
			Elements blog_body = blog_doc.select("div#post-view"+num[1]);
			
			System.out.println(blog_body.text());
						
			///////////////////////////////////////////////////////////////////////////////// ������ ��α� ũ�Ѹ�
			
			
			// 주소 마지막 ' = ' 하고 뺀 이유는 페이지들 처리 하기위함.
			String naver_url = "https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&query=%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A7%88%EC%9D%B4%EB%8B%9D&sm=tab_pge&srchby=all&st=sim&where=post&start=";
			
			
			int naver_url_page = 1; //1페이지 부터
			while(naver_url_page < 21) //3페이지까지 가져오기
			{
			
				Document naver_blog_pagetotal = Jsoup.connect(naver_url+naver_url_page).get(); //네이버 주소 + 주소 페이지
				
				Elements blog_link = naver_blog_pagetotal.select("a.sh_blog_title._sp_each_url._sp_each_title"); // 셀렉트로 여기까지 접근 한 다음에 이것과 동일한 값을 가진 전체를 가져온다.
				
				for(Element element : blog_link)
				{
					String n = element.attr("href"); //href라고 써져있는게 있으면 가져온다.
					if(n.contains("blog.naver.com")) // 비교하기 -> 네이버 블로그만 가져오기. (티스토리같은거 제외) attribute를 네이버에 맞춰놨기때문에. 다음과 티스토리 등을 하고 싶으면 해당사이트 셀렉트 부분만 따로 만들어 주면 된다.
					{
						System.out.println(element.attr("href")); 
						
						blog_doc = Jsoup.connect(n).get();
						iframeElement = blog_doc.select("body").first().select("iframe[id=mainFrame]").first();
						System.out.println(iframeElement.attr("src"));
						src = iframeElement.attr("src");
						
						blog_doc = Jsoup.connect("https://blog.naver.com"+src).get();
						
						String blognum2[] = src.split("&"); 
						String num2[] = blognum2[1].split("=");
						blog_body = blog_doc.select("div#post-view"+num2[1]);
						
						System.out.println(blog_body.text());
					}
				}
				naver_url_page = naver_url_page + 10; //한페이지 넘기기 (블로그 개수 열개)
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
