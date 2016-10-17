package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 手机信息
 */
public class MobileProfile implements ICodeable {
    private String mobileApn;
    private String mobileIP;
    private String mobileName;

    public MobileProfile() {
    }

    public void decode(DataInputStream dataInputStream) throws IOException {
        this.mobileApn = dataInputStream.readUTF();
        this.mobileIP = dataInputStream.readUTF();
        this.mobileName = dataInputStream.readUTF();
    }

    public void encode(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.mobileApn);
        dataOutputStream.writeUTF(this.mobileIP);
        dataOutputStream.writeUTF(this.mobileName);
    }

    public void setMobileApn(String mobileApn) {
        this.mobileApn = mobileApn;
    }

    public String getMobileApn() {
        return this.mobileApn;
    }

    public void setMobileIP(String mobileIP) {
        this.mobileIP = mobileIP;
    }

    public String getMobileIP() {
        return this.mobileIP;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getMobileName() {
        return this.mobileName;
    }


    @Override
    public String toString() {
        return "手机信息:\n{" +
                "mobileApn='" + mobileApn + '\'' +
                ", mobileIP='" + mobileIP + '\'' +
                ", mobileName='" + mobileName + '\'' +
                '}';
    }
}
