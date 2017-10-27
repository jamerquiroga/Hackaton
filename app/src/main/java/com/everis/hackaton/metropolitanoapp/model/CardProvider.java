package com.everis.hackaton.metropolitanoapp.model;

import java.util.Date;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class CardProvider {
    private Card[] cardHolder = null;
    private int currentCard = -1;

    public boolean prepare() { // TODO : Removed hardcoded part
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
