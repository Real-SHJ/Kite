package com.wildbody.kite.Dto;

public class Article {

    private int articleid;
    private String company;
    private String title;
    private String summary;
    private String newspaper;
    private String publicationDate;
    private String url;
    private String image;
    private String content;
    private String keyword;

    public Article() {
        super();
    }

    public Article(int articleid, String company, String title, String summary, String newspaper,
        String publicationDate, String url, String image, String content, String keyword) {
        super();
        this.articleid = articleid;
        this.company = company;
        this.title = title;
        this.summary = summary;
        this.newspaper = newspaper;
        this.publicationDate = publicationDate;
        this.url = url;
        this.image = image;
        this.content = content;
        this.keyword = keyword;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(String newspaper) {
        this.newspaper = newspaper;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Article [articleid=" + articleid + ", company=" + company + ", title=" + title
            + ", summary=" + summary
            + ", newspaper=" + newspaper + ", publicationDate=" + publicationDate + ", url=" + url
            + ", image="
            + image + ", content=" + content + ", keyword=" + keyword + "]";
    }
}
