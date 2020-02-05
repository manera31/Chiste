package com.joanmanera.chistedeldia;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChisteService extends Service {

    public static final String CHANNEL_ID = "CH_01";
    public static final int ID_ALERTA_NOTIFICACION = 1;
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

                    String chiste = "michiste";

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        CharSequence nombre = "Mi canal";
                        String descripcion = "Mi canal de notificación ";
                        int importancia = NotificationManager.IMPORTANCE_DEFAULT;
                        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nombre, importancia);
                        channel.setDescription(descripcion);
                        NotificationManager notificationManager = getSystemService(NotificationManager.class);
                        notificationManager.createNotificationChannel(channel);
                    }

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ChisteService.this, CHANNEL_ID)
                            .setSmallIcon(android.R.drawable.stat_sys_warning)
                            .setContentTitle("Chiste del día")
                            .setContentText(chiste)
                            .setContentInfo("4")
                            .setTicker("Alerta !!");
                    //Creamos el PendingIntent
                    Intent intent = new Intent (ChisteService.this, MainActivity.class);
                    intent.putExtra("chiste", chiste);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    //Asignamos el PendingIntent que será ejecutado al pulsar sobre la notificación
                    mBuilder.setContentIntent(pendingIntent);
                    //Finalmente mostrarmos la notificación
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(ID_ALERTA_NOTIFICACION, mBuilder.build());

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
