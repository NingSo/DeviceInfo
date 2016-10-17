package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 设备信息
 */
public class DevProfile implements ICodeable {
    private String android_id = new String();
    private long buildTime;
    private String country = new String();
    private String deviceId = new String();
    private String language = new String();
    private String manufacture = new String();
    private String mobileModel = new String();
    private String osVersionCode = new String();
    private String osVersionName = new String();
    private String pixel = new String();

    public DevProfile() {
    }

    public DevProfile(String deviceId, String mobileModel, String manufacture, String osVersionName, String osVersionCode, long buildTime, String pixel, String language, String country, String android_id) {
        this.deviceId = deviceId;
        this.mobileModel = mobileModel;
        this.manufacture = manufacture;
        this.osVersionName = osVersionName;
        this.osVersionCode = osVersionCode;
        this.buildTime = buildTime;
        this.pixel = pixel;
        this.language = language;
        this.country = country;
        this.android_id = android_id;
    }

    public void decode(DataInputStream dataInputStream) throws IOException {
        this.deviceId = dataInputStream.readUTF();
        this.mobileModel = dataInputStream.readUTF();
        this.manufacture = dataInputStream.readUTF();
        this.osVersionName = dataInputStream.readUTF();
        this.osVersionCode = dataInputStream.readUTF();
        this.buildTime = dataInputStream.readLong();
        this.pixel = dataInputStream.readUTF();
        this.language = dataInputStream.readUTF();
        this.country = dataInputStream.readUTF();
        this.android_id = dataInputStream.readUTF();
    }

    public void encode(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.deviceId);
        dataOutputStream.writeUTF(this.mobileModel);
        dataOutputStream.writeUTF(this.manufacture);
        dataOutputStream.writeUTF(this.osVersionName);
        dataOutputStream.writeUTF(this.osVersionCode);
        dataOutputStream.writeLong(this.buildTime);
        dataOutputStream.writeUTF(this.pixel);
        dataOutputStream.writeUTF(this.language);
        dataOutputStream.writeUTF(this.country);
        dataOutputStream.writeUTF(this.android_id);
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getMobileModel() {
        return this.mobileModel;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getManufacture() {
        return this.manufacture;
    }

    public void setOsVersionName(String osVersionName) {
        this.osVersionName = osVersionName;
    }

    public String getOsVersionName() {
        return this.osVersionName;
    }

    public void setOsVersionCode(String osVersionCode) {
        this.osVersionCode = osVersionCode;
    }

    public String getOsVersionCode() {
        return this.osVersionCode;
    }

    public void setBuildTime(long buildTime) {
        this.buildTime = buildTime;
    }

    public long getBuildTime() {
        return this.buildTime;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getPixel() {
        return this.pixel;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAndroid_id() {
        return this.android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    @Override
    public String toString() {
        return "设备信息:\n{" +
                "android_id=" + android_id +
                ", \nbuildTime=" + buildTime +
                ", \ncountry=" + country +
                ", \ndeviceId=" + deviceId +
                ", \nlanguage=" + language +
                ", \nmanufacture=" + manufacture +
                ", \nmobileModel=" + mobileModel +
                ", \nosVersionCode=" + osVersionCode +
                ", \nosVersionName=" + osVersionName +
                ", \npixel=" + pixel +
                "\n}";
    }
}
