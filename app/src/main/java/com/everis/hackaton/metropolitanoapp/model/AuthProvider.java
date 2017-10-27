package com.everis.hackaton.metropolitanoapp.model;

import android.util.Log;

import com.everis.hackaton.metropolitanoapp.model.base.Card;
import com.everis.hackaton.metropolitanoapp.model.base.User;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class AuthProvider {
    private BusinessRemoteAccess remote;
    private User hardcodedUserBecauseWeKnowNoDarnLimit = new User();

    private void prepareWithImage() {
        // TODO
    }

    private boolean prepareWithDNI() {
        hardcodedUserBecauseWeKnowNoDarnLimit = new User();
        hardcodedUserBecauseWeKnowNoDarnLimit.setId("1213134134");
        hardcodedUserBecauseWeKnowNoDarnLimit.setApellidos("Quiroga");
        hardcodedUserBecauseWeKnowNoDarnLimit.setNombres("Jamer");

        Retrofit builder = new Retrofit.Builder().baseUrl("http://192.168.43.13:3000").build();
        remote = builder.create(BusinessRemoteAccess.class);

        try {
            Log.i("HCEDEMO", "Requesting card list");
            Response<User> response = remote.showUserForID("1213134134").execute();
            Log.i("HCEDEMO", "Requested card list");
            if (response.isSuccessful())
                hardcodedUserBecauseWeKnowNoDarnLimit = response.body();
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    public User getUser() {
        return hardcodedUserBecauseWeKnowNoDarnLimit;
    }
}
