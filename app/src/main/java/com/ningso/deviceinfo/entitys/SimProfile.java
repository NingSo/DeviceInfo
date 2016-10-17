package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * SIM信息
 */
public class SimProfile implements ICodeable {
    private String IMSI = new String();
    private String line1Number = new String();
    private String networkCountryIso = new String();
    private String networkOperator = new String();
    private String networkOperatorName = new String();
    private String networkType = new String();
    private String simOperatorName = new String();
    private String simOperatore = new String();
    private String simState = new String();

    public SimProfile() {
    }

    public void decode(DataInputStream dataInputStream) throws IOException {
        this.IMSI = dataInputStream.readUTF();
        this.line1Number = dataInputStream.readUTF();
        this.networkOperator = dataInputStream.readUTF();
        this.networkOperatorName = dataInputStream.readUTF();
        this.networkCountryIso = dataInputStream.readUTF();
        this.networkType = dataInputStream.readUTF();
        this.simOperatorName = dataInputStream.readUTF();
        this.simOperatore = dataInputStream.readUTF();
        this.simState = dataInputStream.readUTF();
    }

    public void encode(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.IMSI);
        dataOutputStream.writeUTF(this.line1Number);
        dataOutputStream.writeUTF(this.networkOperator);
        dataOutputStream.writeUTF(this.networkOperatorName);
        dataOutputStream.writeUTF(this.networkCountryIso);
        dataOutputStream.writeUTF(this.networkType);
        dataOutputStream.writeUTF(this.simOperatorName);
        dataOutputStream.writeUTF(this.simOperatore);
        dataOutputStream.writeUTF(this.simState);
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getIMSI() {
        return this.IMSI;
    }

    public void setLine1Number(String line1Number) {
        this.line1Number = line1Number;
    }

    public String getLine1Number() {
        return this.line1Number;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public String getNetworkOperator() {
        return this.networkOperator;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    public String getNetworkOperatorName() {
        return this.networkOperatorName;
    }

    public void setNetworkCountryIso(String networkCountryIso) {
        this.networkCountryIso = networkCountryIso;
    }

    public String getNetworkCountryIso() {
        return this.networkCountryIso;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getSimOperatorName() {
        return this.simOperatorName;
    }

    public void setSimOperatore(String simOperatore) {
        this.simOperatore = simOperatore;
    }

    public String getSimOperatore() {
        return this.simOperatore;
    }

    public void setSimState(String simState) {
        this.simState = simState;
    }

    public String getSimState() {
        return this.simState;
    }


    @Override
    public String toString() {
        return "SIM 卡信息:{" +
                "\nIMSI=" + IMSI +
                ", \nline1Number=" + line1Number +
                ", \nnetworkCountryIso=" + networkCountryIso +
                ", \nnetworkOperator=" + networkOperator +
                ", \nnetworkOperatorName=" + networkOperatorName +
                ", \nnetworkType=" + networkType +
                ", \nsimOperatorName=" + simOperatorName +
                ", \nsimOperatore=" + simOperatore +
                ", \nsimState=" + simState +
                "\n}";
    }
}
