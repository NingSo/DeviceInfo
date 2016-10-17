package com.ningso.deviceinfo.entitys;

import android.graphics.drawable.Drawable;

public class AppInfo {

    private Drawable appicon;
    private String appname;
    private String apkPath;
    private String packname;
    private boolean userapp;
    private String version;
    private String sign;

    public Drawable getAppicon() {
        return appicon;
    }

    public void setAppicon(Drawable appicon) {
        this.appicon = appicon;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public boolean isUserapp() {
        return userapp;
    }

    public void setUserapp(boolean userapp) {
        this.userapp = userapp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appname='" + appname + '\'' +
                ", apkPath='" + apkPath + '\'' +
                ", packname='" + packname + '\'' +
                ", userapp=" + userapp +
                ", version='" + version + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
