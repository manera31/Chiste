package com.joanmanera.chistedeldia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BootReceiver extends BroadcastReceiver {

    private static final String BOOT_RECEIVED = "android.intent.action.BOOT_COMPLETED";
    private static final String REBOOT_RECEIVED = "android.intent.action.QUICKBOOT_POWERON";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent("com.joanmanera.chistedeldia.ChisteService");
        intent1.setClass(context, ChisteService.class);
        context.startService(intent1);
        /*if (intent.getAction().equals(BOOT_RECEIVED)){

            Intent service = new Intent(context.getApplicationContext(), ChisteService.class);
            service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startService(service);

        }
        if (intent.getAction().equals(REBOOT_RECEIVED)){

            Intent service = new Intent(context.getApplicationContext(), ChisteService.class);
            service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startService(service);

        }*/
    }
}
