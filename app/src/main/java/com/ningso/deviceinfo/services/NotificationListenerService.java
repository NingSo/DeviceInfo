package com.ningso.deviceinfo.services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by NingSo on 2016/11/10.下午2:05
 *
 * @author: NingSo
 * @Email: ningso.ping@gmail.com
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationListenerService extends android.service.notification.NotificationListenerService {

    //某应用产生通知
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
//        super.onNotificationPosted(sbn);
        Notification mNotification = sbn.getNotification();
        if (mNotification != null) {
            String packageName = sbn.getPackageName();
            if (packageName.equalsIgnoreCase("com.tencent.mm") || packageName.equalsIgnoreCase("com.tencent.mobileqq")) {
                // TODO: 2016/11/10 忽略
                Log.d(NotificationListenerService.class.getSimpleName(), "Notification:" + packageName);
            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                    cancelNotification(sbn.getKey());
                } else {
                    cancelNotification(sbn.getPackageName(), sbn.getTag(), sbn.getId());
                }
            }
        }
    }

    //当用户删除某条通知
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Notification mNotification = sbn.getNotification();
        String packageName = sbn.getPackageName();
    }
}
