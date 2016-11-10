package com.ningso.deviceinfo.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.PrintWriter;

/**
 * Created by NingSo on 16/3/13.下午10:49
 *
 * @author: NingSo
 * @Email: ningso.ping@gmail.com
 */
public class PackageUtils {

    /**
     * 系统卸载
     *
     * @param context
     * @param pkgname
     */
    public static void unInstallApp(Context context, String pkgname) {
        Uri packageUri = Uri.parse("package:" + pkgname);
        Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
        context.startActivity(intent);
    }

    /**
     * 静默卸载
     */
    public static boolean clientUninstall(String packageName) {
        PrintWriter PrintWriter;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("LD_LIBRARY_PATH=/vendor/lib:/system/lib ");
            PrintWriter.println("pm uninstall " + packageName);
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    private static boolean returnResult(int value) {
        // 代表成功
        if (value == 0) {
            return true;
        } else if (value == 1) { // 失败
            return false;
        } else { // 未知情况
            return false;
        }
    }

    /**
     * check Notification permission
     *
     * @param activity
     * @return
     */
    public static boolean checkNotificationReadPermission(Activity activity) {
        String notiStr = Settings.Secure.getString(activity.getContentResolver(), "enabled_notification_listeners");
        if (notiStr != null && !TextUtils.isEmpty(notiStr)) {
            final String[] names = notiStr.split(":");
            for (String name : names) {
                ComponentName cn = ComponentName.unflattenFromString(name);
                if (cn != null) {
                    if (activity.getPackageName().equals(cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
