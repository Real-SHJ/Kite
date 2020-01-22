package com.wildbody.kite.Dto;

import java.util.List;

public class Member {

    private int memberid;
    private String email;
    private String pw;
    private String lastname;
    private String firstname;
    private String birthday;
    private int gender;
    private String area;
    private String job;
    private String company;
    private String image;
    private List<Article> articleList;
    private List<String> keywordList;

    public Member() {
        super();
    }

    public Member(int memberid, String email, String pw, String lastname, String firstname,
        String birthday, int gender,
        String area, String job, String company, String image, List<Article> articleList,
        List<String> keywordList) {
        super();
        this.memberid = memberid;
        this.email = email;
        this.pw = pw;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.gender = gender;
        this.area = area;
        this.job = job;
        this.company = company;
        this.image = image;
        this.articleList = articleList;
        this.keywordList = keywordList;
    }

    public int getMemberId() {
        return memberid;
    }

    public void setMemberId(int memberid) {
        this.memberid = memberid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    @Override
    public String toString() {
        return "Member [memberid=" + memberid + ", email=" + email + ", pw=" + pw + ", lastname="
            + lastname + ", firstname="
            + firstname + ", birthday=" + birthday + ", gender=" + gender + ", area=" + area
            + ", job=" + job
            + ", company=" + company + ", image=" + image + ", articleList=" + articleList
            + ", keywordList="
            + keywordList + "]";
    }
}