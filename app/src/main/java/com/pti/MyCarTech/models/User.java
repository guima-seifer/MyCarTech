package com.pti.MyCarTech.models;

/**
 * Created by fernando on 29-11-2016.
 */

public class User {
    private String login;
    private String id;
    private String name;
    private String email;
    private String photo;
    private String url;
    private String html_url;

    public User(String login, String id, String name, String email, String photo, String url, String html_url) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
                ", avatar_url='" + photo + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                '}';
    }
}
