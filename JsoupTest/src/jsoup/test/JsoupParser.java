package jsoup.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupParser {
	public static void main(String[] args) throws Exception {
		
		
		// 박물관목록
		String pageUrl = "http://www.museum.or.kr/organ/organ_01.php?left_menu=3&sub_menu=1&MuseumIntroduce_kind=1";

		Document doc = Jsoup.connect(pageUrl).get();
		String title = doc.title();
		System.out.println("제목=" + title);

		// ////////// 박물관 목록 추출
		// 태그에는 table내 tbody가 존재하지만 파싱할때에는 무시하네
		// 그래서 선택자에서 tbody 가 빠진다.
		String selector2 = " table.PdTop5 tr";
		Elements rcwList = doc.select(selector2);
		System.out.println("박물관목록갯수=" + rcwList.size());
		// 목록 내용 추출
		for (int i = 0; i < rcwList.size(); i++) {
			Element li = rcwList.get(i);// tr 태그
			// 디자인을 위한 tr은 td가 1개뿐.
			Elements tdList = li.children();// TD 목록
			if (tdList.size() < 2)
				continue; // td가 1개만 있는 tr은 제외
			// 박물관 이름은 3번 td 태그에 존재
			System.out.print("이름=" + tdList.get(3).text());
			System.out.print(" 주소=" + tdList.get(5).text());
			System.out.println(" 전화=" + tdList.get(7).text());
		}// end for
	
	/*	
		Document doc = Jsoup.connect("http://blog.acronym.co.kr").get();
		Elements titles = doc.select(".title");
		
		//print all title in main page
		for(Element e : titles){
			System.out.println("text:"+e.text());
			System.out.println("html:"+e.html());
		}
		//print all available links on page
		Elements links = doc.select("a [href]");
		for(Element l : links){
			System.out.println("link:"+l.attr("abs:href"));
		}
	*/

		
		//		Document doc = Jsoup.connect("http://en.wikipedia.org").get();
//		Elements newsHeadLines = doc.select("#mp-itn b a");
//		for(Element e : newsHeadLines){
//			System.out.println("newHeadLines:"+e.text());
//		}
//		
		
		
		
		
	
	}// end main
}// end class
