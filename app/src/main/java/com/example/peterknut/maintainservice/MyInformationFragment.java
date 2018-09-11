package com.example.peterknut.maintainservice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyInformationFragment extends Fragment {

    //按钮
    private Button myNameButton;
    private Button historyOrderButton;
    private Button aboutTianduanButton;
    private Button helpButton;
    private Button settingButton;

    //视图
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_information, container, false);

        myNameButton = view.findViewById(R.id.myNameButton);
        historyOrderButton = view.findViewById(R.id.historyOrderButton);
        aboutTianduanButton = view.findViewById(R.id.aboutTianDuanButton);
        helpButton = view.findViewById(R.id.helpButton);
        settingButton = view.findViewById(R.id.settingButton);

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
