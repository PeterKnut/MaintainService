package com.example.peterknut.maintainservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.peterknut.maintainservice.R.id.finishedOrderButton;


public class AllWorkOrderFragment extends Fragment {

    //标题栏
    private Toolbar toolbar;
    //左滑菜单栏
    private DrawerLayout drawerLayout;
    //各按钮
    private Button allWorkOrderButton;
    private Button unSignedOrderButton;
    private Button unCheckOrderButton;
    private Button unFinishedOrderButton;
    private Button unCommentOrderButton;
    private Button finishedOrderButton;

    //要切换的页面
    private UnSignedOrderFragment unSignedOrderFragment;

    //当前页面
    private Fragment currentFragment, targetFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_all_work_order, container, false);
        drawerLayout = view.findViewById(R.id.drawerlayout);
        toolbar = view.findViewById(R.id.my_work_order_toolbar);
        allWorkOrderButton = view.findViewById(R.id.allWorkOrderButton);
        unSignedOrderButton = view.findViewById(R.id.unSignedOrderButton);
        unCheckOrderButton = view.findViewById(R.id.unCheckOrderButton);
        unFinishedOrderButton = view.findViewById(R.id.unFinishedOrderButton);
        unCommentOrderButton = view.findViewById(R.id.unCommentOrderButton);
        finishedOrderButton = view.findViewById(R.id.finishedOrderButton);
        final MainActivity mainActivity = (MainActivity)getActivity();
        View.OnClickListener clickListener = new View.OnClickListener() {
            FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.unSignedOrderButton:
                        transaction.replace(R.id.fragment, new UnSignedOrderFragment());
                        transaction.commit();
                        break;
                }
            }
        };
        unSignedOrderButton.setOnClickListener(clickListener);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawers();
                }else{
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        return view;
    }



}
