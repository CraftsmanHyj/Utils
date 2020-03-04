package com.hyj.lib.utils.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.hyj.lib.utils.dialog.CusDialog;

/**
 * <pre>
 *     测试继承 {@link CusDialog} 写法
 * </pre>
 * Author：hyj
 * Date：2020/3/4 23:09
 */
public class TestDialog extends CusDialog {
    public TestDialog(@NonNull Context context, Builder builder) {
        super(context, builder);
    }

    public static ABuild builder(Context cxt) {
        return new TestDialog.ABuild(cxt);
    }

    public static class ABuild extends Builder {
        Context mContext;
        private OnSelSexListener onSelSexListener;

        private RadioButton boy;
        private RadioButton girl;

        public ABuild setOnSelSexListener(OnSelSexListener onSelSexListener) {
            this.onSelSexListener = onSelSexListener;
            if (null != onSelSexListener) {
                setPositiveListener(null);
            }
            return this;
        }

        public ABuild(Context cxt) {
            super(cxt);
            this.mContext = cxt;

            setCusView(getCusView());
        }

        private LinearLayout getCusView() {
            LinearLayout ll = new LinearLayout(mContext);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setGravity(Gravity.CENTER);

            boy = new RadioButton(mContext);
            boy.setText("男");
//            ll.addView(boy);

            girl = new RadioButton(mContext);
            girl.setText("女");
//            ll.addView(girl);

            RadioGroup rg = new RadioGroup(mContext);
            rg.addView(boy);
            rg.addView(girl);
            ll.addView(rg);

            return ll;
        }

        @Override
        public Builder setPositiveListener(OnClickListener positiveListener) {
            OnClickListener listener = new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (boy.isChecked()) {
                        onSelSexListener.onSelSex(boy.getText().toString());
                    } else if (girl.isChecked()) {
                        onSelSexListener.onSelSex(girl.getText().toString());
                    }
                }
            };
            return super.setPositiveListener(listener);
        }
    }

    /**
     * 性别选中事件
     */
    public interface OnSelSexListener {
        void onSelSex(String sexName);
    }
}
