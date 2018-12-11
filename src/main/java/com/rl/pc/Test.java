package com.rl.pc;

import com.rl.pc.bean.Keywords;
import com.rl.pc.bean.News;

import java.util.List;

public class Test {
    //热搜词总条数
    private static int num=10;
    public static void main(String[] args) {

        List<Keywords> keyrods = BdKeywords.getKeyWords();

        for (int i = 0; i < num; i++) {
            Keywords keyword = keyrods.get(i);

            List<News> news = TtNews.getNewsInfoByKey(keyword.getValue());
            keyword.setNews(news);

        }

        for (int i = 0; i < num; i++) {
            Keywords keyword = keyrods.get(i);
            System.out.println((i + 1) + "、" + keyword.getValue());
            List<News> news = keyword.getNews();

            System.out.println("     " + news.get(0).getAttr());
            System.out.println("  相关阅读：");
            for (News tem : news) {
                System.out.println("      " + tem.getTitle());
            }
        }
    }

}
