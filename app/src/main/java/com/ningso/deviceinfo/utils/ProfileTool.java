package com.ningso.deviceinfo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.ningso.deviceinfo.entitys.AppProfile;
import com.ningso.deviceinfo.entitys.DataNetProfile;
import com.ningso.deviceinfo.entitys.DevProfile;
import com.ningso.deviceinfo.entitys.MobileProfile;
import com.ningso.deviceinfo.entitys.SimProfile;
import com.ningso.deviceinfo.entitys.WifiProfile;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class ProfileTool {
    private static ProfileTool instance;
    private Context mContext;

    public static ProfileTool getInstance() {
        if (instance == null) {
            instance = new ProfileTool();
        }
        return instance;
    }

    private ProfileTool() {
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public AppProfile getAppProfile() {
        try {
            AppProfile appProfile = new AppProfile();
            String pkName = this.mContext.getPackageName();
            PackageManager pm = this.mContext.getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo(pkName, 0);
            appProfile.setAppDisplayName(pInfo.applicationInfo.loadLabel(pm).toString());
            appProfile.setAppPackageName(pkName);
            appProfile.setAppVersionName(pInfo.versionName);
            appProfile.setAppVersionCode(pInfo.versionCode);
            appProfile.setAppInstallTime(pInfo.firstInstallTime);
            appProfile.setAppTargetSdkVersion(String.valueOf(pInfo.applicationInfo.targetSdkVersion));
            appProfile.setAppFlag(String.valueOf(pInfo.applicationInfo.flags));
            return appProfile;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SimProfile getSimProfile() {
        String subscriberId;
        SimProfile simProfile = new SimProfile();
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getSubscriberId() != null) {
            subscriberId = tm.getSubscriberId();
        } else {
            subscriberId = "";
        }
        simProfile.setIMSI(subscriberId);
        if (tm.getLine1Number() == null) {
            simProfile.setLine1Number("");
        } else {
            simProfile.setLine1Number(tm.getLine1Number());
        }
        simProfile.setNetworkOperator(tm.getNetworkOperator());
        simProfile.setNetworkOperatorName(tm.getNetworkOperatorName());
        simProfile.setNetworkCountryIso(tm.getNetworkCountryIso());
        simProfile.setNetworkType(String.valueOf(tm.getNetworkType()));
        simProfile.setSimOperatore(tm.getSimOperator());
        simProfile.setSimOperatorName(tm.getSimOperatorName());
        simProfile.setSimState(String.valueOf(tm.getSimState()));
        return simProfile;
    }

    public WifiProfile getWifiProfile() {
        WifiProfile wifiProfile = new WifiProfile();
        WifiManager wm = (WifiManager) this.mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifi = wm.getConnectionInfo();
        List<WifiConfiguration> wifiConfigurationList = wm.getConfiguredNetworks();
        if (wifi != null) {
            wifiProfile.setWifiBSSID(wifi.getBSSID());
            wifiProfile.setWifiSSID(wifi.getSSID());
            wifiProfile.setWifiIpAddress(String.valueOf(wifi.getIpAddress()));
            wifiProfile.setWifiMacAddress(wifi.getMacAddress());
            wifiProfile.setWifiLinkSpeed(String.valueOf(wifi.getLinkSpeed()));
            wifiProfile.setWifiRssi(String.valueOf(wifi.getRssi()));
            if (wifiConfigurationList != null) {
                for (WifiConfiguration wf : wifiConfigurationList) {
                    if (delQuotationMarks(wf.SSID).equals(delQuotationMarks(wifi.getSSID()))) {
                        wifiProfile.setWifiAllowedAuthAlgorithms(wf.allowedAuthAlgorithms.toString());
                        wifiProfile.setWifiAllowedGroupCiphers(wf.allowedGroupCiphers.toString());
                        wifiProfile.setWifiAllowedKeyManagement(wf.allowedKeyManagement.toString());
                        wifiProfile.setWifiAllowedGroupCiphers(wf.allowedGroupCiphers.toString());
                        wifiProfile.setWifiAllowedProtocols(wf.allowedProtocols.toString());
                    }
                }
            }
        }
        return wifiProfile;
    }

    public MobileProfile getMobileProfile(NetworkInfo info) {
        MobileProfile mobileProfile = new MobileProfile();
        try {
            mobileProfile.setMobileApn(info.getExtraInfo());
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface ni = en.nextElement();
                String dm = ni.getDisplayName();
                byte[] address = ni.getHardwareAddress();
                Enumeration<InetAddress> inetAddress = ni.getInetAddresses();
                List<InterfaceAddress> interfaceAddress = ni.getInterfaceAddresses();
                int mtu = ni.getMTU();
                String name = ni.getName();
                if (inetAddress != null && inetAddress.hasMoreElements()) {
                    InetAddress ia = inetAddress.nextElement();
                    String strIa = ia.toString();
                    if (!(strIa.contains("127.0.0.1") || strIa.contains("::1%1%1"))) {
                        mobileProfile.setMobileIP(ia.toString());
                        mobileProfile.setMobileName(name);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return mobileProfile;
    }

    public DataNetProfile getDataNetProfile() {
        DataNetProfile dataNetProfile = new DataNetProfile();
        ConnectivityManager connectivity = (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                dataNetProfile.setDataNetworkType(info.getTypeName());
                switch (info.getType()) {
                    case 0:
                        dataNetProfile.setMobileProfile(getMobileProfile(info));
                        break;
                    case 1:
                        dataNetProfile.setWifiProfile(getWifiProfile());
                        break;
                }
            }
        }
        return dataNetProfile;
    }

    public DevProfile getDevProfile() {
        DevProfile devProfile = new DevProfile();
        devProfile.setDeviceId(((TelephonyManager) this.mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        devProfile.setManufacture(Build.MANUFACTURER);
        devProfile.setMobileModel(Build.MODEL);
        devProfile.setOsVersionName(VERSION.RELEASE);
        devProfile.setOsVersionCode(String.valueOf(VERSION.SDK_INT));
        devProfile.setBuildTime(Build.TIME);
        DisplayMetrics dm = new DisplayMetrics();
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
            devProfile.setPixel(dm.widthPixels + "*" + dm.heightPixels + "*" + dm.densityDpi);
        }
        devProfile.setLanguage(Locale.getDefault().getLanguage());
        devProfile.setCountry(Locale.getDefault().getCountry());
        devProfile.setAndroid_id(Secure.getString(this.mContext.getContentResolver(), "android_id"));
        return devProfile;
    }

    public String delQuotationMarks(String data) {
        if (data == null || data.length() <= 2 || !data.startsWith("\"") || !data.endsWith("\"")) {
            return data;
        }
        return data.substring(1, data.length() - 1);
    }
}
