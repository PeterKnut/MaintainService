package com.example.peterknut.maintainservice;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveRoomConfig;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

import java.util.ArrayList;
import java.util.List;


public class JoinRoomActivity extends Activity implements IGuestRoomView, View.OnClickListener {
    private ImageView mMicCtrBtn, mCameraCtrBtn;
    private EditText mRoomIdText;
    private Button mJoinBtn, mQuitBtn;
    private boolean mMicEnable, mCameraEnable;
    private GuestRoomHelper helper;

    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        // 判断仅在主线程进行初始化
        if (MsfSdkUtils.isMainProcess(this)) {
            /**
             *
             * 初始化iLiveSDK
             * 此处请参考文档替换自己应用的SDKAPPID与ACCOUNTTYPE后进行测试
             *
             */
            ILiveSDK.getInstance().initSdk(this, 1400144103, 36862);
            // 初始化iLiveSDK房间管理模块
            ILiveRoomManager.getInstance().init(new ILiveRoomConfig());
            //初始化UI处理线程
            BackgroundTasks.initInstance();
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        checkPermission();
        setContentView(R.layout.activity_guest_room);
        initView();
    }


    /**
     * 初始化UI
     */
    private void initView() {

        mMicCtrBtn = findViewById(R.id.enable_mic);
        mMicCtrBtn.setOnClickListener(this);
        mCameraCtrBtn = findViewById(R.id.enable_camera);
        mCameraCtrBtn.setOnClickListener(this);
        mJoinBtn = findViewById(R.id.join_btn);
        mJoinBtn.setOnClickListener(this);
        mQuitBtn = findViewById(R.id.quit_btn);
        mQuitBtn.setOnClickListener(this);
        mRoomIdText = findViewById(R.id.ie_room_id);
        helper = new GuestRoomHelper(this);
        AVRootView avRootView = findViewById(R.id.av_root_view);
        helper.setRootView(avRootView);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join_btn:
                joinRoom();
                break;
            case R.id.quit_btn:
                helper.quitRoom();
                break;
            //开关麦克
            case R.id.enable_mic:
                mMicEnable = !mMicEnable;
                if (mMicEnable)
                    mMicCtrBtn.setImageResource(R.mipmap.ic_mic_on);
                else
                    mMicCtrBtn.setImageResource(R.mipmap.ic_mic_off);
                helper.enableMic(mMicEnable);
                break;
            //开关摄像头
            case R.id.enable_camera:
                mCameraEnable = !mCameraEnable;
                if (mCameraEnable)
                    mCameraCtrBtn.setImageResource(R.mipmap.ic_camera_on);
                else
                    mCameraCtrBtn.setImageResource(R.mipmap.ic_camera_off);
                helper.enableCamera(ILiveConstants.FRONT_CAMERA, mCameraEnable);
                break;
        }
    }

    /**
     * 加入房间
     */
    private void joinRoom() {
        //先判断用户是否已登录
        if (LoginHelper.isLogin()) {
            int roomId = 0;
            try {
                roomId = Integer.parseInt(mRoomIdText.getText().toString());
            } catch (Exception e) {

            }
            //验证房间号是否合法
            if (roomId > 0 && roomId <= 10000000)
                helper.joinRoom(roomId);
            else
                UIUtils.toastLongMessage("请输入合法的房间ID（1~10000000）");
        } else {
            UIUtils.toastLongMessage("您还未登录");
        }

    }


    public void onEnterRoom() {
        UIUtils.toastShortMessage("加入房间成功");
    }

    public void onEnterRoomFailed(String module, int errCode, String errMsg) {
        UIUtils.toastLongMessage("加入房间失败：" + errCode + "::::" + errMsg);
    }


    public void onQuitRoomSuccess() {
        UIUtils.toastShortMessage("退出房间成功");
    }

    public void onQuitRoomFailed(int errCode, String errMsg) {
        UIUtils.toastLongMessage("退出房间失败：" + errCode + "::::" + errMsg);
    }


    protected boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(this,
                        (String[]) permissions.toArray(new String[0]),
                        100);
                return false;
            }
        }

        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        helper.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.quitRoom();
    }
}
