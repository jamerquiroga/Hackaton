package com.everis.hackaton.metropolitanoapp.model;

import com.everis.hackaton.metropolitanoapp.model.base.Card;
import com.everis.hackaton.metropolitanoapp.model.base.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public interface BusinessRemoteAccess {
    @GET("tarjetas")
    Call<List<Card>> listCards();

    @GET("usuario")
    Call<User> showUserForID(String id);
}
