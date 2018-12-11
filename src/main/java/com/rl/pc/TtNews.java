package com.rl.pc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rl.pc.bean.News;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TtNews {
    //相关新闻条数
    public static int num=3;
    public static List<News> getNewsInfoByKey(String keyword) {
        JSONObject json = new JSONObject();
        List<News> list = new ArrayList<News>();
        try {
            //发送 GET 请求
            keyword = URLEncoder.encode(keyword, "utf-8");
            String info = HttpRequest.sendGet("https://www.toutiao.com/search_content?offset=0&format=json&autoload=true&count=50&cur_tab&keyword=" + keyword);
            //   System.out.print(News);
            json = JSONObject.parseObject(info, JSONObject.class);

            JSONArray data = json.getJSONArray("data");
            int n = 0;
            for (int i = 0; i < data.size(); i++) {
                JSONObject tem = data.getJSONObject(i);
                String title = tem.getString("title");
                String share_url = tem.getString("share_url");
                String attr = tem.getString("abstract");
                if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(share_url) && StringUtils.isNotBlank(attr)) {

                    if (n >= num) {

                        break;
                    } else {
                        //     getNewsDetals(share_url);
                     //   System.out.println(title + "   " + share_url);
                    //    System.out.println(attr);
                        News news = new News(title, share_url, attr);
                        list.add(news);
                    }
                    n++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static String getNewsDetals(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            Elements scripts = doc.getElementsByTag("script");
            for (Element script : scripts) {

                if (script.html().contains("var BASE_DATA = ")) //注意这里一定是html(), 而不是text()
                {
                    String str = script.html().replace("\n", ""); //这里是为了解决 无法多行匹配的问题
                    String pattern = "var BASE_DATA = \\{(.*?)\\},    shareInfo"; //()必须加，

                    Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);// Pattern.MULTILINE 好像没有什么用，所以才使用上面的replace
                    Matcher m = r.matcher(str);
                    if (m.find()) {
                        String option_1 = m.group(1);
                        System.out.println("{" + option_1 + "}");
                        JSONObject json = JSONObject.parseObject("{" + option_1 + "}}", JSONObject.class);
                        System.out.println(json);
                        System.out.println(json.getString("articleInfo"));
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ;

    public static void main(String[] args) {

    }
}
