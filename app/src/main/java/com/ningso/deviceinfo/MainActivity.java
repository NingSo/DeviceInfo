package com.ningso.deviceinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.ningso.deviceinfo.entitys.AppProfile;
import com.ningso.deviceinfo.entitys.DataNetProfile;
import com.ningso.deviceinfo.entitys.DevProfile;
import com.ningso.deviceinfo.entitys.SimProfile;
import com.ningso.deviceinfo.entitys.WifiProfile;
import com.ningso.deviceinfo.utils.ProfileTool;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.tv_device);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AppInfoActivity.class));
            }
        });
        StringBuilder sb = new StringBuilder();
        ProfileTool profileTool = ProfileTool.getInstance();
        profileTool.init(this);
        AppProfile appProfile = profileTool.getAppProfile();
        sb.append(appProfile.toString()).append("\n");
        System.out.println(appProfile.toString());
        DevProfile devProfile = profileTool.getDevProfile();
        sb.append(devProfile.toString()).append("\n");
        System.out.println(devProfile.toString());
        SimProfile simProfile = profileTool.getSimProfile();
        sb.append(simProfile.toString()).append("\n");
        System.out.println(simProfile.toString());
        WifiProfile wifiProfile = profileTool.getWifiProfile();
        sb.append(wifiProfile.toString()).append("\n");
        System.out.println(wifiProfile.toString());
        DataNetProfile dataNetProfile = profileTool.getDataNetProfile();
        sb.append(dataNetProfile.toString()).append("\n");
        System.out.println(dataNetProfile.toString());
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(sb.toString());
    }
}
