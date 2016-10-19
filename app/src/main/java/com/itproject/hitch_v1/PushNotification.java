package com.itproject.hitch_v1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Locale;

/**
 * Created by jamesz on 10/14/2016.
 */

public class PushNotification extends FirebaseMessagingService {

    private TextToSpeech tts;
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.US);

                    if(result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS","This Language is not supported");
                    }
                    else {
                        speakout();
                    }
                }
                else{
                    Log.e("TTS","Initialization Failed");
                }
            }
        });
    }

    private void speakout(){

        String text = "John Doe wants to HITCH, say HIITCH if you want to Accept his request and no if you want to decline";

        tts.setSpeechRate(1);
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.e(TAG,"From: " + remoteMessage.getFrom());
        Log.e(TAG,"Notification Message Body: " + remoteMessage.getNotification().getBody());
        sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        speakout();

    }

    private void sendNotification(String title, String messageBody){
        Intent intent = new Intent(this,SearchResult.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 , notificationBuilder.build());

    }


}
