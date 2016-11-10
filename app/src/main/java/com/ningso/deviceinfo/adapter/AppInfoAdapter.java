package com.ningso.deviceinfo.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ningso.deviceinfo.R;
import com.ningso.deviceinfo.entitys.AppInfo;
import com.ningso.deviceinfo.utils.PackageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NingSo on 16/3/10.下午11:16
 *
 * @author: NingSo
 * @Email: ningdev@163.com
 */
public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.AppViewHolder> {

    private List<AppInfo> mList = new ArrayList<>();
    private Context mContext;

    public AppInfoAdapter(Context context, List<AppInfo> mList) {
        this.mList = mList;
        this.mContext = context;
    }

    public void setList(List<AppInfo> list) {
        if (list != null && list.size() > 0) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        final AppInfo appInfo = mList.get(position);
        holder.appIcon.setImageDrawable(appInfo.getAppicon());
        holder.appName.setText(appInfo.getAppname());
        holder.appVersion.setText("Code: " + appInfo.getVersionCode() + " Name: " + appInfo.getVersion());
        holder.appSign.setText(String.format(mContext.getString(R.string.msg_app_sign), appInfo.getSign()));
        holder.apkPath.setText(String.format(mContext.getString(R.string.msg_app_path), appInfo.getApkPath()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "应用信息已复制到剪切板", Snackbar.LENGTH_SHORT).show();
                StringBuffer strInfo = new StringBuffer();
                strInfo.append("appName: ").append(appInfo.getAppname())
                        .append("\nVersionName: ").append(appInfo.getVersion())
                        .append("\nVersionCode: ").append(appInfo.getVersionCode())
                        .append("\nSign: ").append(appInfo.getSign());
                getCooper(strInfo.toString());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final int[] index = new int[1];
                new AlertDialog.Builder(mContext)
                        .setIcon(R.drawable.ic_icon)
                        .setTitle(String.format(mContext.getString(R.string.check_uninstallapp), appInfo.getAppname()))
                        .setSingleChoiceItems(R.array.options, 0, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                index[0] = which;
                            }
                        })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (index[0] == 0) {
                                    PackageUtils.unInstallApp(mContext, appInfo.getPackname());
                                } else if (index[0] == 1) {
                                    view.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Snackbar.make(view, "卸载" + appInfo.getAppname()
                                                            + (PackageUtils.clientUninstall(appInfo.getPackname()) ? "成功" : "失败"),
                                                    Snackbar.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    String appDir = "file://" + appInfo.getApkPath();
                                    Uri uri = Uri.parse(appDir);
                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                                    intent.setType("*/*");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(Intent.createChooser(intent, "发送"));
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancle, null).show();
                return false;
            }
        });
    }

    private void getCooper(String str) {
        ((ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText(null, str));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        ImageView appIcon;
        TextView appName;
        TextView appVersion;
        TextView appSign;
        TextView apkPath;

        AppViewHolder(View itemView) {
            super(itemView);
            appIcon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
            appName = (TextView) itemView.findViewById(R.id.tv_app_name);
            appVersion = (TextView) itemView.findViewById(R.id.tv_app_version);
            appSign = (TextView) itemView.findViewById(R.id.tv_app_sign);
            apkPath = (TextView) itemView.findViewById(R.id.tv_app_path);
        }
    }
}
