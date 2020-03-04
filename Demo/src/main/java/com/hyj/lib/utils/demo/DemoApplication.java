package com.hyj.lib.utils.demo;

import android.app.Application;
import android.view.Gravity;

import com.hjq.toast.ToastUtils;
import com.hyj.lib.utils.UtilsInit;

/**
 * <pre>
 * </pre>
 * Author：hyj
 * Date：2020/3/1 15:34
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UtilsInit.init(this);
        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 150);
    }
}
