package com.rl.pc.bean;

import java.util.List;

public class Keywords {

    private String value;
    private String num;
    private String attr;

    private List<News> news;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Keywords(String value, String num, String attr) {
        this.value = value;
        this.num = num;
        this.attr = attr;
    }

    public Keywords() {

    }
}
