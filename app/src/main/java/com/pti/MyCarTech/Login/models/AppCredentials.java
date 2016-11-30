package com.pti.MyCarTech.Login.models;

/**
 * Created by fernando on 24-11-2016.
 */

public class AppCredentials {


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashed_token() {
        return hashed_token;
    }

    public void setHashed_token(String hashed_token) {
        this.hashed_token = hashed_token;
    }

    private String client_id;
    private String client_secret;
    private String note;
    private String scope;
    private String token;
    private String hashed_token;

    public AppCredentials(String client_id, String client_secret, String note) {

        this.client_id = client_id;
        this.client_secret = client_secret;
        this.note = note;

    }

    @Override
    public String toString() {
        return "AppCredentials{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", note='" + note + '\'' +
                ", scope='" + scope + '\'' +
                ", token='" + token + '\'' +
                ", hashed_token='" + hashed_token + '\'' +
                '}';
    }
}
