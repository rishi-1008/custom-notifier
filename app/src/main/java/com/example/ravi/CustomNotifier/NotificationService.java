package com.example.ravi.CustomNotifier;

import android.content.Context;
import android.media.AudioManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationService extends NotificationListenerService {
    Context context;
    @Override
    public void onCreate() {
        context=getApplicationContext();
    }


    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        AudioManager manager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
        String pack = sbn.getPackageName();
        try{
            pack = getPackageManager().getApplicationInfo(pack,0).loadLabel(getPackageManager()).toString();
            if(appsListAdapter.selectedApps.containsKey(pack))
            {
                manager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,appsListAdapter.selectedApps.get(pack).getAppVolume(),AudioManager.MODE_NORMAL);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
