package com.hyj.lib.utils.demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.toast.ToastUtils;
import com.hyj.lib.permission.PermissionManager;
import com.hyj.lib.permission.callback.SimplePermissionCallback;
import com.hyj.lib.utils.demo.permission.PermEnum;
import com.hyj.lib.utils.dialog.CusDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tvCusTest)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testCusDialog();
                    }
                });
    }

    private void testCusDialog() {
        TestDialog.builder(this)
                .setOnSelSexListener(new TestDialog.OnSelSexListener() {
                    @Override
                    public void onSelSex(String sexName) {
                        ToastUtils.show(sexName);
                    }
                })
                .show();

//        Button button = new Button(this);
//        button.setText("测试");
//
//        CusDialog.builder(this)
//                .setTitle("测试")
//                .setMessage("18520303879")
//                .setMessageViewGravity(Gravity.RIGHT)
//                .setNegativeTip("取消")
//                .setNegativeListener(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtils.show("取消");
//                    }
//                })
//                .setPositiveTip("确定")
//                .setPositiveListener(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtils.show("确定");
//                    }
//                })
//                .show();
    }
}