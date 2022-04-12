
package crewling2; 

import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 

import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements; 

import poly.dto.NewsDTO; 

public class stockWorld2 { 
public static void main(String[] args) throws Exception { 

String url = "https://finance.naver.com/world/"; 
Document doc = null; 

doc = Jsoup.connect(url).get(); 

Elements element = doc.select("body"); 
System.out.println(element); 
Iterator<Element> eList = element.select("tr").iterator(); 

while (eList.hasNext()) { 
Element stopstock = eList.next(); 
String oil_name = stopstock.select("td").eq(0).select("span").text(); 
String oil_name1 = stopstock.select("td").eq(1).select("a").text(); 
String oil_name2 = stopstock.select("td").eq(2).select("span").text(); 
String oil_name3 = stopstock.select("td").eq(3).select("span").text(); 
String oil_name4 = stopstock.select("td").eq(4).select("span").text(); 

System.out.println("----------------------"); 
System.out.println("국가명 : "+ oil_name); 
System.out.println("지수명 : "+ oil_name1); 
System.out.println("현재가 : "+ oil_name2); 
System.out.println("전일대비 : "+ oil_name3); 
System.out.println("등락률 : "+ oil_name4); 



} 

} 
} 