package com.pti.MyCarTech.Login.GitHubAuthentication;


import com.pti.MyCarTech.Login.models.AppCredentials;
import com.pti.MyCarTech.Login.models.Email;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by fernando on 24-11-2016.
 */

public interface LoginService {
    String LOGINDATA = "/user/emails";
    String TOKEN = "/authorizations";

    @GET(LOGINDATA)
    Call<List<Email>> fetchData();

    @POST(TOKEN)
    Call<AppCredentials> getAccessToken(@Body AppCredentials appCredentials);
}
