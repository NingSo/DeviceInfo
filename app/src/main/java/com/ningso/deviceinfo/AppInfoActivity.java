package com.ningso.deviceinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ningso.deviceinfo.adapter.AppInfoAdapter;
import com.ningso.deviceinfo.entitys.AppInfo;
import com.ningso.deviceinfo.utils.MD5;

import java.util.ArrayList;
import java.util.List;

public class AppInfoActivity extends AppCompatActivity {

    RecyclerView mRecyelerview;
    LinearLayoutManager linearLayoutManager;
    AppInfoAdapter mAdapter;
    ProgressDialog progressDialog;
    Context mContext;

    private List<AppInfo> mUserAppList = new ArrayList<>();
    private List<AppInfo> mSystemAppList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        mContext = this;
        mRecyelerview = (RecyclerView) findViewById(R.id.info_recycleview);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        mRecyelerview.setLayoutManager(linearLayoutManager);
        mRecyelerview.setItemAnimator(new DefaultItemAnimator());
        new AppInfoAtaTask().execute(false);
    }

    class AppInfoAtaTask extends AsyncTask<Boolean, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("正在加载");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Boolean... params) {
            getAppList(mContext);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            } else {
                mAdapter = new AppInfoAdapter(mContext, mUserAppList);
                mRecyelerview.setAdapter(mAdapter);
            }
        }
    }

    /**
     * 获取非系统应用信息列表
     */
    private void getAppList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_SIGNATURES | PackageManager.GET_META_DATA);
        for (PackageInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(context.getPackageName())) { // 过滤自己
                continue;
            }
            AppInfo appInfo = new AppInfo();
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                //非系统应用
                appInfo.setPackname(packageInfo.packageName);
                appInfo.setVersion(packageInfo.versionName);
                appInfo.setVersionCode(packageInfo.versionCode);
                appInfo.setApkPath(packageInfo.applicationInfo.sourceDir);
                appInfo.setAppicon(packageInfo.applicationInfo.loadIcon(packageManager));
                appInfo.setAppname(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                appInfo.setUserapp(true);
                appInfo.setSign(getSignMD5(getSign(this, packageInfo.packageName)));
                mUserAppList.add(appInfo);
            } else {
                //系统应用
                appInfo.setPackname(packageInfo.packageName);
                appInfo.setVersion(packageInfo.versionName);
                appInfo.setVersionCode(packageInfo.versionCode);
                appInfo.setApkPath(packageInfo.applicationInfo.sourceDir);
                appInfo.setAppicon(packageInfo.applicationInfo.loadIcon(packageManager));
                appInfo.setAppname(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                appInfo.setSign(getSignMD5(getSign(this, packageInfo.packageName)));
                appInfo.setUserapp(false);
                mSystemAppList.add(appInfo);
            }
            Log.e("@", appInfo.toString());
        }
    }

    public String getSignMD5(byte[] sign) {
        if (sign != null) {
            try {
                return MD5.hexdigest(sign);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] getSign(Context context, String pkgName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
            for (Signature toByteArray : packageInfo.signatures) {
                byte[] str = toByteArray.toByteArray();
                if (str != null) {
                    return str;
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_applist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_users) {
            mAdapter.setList(mUserAppList);
            mRecyelerview.scrollToPosition(0);
            return true;
        } else if (id == R.id.action_systems) {
            mAdapter.setList(mSystemAppList);
            mRecyelerview.scrollToPosition(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
