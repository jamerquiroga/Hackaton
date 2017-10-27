package com.everis.hackaton.metropolitanoapp.cardemulation;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jhonatanavalos on 10/26/17.
 */

public class NFCCardService extends HostApduService {

    private AIDPermission currentPermission = AIDPermission.NONE;

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        switch (checkForAIDPermission(apdu)) {
            case READ:
                Log.i("HCEDEMO", "Tarjeta activada en modo de lectura");
                currentPermission = AIDPermission.READ;
                break;
            case WRITE:
                Log.i("HCEDEMO", "Tarjeta activada en modo de escritura");
                currentPermission = AIDPermission.WRITE;
                break;
            default:
            case NONE:
                if (currentPermission != AIDPermission.READ && currentPermission != AIDPermission.WRITE)
                    return "".getBytes();

                Log.i("HCEDEMO", "Mensaje recibido: " + new String(apdu));
                break;
        }
        return "".getBytes();
    }

    @Override
    public void onDeactivated(int reason) {
        Log.i("HCEDEMO", "Deactivated: " + reason);
    }

    private AIDPermission checkForAIDPermission(byte[] apdu) {
        if (apdu.equals(getReadAID())) {
            return AIDPermission.READ;
        }
        else if (apdu.equals(getWriteAID())) {
            return AIDPermission.WRITE;
        }
        else {return AIDPermission.NONE;}
    }

    private byte[] getReadAID () {
        return /*"0&#164;&#004;0*/"F0010203040506".getBytes();
    }

    private byte[] getWriteAID()  {
        return /*"0&#164;&#004;0*/"F0011223344556".getBytes();
    }

    private enum AIDPermission {
        NONE, READ, WRITE
    }

}
