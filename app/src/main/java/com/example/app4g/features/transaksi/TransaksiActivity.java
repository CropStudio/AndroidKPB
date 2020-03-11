package com.example.app4g.features.transaksi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.app4g.MainActivity;
import com.example.app4g.NewMessageNotification;
import com.example.app4g.R;
import com.example.app4g.server.rmq.ReceiveRmq;
import com.example.app4g.server.rmq.SendRmq;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiActivity extends AppCompatActivity {
    @BindView(R.id.mBtn)
    Button mBtn;
    NewMessageNotification notif ;
    ReceiveRmq receiveRmq ;
    SendRmq sendRmq ;
    public static String[] stringArray = {"String1"};
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);
//        notif  = new NewMessageNotification();
//        mBtn.setOnClickListener(view -> notif.setNotify(this,"examplenotif" , 1));
        mBtn.setOnClickListener(view -> {
            try {
                SendRmq.main(stringArray);
                ReceiveRmq.main(stringArray);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        try {
//            ReceiveRmq.main(stringArray);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void showNotif() {
        String NOTIFICATION_CHANNEL_ID = "channel_androidnotif";
        Context context = this.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelName = "Android Notif Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent mIntent = new Intent(this, TransaksiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_kpb)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_kpb))
                .setTicker("notif starting")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("Notifikasi KPB")
                .setContentText("by imamfarisiwww.com");
        Random random = new Random();
//        int m = random.nextInt(9999 - 1000) + 1000;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(m, builder.build());
    }
}
