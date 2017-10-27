package com.everis.hackaton.metropolitanoapp.cardemulation;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

import com.everis.hackaton.metropolitanoapp.model.AuthProvider;
import com.everis.hackaton.metropolitanoapp.model.base.Card;
import com.everis.hackaton.metropolitanoapp.model.CardProvider;

import java.util.Arrays;

/**
 * Created by jhonatanavalos on 10/26/17.
 */

public class NFCCardService extends HostApduService {

    private AIDPermission currentPermission = AIDPermission.NONE;
    private CardProvider cardProvider = new CardProvider();
    private AuthProvider userProvider = new AuthProvider();

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        Log.i("HCEDEMO", "Paquete recibido: " + new String(apdu));
        switch (checkForAIDPermission(apdu)) {
            case READ:
                Log.i("HCEDEMO", "Accediendo a la informacion de las tarjetas");
                if (!cardProvider.prepare(userProvider.getUser())) {
                    Log.i("HCEDEMO", "Error al intentar conectarse a la base de datos");
                    return null;
                }

                Log.i("HCEDEMO", "Tarjeta activada en modo de lectura");
                currentPermission = AIDPermission.READ;
                return "ok read".getBytes();
            case WRITE:
                Log.i("HCEDEMO", "Accediendo a la informacion de las tarjetas");
                if (!cardProvider.prepare(userProvider.getUser())) {
                    Log.i("HCEDEMO", "Error al intentar conectarse a la base de datos");
                    return null;
                }
                Log.i("HCEDEMO", "Tarjeta activada en modo de escritura");
                currentPermission = AIDPermission.WRITE;
                return "ok write".getBytes();
            default:
            case NONE:
                if (currentPermission == AIDPermission.NONE)
                    break;

                Log.i("HCEDEMO", "Instruccion: " + new String(apdu));
                return respondToCommand(new String(apdu)).getBytes();
        }
        Log.i("HCEDEMO", "Mensaje ignorado: " + new String(apdu));
        return "no".getBytes();
    }

    @Override
    public void onDeactivated(int reason) {
        currentPermission = AIDPermission.NONE;
        Log.i("HCEDEMO", "Tarjeta desactivada. Razon: " + (reason==0? "Se desconecto" : reason));
    }

    private static final byte[] CLA_INS_P1_P2 = { 0x00, (byte)0xA4, 0x04, 0x00 };
    private static final byte[] AID_READ = { (byte)0xF0, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06 };
    private static final byte[] AID_WRITE = { (byte)0xF0, 0x01, 0x12, 0x23, 0x34, 0x45, 0x56 };
    private byte[] createSelectAidApdu(byte[] aid) {
        byte[] result = new byte[6 + aid.length];
        System.arraycopy(CLA_INS_P1_P2, 0, result, 0, CLA_INS_P1_P2.length);
        result[4] = (byte)aid.length;
        System.arraycopy(aid, 0, result, 5, aid.length);
        result[result.length - 1] = 0;
        return result;
    }

    private AIDPermission checkForAIDPermission(byte[] apdu) {
        //Log.i("HCEDEMO", "Arr1: " + apdu.length + " Arr2: " + getReadAID().length);
        if (Arrays.equals(apdu, getReadAID()))
            return AIDPermission.READ;
        else if (Arrays.equals(apdu, getWriteAID()))
            return AIDPermission.WRITE;
        else return AIDPermission.NONE;
    }

    private byte[] getReadAID () {
        return createSelectAidApdu(AID_READ);
    }

    private byte[] getWriteAID()  {
        return createSelectAidApdu(AID_WRITE);
    }

    private enum AIDPermission {
        NONE, READ, WRITE
    }

    private String respondToCommand(String pComm) {
        if (currentPermission == AIDPermission.NONE) return "";
        if (pComm.equals("wait")) return "ok";

        String[] comm = pComm.split(" ");
        Card curCard = cardProvider.getSeletedCard();
        if (comm[0].equals("read") && currentPermission == AIDPermission.READ)
            switch (comm[1]) {
                case "saldo":
                    return Double.toString(curCard.saldo);
                case "id":
                    return Long.toString(curCard.id);
                case "ultimoIngreso":
                    return Long.toString(curCard.ultimoIngreso);
                case "idTipo":
                    return Integer.toString(curCard.getTipoTarjeta().value);
            }
        else if (comm[0].equals("write") && currentPermission == AIDPermission.WRITE) {
            String param = comm[2];
            switch (comm[1]) {
                case "saldo":
                    Log.i("HCEDEMO", "Saldo antes de cambiar el saldo: " + curCard.saldo);
                    curCard.saldo = Double.parseDouble(param);
                    Log.i("HCEDEMO", "Saldo luego de cambiar el saldo: " + curCard.saldo);
                    break;
                case "ultimoIngreso":
                    Log.i("HCEDEMO", "Ultimo ingreso: " + curCard.saldo);
                    curCard.ultimoIngreso = Long.parseLong(param);
                    Log.i("HCEDEMO", "Actualizacion al ultimo ingreso: " + curCard.saldo);
                    break;
            }
        }

        return "";
    }

}
