package com.rl.pc.bean;

public class News {
    private String title;
    private String share_url;
    private String attr;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
    public News(){

    }
    public News(String title, String share_url, String attr) {
        this.title = title;
        this.share_url = share_url;
        this.attr = attr;
    }
}
