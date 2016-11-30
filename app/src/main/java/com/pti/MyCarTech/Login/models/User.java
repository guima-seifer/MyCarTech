package com.pti.MyCarTech.Login.models;

/**
 * Created by fernando on 29-11-2016.
 */

public class User {
    private String login;
    private String id;
    private String name;
    private String email;
    private String avatar_url;
    private String url;
    private String html_url;

    public User(String login, String id, String name, String email, String avatar_url, String url, String html_url) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar_url = avatar_url;
        this.url = url;
        this.html_url = html_url;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                '}';
    }
}
