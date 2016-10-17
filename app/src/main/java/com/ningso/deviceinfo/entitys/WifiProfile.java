package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * WI-FI信息
 */
public class WifiProfile implements ICodeable {
    private String wifiAllowedAuthAlgorithms = new String();
    private String wifiAllowedGroupCiphers = new String();
    private String wifiAllowedKeyManagement = new String();
    private String wifiAllowedPairwiseCiphers = new String();
    private String wifiAllowedProtocols = new String();
    private String wifiBSSID = new String();
    private String wifiIpAddress = new String();
    private String wifiLinkSpeed = new String();
    private String wifiMacAddress = new String();
    private String wifiRssi = new String();
    private String wifiSSID = new String();

    public WifiProfile() {
    }

    public WifiProfile(String wifiBSSID, String wifiSSID, String wifiIpAddress, String wifiMacAddress, String wifiLinkSpeed, String wifiRssi, String wifiAllowedAuthAlgorithms, String wifiAllowedGroupCiphers, String wifiAllowedKeyManagement, String wifiAllowedPairwiseCiphers, String wifiAllowedProtocols) {
        this.wifiBSSID = wifiBSSID;
        this.wifiSSID = wifiSSID;
        this.wifiIpAddress = wifiIpAddress;
        this.wifiMacAddress = wifiMacAddress;
        this.wifiLinkSpeed = wifiLinkSpeed;
        this.wifiRssi = wifiRssi;
        this.wifiAllowedAuthAlgorithms = wifiAllowedAuthAlgorithms;
        this.wifiAllowedGroupCiphers = wifiAllowedGroupCiphers;
        this.wifiAllowedKeyManagement = wifiAllowedKeyManagement;
        this.wifiAllowedPairwiseCiphers = wifiAllowedPairwiseCiphers;
        this.wifiAllowedProtocols = wifiAllowedProtocols;
    }

    public void decode(DataInputStream dataInputStream) throws IOException {
        this.wifiBSSID = dataInputStream.readUTF();
        this.wifiSSID = dataInputStream.readUTF();
        this.wifiIpAddress = dataInputStream.readUTF();
        this.wifiMacAddress = dataInputStream.readUTF();
        this.wifiLinkSpeed = dataInputStream.readUTF();
        this.wifiRssi = dataInputStream.readUTF();
        this.wifiAllowedAuthAlgorithms = dataInputStream.readUTF();
        this.wifiAllowedGroupCiphers = dataInputStream.readUTF();
        this.wifiAllowedKeyManagement = dataInputStream.readUTF();
        this.wifiAllowedPairwiseCiphers = dataInputStream.readUTF();
        this.wifiAllowedProtocols = dataInputStream.readUTF();
    }

    public void encode(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.wifiBSSID);
        dataOutputStream.writeUTF(this.wifiSSID);
        dataOutputStream.writeUTF(this.wifiIpAddress);
        dataOutputStream.writeUTF(this.wifiMacAddress);
        dataOutputStream.writeUTF(this.wifiLinkSpeed);
        dataOutputStream.writeUTF(this.wifiRssi);
        dataOutputStream.writeUTF(this.wifiAllowedAuthAlgorithms);
        dataOutputStream.writeUTF(this.wifiAllowedGroupCiphers);
        dataOutputStream.writeUTF(this.wifiAllowedKeyManagement);
        dataOutputStream.writeUTF(this.wifiAllowedPairwiseCiphers);
        dataOutputStream.writeUTF(this.wifiAllowedProtocols);
    }

    public void setWifiBSSID(String wifiBSSID) {
        this.wifiBSSID = wifiBSSID;
    }

    public String getWifiBSSID() {
        return this.wifiBSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getWifiSSID() {
        return this.wifiSSID;
    }

    public void setWifiIpAddress(String wifiIpAddress) {
        this.wifiIpAddress = wifiIpAddress;
    }

    public String getWifiIpAddress() {
        return this.wifiIpAddress;
    }

    public void setWifiMacAddress(String wifiMacAddress) {
        this.wifiMacAddress = wifiMacAddress;
    }

    public String getWifiMacAddress() {
        return this.wifiMacAddress;
    }

    public void setWifiLinkSpeed(String wifiLinkSpeed) {
        this.wifiLinkSpeed = wifiLinkSpeed;
    }

    public String getWifiLinkSpeed() {
        return this.wifiLinkSpeed;
    }

    public void setWifiRssi(String wifiRssi) {
        this.wifiRssi = wifiRssi;
    }

    public String getWifiRssi() {
        return this.wifiRssi;
    }

    public void setWifiAllowedAuthAlgorithms(String wifiAllowedAuthAlgorithms) {
        this.wifiAllowedAuthAlgorithms = wifiAllowedAuthAlgorithms;
    }

    public String getWifiAllowedAuthAlgorithms() {
        return this.wifiAllowedAuthAlgorithms;
    }

    public void setWifiAllowedGroupCiphers(String wifiAllowedGroupCiphers) {
        this.wifiAllowedGroupCiphers = wifiAllowedGroupCiphers;
    }

    public String getWifiAllowedGroupCiphers() {
        return this.wifiAllowedGroupCiphers;
    }

    public void setWifiAllowedKeyManagement(String wifiAllowedKeyManagement) {
        this.wifiAllowedKeyManagement = wifiAllowedKeyManagement;
    }

    public String getWifiAllowedKeyManagement() {
        return this.wifiAllowedKeyManagement;
    }

    public void setWifiAllowedPairwiseCiphers(String wifiAllowedPairwiseCiphers) {
        this.wifiAllowedPairwiseCiphers = wifiAllowedPairwiseCiphers;
    }

    public String getWifiAllowedPairwiseCiphers() {
        return this.wifiAllowedPairwiseCiphers;
    }

    public void setWifiAllowedProtocols(String wifiAllowedProtocols) {
        this.wifiAllowedProtocols = wifiAllowedProtocols;
    }

    public String getWifiAllowedProtocols() {
        return this.wifiAllowedProtocols;
    }


    @Override
    public String toString() {
        return "WI-FI信息:{" +
                "\nwifiAllowedAuthAlgorithms=" + wifiAllowedAuthAlgorithms +
                ", \nwifiAllowedGroupCiphers=" + wifiAllowedGroupCiphers +
                ", \nwifiAllowedKeyManagement=" + wifiAllowedKeyManagement +
                ", \nwifiAllowedPairwiseCiphers=" + wifiAllowedPairwiseCiphers +
                ", \nwifiAllowedProtocols=" + wifiAllowedProtocols +
                ", \nwifiBSSID=" + wifiBSSID +
                ", \nwifiIpAddress=" + wifiIpAddress +
                ", \nwifiLinkSpeed=" + wifiLinkSpeed +
                ", \nwifiMacAddress=" + wifiMacAddress +
                ", \nwifiRssi=" + wifiRssi +
                ", \nwifiSSID=" + wifiSSID +
                "\n}";
    }
}
