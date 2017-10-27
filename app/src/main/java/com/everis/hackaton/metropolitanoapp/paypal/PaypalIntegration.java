package com.everis.hackaton.metropolitanoapp.paypal;

import android.net.Uri;

import com.paypal.android.sdk.payments.PayPalConfiguration;

/**
 * Created by root on 10/27/17.
 */

public class PaypalIntegration {

    private static final String CONFIG_CLIENT_ID = "AadaOOdM8JkXYZna46arratWYSlaG7ic9G8pPlR2_cL_2FnS3q09su5BkbKwhDU92Ep0Cq-bE8rduKVZ";
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    public static final int REQUEST_CODE_PAYMENT = 1;



    public static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)

            // configuracion minima del cliente
            .merchantName("Mi tienda")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.mi_tienda.com/privacy"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.mi_tienda.com/legal"));
}
