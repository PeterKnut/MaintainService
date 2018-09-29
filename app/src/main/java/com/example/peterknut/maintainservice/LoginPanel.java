package com.example.peterknut.maintainservice;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * Created by valexhuang on 2018/4/12.
 *
 * 登录集成控件，此处不做过多说明，关于登录和创建房间等具体可参考第一章节
 */

public class LoginPanel extends LinearLayout implements ILoginView {

    private EditText accoutText;
    private Button loginBtn;
    private ImageView ctrBtn;
    private TextView loginSate;
    private View loginGroup;
    LoginHelper helper;

    public LoginPanel(Context context) {
        super(context);
        initView();
    }

    public LoginPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoginPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        helper = new LoginHelper(this);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_login_panel, this);

        accoutText = findViewById(R.id.panel_account_text);
        loginGroup = findViewById(R.id.login_group);

        ctrBtn = findViewById(R.id.panel_ctrl);
        ctrBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = loginGroup.getVisibility() == View.VISIBLE;
                playAnim(flag);
            }
        });

        loginBtn = findViewById(R.id.panel_login_btn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.login(getAccount());
            }
        });

        loginSate = findViewById(R.id.panel_login_state);
        if (LoginHelper.isLogin()) {
            accoutText.setText(LoginHelper.getCurrentAccount());
            updateLoginState(true);
        }
    }


    public void updateLoginState(boolean state) {
        String textLable = state ? "已登录" : "未登陆";
        int color = state ? Color.GREEN : Color.RED;
        loginSate.setText(textLable);
        loginSate.setTextColor(color);
        String buttonLabel = state ? "注销" : "登录";
        loginBtn.setText(buttonLabel);
    }

    @Override
    public void onLoginSDKSuccess() {
        updateLoginState(true);
    }

    @Override
    public void onLoginSDKFailed(String module, int errCode, String errMsg) {
        UIUtils.toastLongMessage("登录失败" + ":::" + errCode + "=" + errMsg);
    }

    @Override
    public void onLogoutSDKSuccess() {
        updateLoginState(false);
    }

    @Override
    public void onLogoutSDKFailed(String module, int errCode, String errMsg) {
        UIUtils.toastLongMessage("注销失败" + ":::" + errCode + "=" + errMsg);
    }

    public String getAccount() {
        return accoutText.getText().toString();
    }

    private void playAnim(final boolean flag) {
        RotateAnimation rotate;
        ScaleAnimation scale;
        if (flag) {
            rotate = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);

            scale = new ScaleAnimation(1, 1, 1, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        } else {
            rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);

            scale = new ScaleAnimation(1, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);

        }

        rotate.setFillAfter(true);
        rotate.setDuration(300);

        scale.setFillAfter(true);
        scale.setDuration(300);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (flag)
                    loginGroup.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ctrBtn.startAnimation(rotate);


        loginGroup.setVisibility(View.VISIBLE);
        loginGroup.startAnimation(scale);
    }

}
