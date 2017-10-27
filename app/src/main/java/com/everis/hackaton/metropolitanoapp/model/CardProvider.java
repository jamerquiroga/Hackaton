package com.everis.hackaton.metropolitanoapp.model;

import android.util.Log;

import com.everis.hackaton.metropolitanoapp.model.base.Card;
import com.everis.hackaton.metropolitanoapp.model.base.User;

import java.util.Date;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class CardProvider {
    private BusinessRemoteAccess remote;
    private Card[] cardHolder = null;
    private int currentCard = -1;

    public boolean prepare(User user) { // TODO : Removed hardcoded part
        Retrofit builder = new Retrofit.Builder().baseUrl("http://192.168.43.13:3000").build();
        remote = builder.create(BusinessRemoteAccess.class);

        try {
            Log.i("HCEDEMO", "Requesting card list");
            Response<List<Card>> response = remote.listCards().execute();
            Log.i("HCEDEMO", "Requested card list");
            if (response.isSuccessful())
                cardHolder = (Card[]) response.body().toArray();
        } catch (Exception ex) {
            return false;
        }

        /*cardHolder = new Card[1];
        Card c = new Card();
        c.saldo = 10;
        c.ultimoIngreso = new Date().getTime();
        cardHolder[0] = c;*/

        currentCard = 0;

        return true; // Ready
    }

    public Card[] getCards() {
        return cardHolder;
    }

    public Card getSeletedCard() {
        return  cardHolder[currentCard];
    }

    public void setSelectedCard(int cardPos) {
        currentCard = cardPos;
    }
}
