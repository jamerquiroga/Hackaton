package com.everis.hackaton.metropolitanoapp.model;

import com.everis.hackaton.metropolitanoapp.model.base.User;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class AuthProvider {
    private User hardcodedUserBecauseWeKnowNoDarnLimit = new User();

    private void prepareWithImage() {
        // TODO
    }

    private void prepare() {
        hardcodedUserBecauseWeKnowNoDarnLimit = new User();
        hardcodedUserBecauseWeKnowNoDarnLimit.id = 1213134134;
        hardcodedUserBecauseWeKnowNoDarnLimit.apellidos = "Quiroga";
        hardcodedUserBecauseWeKnowNoDarnLimit.nombres = "Jamer";
    }

    public User getUser() {
        return hardcodedUserBecauseWeKnowNoDarnLimit;
    }
}
