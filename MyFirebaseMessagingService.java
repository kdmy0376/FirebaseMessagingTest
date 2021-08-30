package dw.koo.android.firebase_messaging_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("FCM onMessageReceived", remoteMessage.getData().toString());
        //Log.d("FCM onMessageReceived", "Title : " +remoteMessage.getNotification().getTitle());
        //Log.d("FCM onMessageReceived", "Message : " +remoteMessage.getNotification().getBody());
        if (remoteMessage != null && remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage);
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {

        //String title = remoteMessage.getNotification().getTitle();
        //String message = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        final String CHANNEL_ID = "ChannerID";
        NotificationManager mManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final String CHANNEL_NAME = "ChannerName";
            final int importance = NotificationManager.IMPORTANCE_HIGH;

            // add in API level 26
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentTitle(title);
        builder.setContentText(message);
        mManager.notify(0, builder.build());
    }

    @Override
    public void onNewToken(String s) {
        Log.d("FCM onNewToken", "Token : " +s);
        super.onNewToken(s);
    }

}