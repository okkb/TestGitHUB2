package jsoup.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupParser {
	public static void main(String[] args) throws Exception {
		
		
		// �ڹ������
		String pageUrl = "http://www.museum.or.kr/organ/organ_01.php?left_menu=3&sub_menu=1&MuseumIntroduce_kind=1";

		Document doc = Jsoup.connect(pageUrl).get();
		String title = doc.title();
		System.out.println("����=" + title);

		// ////////// �ڹ��� ��� ����
		// �±׿��� table�� tbody�� ���������� �Ľ��Ҷ����� �����ϳ�
		// �׷��� �����ڿ��� tbody �� ������.
		String selector2 = " table.PdTop5 tr";
		Elements rcwList = doc.select(selector2);
		System.out.println("�ڹ�����ϰ���=" + rcwList.size());
		// ��� ���� ����
		for (int i = 0; i < rcwList.size(); i++) {
			Element li = rcwList.get(i);// tr �±�
			// �������� ���� tr�� td�� 1����.
			Elements tdList = li.children();// TD ���
			if (tdList.size() < 2)
				continue; // td�� 1���� �ִ� tr�� ����
			// �ڹ��� �̸��� 3�� td �±׿� ����
			System.out.print("�̸�=" + tdList.get(3).text());
			System.out.print(" �ּ�=" + tdList.get(5).text());
			System.out.println(" ��ȭ=" + tdList.get(7).text());
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
