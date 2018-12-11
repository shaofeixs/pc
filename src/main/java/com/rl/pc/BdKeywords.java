package com.rl.pc;

import com.rl.pc.bean.Keywords;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BdKeywords {
    private static String url = "http://top.baidu.com/buzz?b=1&c=513&fr=topbuzz_b1";

    public static List<Keywords> getKeyWords() {

        List<Keywords> list = new ArrayList<Keywords>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element content = doc.getElementsByClass("list-table").first();
            Elements links = content.getElementsByTag("tr");
            for (int i = 0; i < links.size(); i++) {
                Element link = links.get(i);
                if (!link.getElementsByClass("list-title").isEmpty()) {
                    String title = link.getElementsByClass("list-title").first().html();
                    String num = link.getElementsByClass("last").first().text();
                //    System.out.println(title + "     " + num);

                    Keywords keyword = new Keywords(title, num, null);
                    list.add(keyword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
