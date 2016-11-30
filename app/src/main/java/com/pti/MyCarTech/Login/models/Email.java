package com.pti.MyCarTech.Login.models;

import java.util.ArrayList;

/**
 * Created by fernando on 29-11-2016.
 *
 * Model from https://developer.github.com/v3/users/emails/
 */

public class Email {

    private String email;
    private String verified;
    private String primary;

    public Email() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                ", verified='" + verified + '\'' +
                ", primary='" + primary + '\'' +
                '}';
    }
}
