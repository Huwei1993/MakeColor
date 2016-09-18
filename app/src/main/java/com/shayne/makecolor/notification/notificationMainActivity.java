package com.shayne.makecolor.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.shayne.makecolor.Message.SmessMainActivity;
import com.shayne.makecolor.R;

public class notificationMainActivity extends AppCompatActivity {

    private NotificationManager nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
         nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);    //   系统服务
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  void reminded(View view){
        //  “.” 链式调用   如果返回值是同一个类型就可以连续的“.”  高版本使用方法
//        Notification notification =  new Notification.Builder(notificationMainActivity.this)
//                .setContentTitle("我是通知的标题栏")    // 例如短信通知栏
//                .setContentText("短信的内容")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setWhen(System.currentTimeMillis())
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.iamge1))
//                .build();
        Intent intent  =  new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://120"));
        PendingIntent pendingIntent  = PendingIntent.getActivity(this,0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
        Notification.Builder builder = new Notification.Builder(this).setTicker("显示于屏幕顶端状态栏的文本")
                .setSmallIcon(R.mipmap.iamge1)
                .setContentTitle("我是通知的标题栏")
                .setContentText("短信内容")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())   //  获取系统当前时间

                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.iamge1));
        Notification notification = builder.getNotification();

        notification.flags = Notification.FLAG_AUTO_CANCEL;

        /**
         * Must be one or more of: PendingIntent.FLAG_ONE_SHOT, PendingIntent.FLAG_NO_CREATE, PendingIntent.FLAG_CANCEL_CURRENT, PendingIntent.FLAG_UPDATE_CURRENT, Intent.FILL_IN_ACTION, Intent.FILL_IN_DATA, Intent.FILL_IN_CATEGORIES, Intent.FILL_IN_COMPONENT, Intent.FILL_IN_PACKAGE, Intent.FILL_IN_SOURCE_BOUNDS, Intent.FILL_IN_SELECTOR, Intent.FILL_IN_CLIP_DATA
         */

        nm.notify(0,notification);


        /**
         * System.currentTimeMillis() 获取当前系统时间
         * 低版本使用方法（通常使用），向下兼容
//         */
//        //  Notification    notification =new Notification(R.mipmap.iamge1,"信息通知到了",System.currentTimeMillis());
//        NotificationCompat.Builder  compat = new NotificationCompat.Builder(this)
//                .setSmallIcon()




    }

    public void returnToSms(View view){
        Intent intent = new Intent(notificationMainActivity.this, SmessMainActivity.class);
        startActivity(intent);
        finish();
    }

}
