package com.everis.hackaton.metropolitanoapp.model;

import com.everis.hackaton.metropolitanoapp.model.base.Card;
import com.everis.hackaton.metropolitanoapp.model.base.User;

import java.util.Date;

import retrofit2.Retrofit;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class CardProvider {
    private BusinessRemoteAccess remote;
    private Card[] cardHolder = null;
    private int currentCard = -1;

    public boolean prepare(User user) { // TODO : Removed hardcoded part
        cardHolder = new Card[1];
        Card c = new Card();
        c.saldo = 10;
        c.ultimoIngreso = new Date().getTime();
        cardHolder[0] = c;

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
