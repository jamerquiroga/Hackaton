package com.everis.hackaton.metropolitanoapp.model;

import com.everis.hackaton.metropolitanoapp.model.base.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public interface BusinessRemoteAccess {
    @GET("users/{user}/repos")
    Call<List<Card>> listRepos(@Path("user") String user);
}
