package com.rl.pc;

import com.rl.pc.bean.Keywords;
import com.rl.pc.bean.News;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Test {
    //热搜词总条数
    private static int num = 10;
    private static String img="<img src='imgurl'/>";
    public static void main(String[] args) {

        List<Keywords> keyrods = BdKeywords.getKeyWords();
        for (int i = 0; i < num; i++) {
            Keywords keyword = keyrods.get(i);
            List<News> news = TtNews.getNewsInfoByKey(keyword.getValue());
            keyword.setNews(news);

        }

        for (int i = 0; i < num; i++) {
            Keywords keyword = keyrods.get(i);
            System.out.println((i + 1) + "、" + keyword.getValue()+"</br>");
            List<News> news = keyword.getNews();

            System.out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + news.get(0).getAttr()+"</br>");
            if(StringUtils.isNotBlank(news.get(0).getImgUrl())){
                System.out.println(img.replace("imgurl", news.get(0).getImgUrl()) + "</br>");
            }
            System.out.println("&nbsp;&nbsp;相关阅读："+"</br>");
            for (News tem : news) {
                System.out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + tem.getTitle()+"</br>");
                System.out.println("&nbsp;&nbsp;&nbsp;&nbsp;" + tem.getShare_url().replace("group/","a").replace("http","https")+"</br>");
            }
        }
    }

}
