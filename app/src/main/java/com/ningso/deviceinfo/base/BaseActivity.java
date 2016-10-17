package com.ningso.deviceinfo.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by NingSo on 16/3/10.下午10:52
 *
 * @author: NingSo
 * @Email: ningdev@163.com
 */
public class BaseActivity extends AppCompatActivity {

    protected boolean isNull(Object object) {
        return null == object;
    }

    protected boolean notNull(Object object) {
        return object != null;
    }


}
