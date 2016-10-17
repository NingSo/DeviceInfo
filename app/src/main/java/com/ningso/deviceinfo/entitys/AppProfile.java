package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 应用信息
 */
public class AppProfile implements ICodeable {
    private String appDisplayName;
    private String appFlag;
    private long appInstallTime;
    private String appPackageName;
    private String appTargetSdkVersion;
    private int appVersionCode;
    private String appVersionName;

    public AppProfile() {

    }

    public void decode(DataInputStream dataInputStream) throws IOException {
        this.appDisplayName = dataInputStream.readUTF();
        this.appPackageName = dataInputStream.readUTF();
        this.appVersionName = dataInputStream.readUTF();
        this.appVersionCode = dataInputStream.readInt();
        this.appInstallTime = dataInputStream.readLong();
        this.appTargetSdkVersion = dataInputStream.readUTF();
        this.appFlag = dataInputStream.readUTF();
    }

    public void encode(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.appDisplayName);
        dataOutputStream.writeUTF(this.appPackageName);
        dataOutputStream.writeUTF(this.appVersionName);
        dataOutputStream.writeInt(this.appVersionCode);
        dataOutputStream.writeLong(this.appInstallTime);
        dataOutputStream.writeUTF(this.appTargetSdkVersion);
        dataOutputStream.writeUTF(this.appFlag);
    }

    public void setAppDisplayName(String appDisplayName) {
        this.appDisplayName = appDisplayName;
    }

    public String getAppDisplayName() {
        return this.appDisplayName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public String getAppPackageName() {
        return this.appPackageName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionName() {
        return this.appVersionName;
    }

    public void setAppVersionCode(int appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public int getAppVersionCode() {
        return this.appVersionCode;
    }

    public void setAppInstallTime(long appInstallTime) {
        this.appInstallTime = appInstallTime;
    }

    public long getAppInstallTime() {
        return this.appInstallTime;
    }

    public void setAppTargetSdkVersion(String appTargetSdkVersion) {
        this.appTargetSdkVersion = appTargetSdkVersion;
    }

    public String getAppTargetSdkVersion() {
        return this.appTargetSdkVersion;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag;
    }

    public String getAppFlag() {
        return this.appFlag;
    }

    @Override
    public String toString() {
        return "应用信息:{" +
                " \nappFlag=" + appFlag +
                ", \nappInstallTime=" + appInstallTime +
                ", \nappPackageName=" + appPackageName +
                ", \nappTargetSdkVersion=" + appTargetSdkVersion +
                ", \nappVersionCode=" + appVersionCode +
                ", \nappVersionName=" + appVersionName +
                "\n}";
    }
}
