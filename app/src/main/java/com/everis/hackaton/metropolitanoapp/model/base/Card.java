package com.everis.hackaton.metropolitanoapp.model.base;

/**
 * Created by jhonatanavalos on 10/27/17.
 */

public class Card {
    public long id = 0;
    public double saldo = 0;
    public long ultimoIngreso = 0L;
    private int tipoTarjeta = 0;

    public enum TipoTarjeta { // TODO: Esto deberia cargarse desde back tambien
        General(1), Universitario(2), Escolar(3);

        public final int value;
        TipoTarjeta(int type) {
            this.value = type;
        }
    }

    public TipoTarjeta getTipoTarjeta () {
        switch (tipoTarjeta) {
            case 1: return TipoTarjeta.General;
            case 2: return TipoTarjeta.Universitario;
            default: return TipoTarjeta.Escolar;
        }
    }
}
