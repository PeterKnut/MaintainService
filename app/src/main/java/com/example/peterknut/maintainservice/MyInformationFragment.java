package com.example.peterknut.maintainservice;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyInformationFragment extends Fragment {

    // TODO: 2018/9/26 对该页面进行初始化 
    //按钮
    private Button myNameButton;
    private Button historyOrderButton;
    private Button aboutTianduanButton;
    private Button helpButton;
    private Button settingButton;

    //视图
    private View view;

    private View.OnClickListener mClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myNameButton:
                    // TODO: 2018/9/24 跳转到我的信息
                    Intent intent1 = new Intent(getActivity(), PersonalInfromationActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.historyOrderButton:
                    // TODO: 2018/9/24 跳转到历史订单
                    Intent intent2 = new Intent(getActivity(), HistoryOrderActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.aboutTianDuanButton:
                    // TODO: 2018/9/24 跳转到关于天缎订单
                    Intent intent3 = new Intent(getActivity(), AboutTianduanActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.helpButton:
                    // TODO: 2018/9/24 跳转到帮助界面
                    Intent intent4 = new Intent(getActivity(), HelpActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.settingButton:
                    // TODO: 2018/9/24 跳转到设置界面
                    Intent intent5 = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent5);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_information, container, false);

        myNameButton = view.findViewById(R.id.myNameButton);
        historyOrderButton = view.findViewById(R.id.historyOrderButton);
        aboutTianduanButton = view.findViewById(R.id.aboutTianDuanButton);
        helpButton = view.findViewById(R.id.helpButton);
        settingButton = view.findViewById(R.id.settingButton);

        myNameButton.setOnClickListener(mClickListener);
        historyOrderButton.setOnClickListener(mClickListener);
        aboutTianduanButton.setOnClickListener(mClickListener);
        helpButton.setOnClickListener(mClickListener);
        settingButton.setOnClickListener(mClickListener);

        setDrawable();
        return view;
    }


    //设置按钮左边图片大小
    public void setDrawable(){

        Drawable profilePhoto = getResources().getDrawable(R.drawable.profile_photo);
        //前两个表示坐标位置，长宽
        profilePhoto.setBounds(20, 10, 230,240);
        myNameButton.setCompoundDrawables(profilePhoto,null,null,null);

    }


}
