package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 网络信息
 */
public class DataNetProfile implements ICodeable {

    private String dataNetworkType;
    private MobileProfile mobileProfile;
    private WifiProfile wifiProfile;

    public DataNetProfile() {
    }

    public void decode(DataInputStream dataInputStream) throws IOException {
        this.dataNetworkType = dataInputStream.readUTF();
        this.wifiProfile.decode(dataInputStream);
        this.mobileProfile.decode(dataInputStream);
    }

    public void encode(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.dataNetworkType);
        this.wifiProfile.encode(dataOutputStream);
        this.mobileProfile.encode(dataOutputStream);
    }

    public void setDataNetworkType(String dataNetworkType) {
        this.dataNetworkType = dataNetworkType;
    }

    public String getDataNetworkType() {
        return this.dataNetworkType;
    }

    public void setWifiProfile(WifiProfile wifiProfile) {
        this.wifiProfile = wifiProfile;
    }

    public WifiProfile getWifiProfile() {
        return this.wifiProfile;
    }

    public void setMobileProfile(MobileProfile mobileProfile) {
        this.mobileProfile = mobileProfile;
    }

    public MobileProfile getMobileProfile() {
        return this.mobileProfile;
    }

    @Override
    public String toString() {
        return "网络信息:{" +
                "\ndataNetworkType=" + dataNetworkType +
                ", \nmobileProfile=" + mobileProfile +
                "\n}";
    }
}
