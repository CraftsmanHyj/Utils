package com.hyj.lib.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hyj.lib.utils.R;

/**
 * <pre>
 *     自定义Dialog
 * </pre>
 *
 * @Author hyj
 * @Date 2017/10/24 15:37
 */
public class CusDialog extends Dialog {
    private Builder BUILDER;

    private View rootView;//根布局
    private TextView tvTitle;//标题
    private TextView tvMessage;//显示提示消息
    private LinearLayout llCusView;//自定义界面
    private LinearLayout llBtn;//按钮容器
    private Button btnNegative;//取消按钮
    private Button btnPositive;//确定按钮

    private CusDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.CusDialog);

        this.BUILDER = builder;
        myInit();
    }

    private void myInit() {
        initView();
        initCustomView();
        initViewData();
        initListener();
    }

    /**
     * 初始化View
     */
    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(BUILDER.layoutID, null);
        setContentView(rootView);

        tvTitle = rootView.findViewById(R.id.cusTvTitle);
        tvTitle.setVisibility(BUILDER.showTitle ? View.VISIBLE : View.GONE);

        llCusView = rootView.findViewById(R.id.cusLlContent);
        tvMessage = rootView.findViewById(R.id.cusTvMessage);
        tvMessage.setGravity(BUILDER.msgViewGravity);
        tvMessage.setAutoLinkMask(BUILDER.autoLinkMask);

        llBtn = rootView.findViewById(R.id.cusLlBtn);
        llBtn.setVisibility(BUILDER.showButton ? View.VISIBLE : View.GONE);
        llCusView.setBackgroundResource(BUILDER.showButton ? R.drawable.dialog_btn_top : R.drawable.dialog_btn);

        btnNegative = rootView.findViewById(R.id.cusBtnNegative);
        btnNegative.setVisibility(BUILDER.showNegativeButton ? View.VISIBLE : View.GONE);
        btnPositive = rootView.findViewById(R.id.cusBtnPositive);
        btnPositive.setBackgroundResource(BUILDER.showNegativeButton ? R.drawable.dialog_btn_right : R.drawable.dialog_btn_bottom);
    }

    /**
     * 设置自定义View
     */
    private void initCustomView() {
        if (null == BUILDER.cusView) {
            return;
        }

        tvMessage.setVisibility(View.GONE);
        llCusView.addView(BUILDER.cusView);
    }

    private void initViewData() {
        tvTitle.setText(BUILDER.title);
        tvMessage.setText(BUILDER.message);
        tvMessage.setGravity(BUILDER.msgViewGravity);
        tvMessage.setAutoLinkMask(BUILDER.autoLinkMask);
        tvMessage.setMovementMethod(LinkMovementMethod.getInstance());
        btnNegative.setText(BUILDER.negativeTip);
        btnPositive.setText(BUILDER.positiveTip);

        setCancelable(BUILDER.cancleAble);
        setCanceledOnTouchOutside(BUILDER.canceledOnTouchOutside);
        setOnDismissListener(BUILDER.dismissListener);
    }

    private void initListener() {
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != BUILDER.negativeListener) {
                    BUILDER.negativeListener.onClick(CusDialog.this, DialogInterface.BUTTON_NEGATIVE);
                }

                autoDismiss();
            }
        });

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != BUILDER.positiveListener) {
                    BUILDER.positiveListener.onClick(CusDialog.this, DialogInterface.BUTTON_POSITIVE);
                }

                autoDismiss();
            }
        });
    }

    /**
     * 隐藏dialog
     */
    private void autoDismiss() {
        if (BUILDER.autoDismiss) {
            dismiss();
        }
    }

    /**
     * 获取一个Builder构造对象
     *
     * @param cxt
     * @return
     */
    public static Builder builder(Context cxt) {
        return new CusDialog.Builder(cxt);
    }

    public static class Builder {
        private Context mContext;//上下文
        private int layoutID;//布局文件ID

        private View cusView;//自定义View

        private String title = "消息提示";//标题
        private boolean showTitle = true;//是否显示title

        private CharSequence message = "提示消息内容";//要显示的消息
        private int msgViewGravity = Gravity.CENTER; //消息view的内容布局
        private int autoLinkMask = Linkify.ALL;//是否自动识别所有连接
        private boolean autoDismiss = true;//界面是否自动消息
        private boolean cancleAble = true;//点击返回按钮是否可以被取消
        private boolean canceledOnTouchOutside = false;//点击dialog之外是否可以被取消

        private boolean showButton = true;//是否需要显示底部的按钮布局
        private String negativeTip = "取消";//取消按钮显示的文字
        private boolean showNegativeButton = true;//是否显示 取消按钮
        private String positiveTip = "确定";//确定按钮显示的文字
        private OnClickListener negativeListener;//取消事件
        private OnClickListener positiveListener;//确认事件
        private OnDismissListener dismissListener;//消失监听事件

        public Builder(Context cxt) {
            this.mContext = cxt;
            this.layoutID = R.layout.dialog_custom;//给layoutID一个默认值
        }

        /**
         * 设置新的布局文件：其中按钮的ID需要与R.layout.dialog_custom文件中的保持一致
         *
         * @param layoutID 文件ID：默认是R.layout.dialog_custom
         * @return
         */
        public Builder setLayoutID(int layoutID) {
            if (layoutID > 0) {
                this.layoutID = layoutID;
            }

            return this;
        }

        /**
         * 设置自定义布局
         *
         * @param cusView
         * @return
         */
        public Builder setCusView(View cusView) {
            this.cusView = cusView;
            return this;
        }

        /**
         * 设置提示标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * 设置是否显示标题
         *
         * @param showTitle
         * @return
         */
        public Builder setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
            return this;
        }

        /**
         * 设置提示信息
         *
         * @param message
         * @return
         */
        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        /**
         * 设置消息内容如何布局
         *
         * @param gravity
         * @return
         * @see Gravity
         */
        public Builder setMessageViewGravity(int gravity) {
            this.msgViewGravity = gravity;
            return this;
        }

        /**
         * 设置是否可以响应超链接
         *
         * @param autoLinkMask See {@link android.text.util.Linkify#ALL Linkify.ALL}
         */
        public Builder setAutoLinkMask(int autoLinkMask) {
            this.autoLinkMask = autoLinkMask;
            return this;
        }

        /**
         * 控制点击按钮之后，是否自动dismiss掉
         *
         * @param autoDismiss
         */
        public Builder setAutoDismiss(boolean autoDismiss) {
            this.autoDismiss = autoDismiss;
            return this;
        }

        /**
         * 点击返回按钮是否可以被取消
         *
         * @param cancleAble
         * @return
         */
        public Builder setCancleAble(boolean cancleAble) {
            this.cancleAble = cancleAble;
            return this;
        }

        /**
         * 点击dialog之外是否可以被取消
         *
         * @param canceledOnTouchOutside
         * @return
         */
        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        /**
         * 点击返回、dialog之外是否可以消失
         *
         * @param cancle
         * @return
         */
        public Builder setCancle(boolean cancle) {
            this.cancleAble = cancle;
            this.canceledOnTouchOutside = cancle;
            return this;
        }

        /**
         * 设置是否需要显示按钮布局
         *
         * @param showButton
         * @return
         */
        public Builder setShowButton(boolean showButton) {
            this.showButton = showButton;
            return this;
        }

        /**
         * 设置取消按钮的文字
         *
         * @param negativeTip
         * @return
         */
        public Builder setNegativeTip(String negativeTip) {
            this.negativeTip = negativeTip;
            return this;
        }

        /**
         * 是否需要显示 取消按钮
         *
         * @param showNegativeButton true：显示；false：不显示；
         * @return
         */
        public Builder showNegativeButton(boolean showNegativeButton) {
            this.showNegativeButton = showNegativeButton;
            return this;
        }

        /**
         * 设置确定按钮的文字
         *
         * @param positiveTip
         * @return
         */
        public Builder setPositiveTip(String positiveTip) {
            this.positiveTip = positiveTip;
            return this;
        }

        /**
         * 设置取消按钮事件监听
         *
         * @param negativeListener
         * @return
         */
        public Builder setNegativeListener(OnClickListener negativeListener) {
            this.negativeListener = negativeListener;
            return this;
        }

        /**
         * 设置确定按钮事件监听
         *
         * @param positiveListener
         * @return
         */
        public Builder setPositiveListener(OnClickListener positiveListener) {
            this.positiveListener = positiveListener;
            return this;
        }

        /**
         * 设置dialog消失时的监听事件
         *
         * @param dismissListener
         * @return
         */
        public Builder setOnDismissListener(OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }

        public CusDialog build() {
            return new CusDialog(mContext, this);
        }

        /**
         * 显示Dialog
         */
        public void show() {
            if (null != mContext) {
                build().show();
            }
        }
    }
}