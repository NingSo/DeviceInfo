package com.ningso.deviceinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ningso.deviceinfo.entitys.AppProfile;
import com.ningso.deviceinfo.entitys.DataNetProfile;
import com.ningso.deviceinfo.entitys.DevProfile;
import com.ningso.deviceinfo.entitys.SimProfile;
import com.ningso.deviceinfo.entitys.WifiProfile;
import com.ningso.deviceinfo.utils.PackageUtils;
import com.ningso.deviceinfo.utils.ProfileTool;

import static com.ningso.deviceinfo.R.id.fab;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.tv_device);
        setSupportActionBar(toolbar);

        mFloatingActionButton = (FloatingActionButton) findViewById(fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            if (!PackageUtils.checkNotificationReadPermission(this)) {
                Snackbar.make(mFloatingActionButton, getText(R.string.no_read_notification_promission), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getText(R.string.action_settings), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                                startActivity(intent);
                            }
                        })
                        .show();
            } else {
                Snackbar.make(mFloatingActionButton, getText(R.string.has_read_notification_promission), Snackbar.LENGTH_INDEFINITE).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
