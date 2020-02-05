package com.joanmanera.chistedeldia;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ChisteService extends Service {

    private Thread hilo = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if (hilo == null || !hilo.isAlive()){
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    //TODO
                }
            });
            hilo.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
