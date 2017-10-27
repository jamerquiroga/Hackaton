package com.everis.hackaton.metropolitanoapp.model;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class Card {
    public long id = 0;
    public double saldo = 0;
    public long ultimoIngreso = 0L;
    public TipoTarjeta tipoTarjeta = TipoTarjeta.General;

    public enum TipoTarjeta { // TODO: Esto deberia cargarse desde back tambien
        General(1), Universitario(2), Escolar(3);

        public final int value;
        TipoTarjeta(int type) {
            this.value = type;
        }
    }
}
