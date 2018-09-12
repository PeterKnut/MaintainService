package com.example.peterknut.maintainservice;

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


public class WorkListFragment extends Fragment {

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

    //页面视图
    private View view;
    //要切换的页面
    private AllWorkOrderFragment allWorkOrderFragment;
    private UnSignedOrderFragment unSignedOrderFragment;
    private UncheckInOrderFragment uncheckInOrderFragment;
    private UncommentOrderFragment uncommentOrderFragment;
    private UnfinishedOrderFragment unfinishedOrderFragment;
    private FinishedOrderFragment finishedOrderFragment;

    //当前页面
    private Fragment currentFragment, targetFragment;
    private FragmentTransaction transaction;
    //菜单栏按钮点击事件监听器
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.allWorkOrderButton:
                    if(allWorkOrderFragment == null)
                        switchFragment(new AllWorkOrderFragment());
                    else
                        switchFragment(allWorkOrderFragment);
                    break;
                case R.id.unSignedOrderButton:
                    if (unSignedOrderFragment == null)
                        switchFragment(new UnSignedOrderFragment());
                    else
                        switchFragment(unSignedOrderFragment);
                    break;
                case R.id.unCheckOrderButton:
                    if (uncheckInOrderFragment == null)
                        switchFragment(new UncheckInOrderFragment());
                    else
                        switchFragment(uncheckInOrderFragment);
                    break;
                case R.id.unFinishedOrderButton:
                    if (unfinishedOrderFragment == null)
                        switchFragment(new UnfinishedOrderFragment());
                    else
                        switchFragment(unfinishedOrderFragment);
                    break;
                case R.id.unCommentOrderButton:
                    if(uncommentOrderFragment == null)
                        switchFragment(new UncommentOrderFragment());
                    else
                        switchFragment(uncommentOrderFragment);
                    break;
                case R.id.finishedOrderButton:
                    if(finishedOrderFragment == null)
                        switchFragment(new FinishedOrderFragment());
                    else
                        switchFragment(finishedOrderFragment);
                    break;
            }
            drawerLayout.closeDrawers();
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        init(inflater, container);
        return view;
    }

    //绑定控件
    public void init(LayoutInflater inflater, ViewGroup container){

        view = inflater.inflate(R.layout.fragment_work_list, container, false);
        drawerLayout = view.findViewById(R.id.drawerlayout);
        toolbar = view.findViewById(R.id.my_work_order_toolbar);
        allWorkOrderButton = view.findViewById(R.id.allWorkOrderButton);
        unSignedOrderButton = view.findViewById(R.id.unSignedOrderButton);
        unCheckOrderButton = view.findViewById(R.id.unCheckOrderButton);
        unFinishedOrderButton = view.findViewById(R.id.unFinishedOrderButton);
        unCommentOrderButton = view.findViewById(R.id.unCommentOrderButton);
        finishedOrderButton = view.findViewById(R.id.finishedOrderButton);

        //为各按钮添加事件监听器
         allWorkOrderButton.setOnClickListener(clickListener);
         unSignedOrderButton.setOnClickListener(clickListener);
         unCheckOrderButton.setOnClickListener(clickListener);
         unFinishedOrderButton.setOnClickListener(clickListener);
         unCommentOrderButton.setOnClickListener(clickListener);
         finishedOrderButton.setOnClickListener(clickListener);
        //为导航栏导航菜单添加点击事件监听器
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

        //为这个页面设置默认启动时显示页面
        allWorkOrderFragment = new AllWorkOrderFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.work_list_framelayout, allWorkOrderFragment).commit();

        currentFragment = allWorkOrderFragment;
    }
    //切换页面
    public void switchFragment(Fragment targetFragment){
        transaction = getChildFragmentManager().beginTransaction();
        if(targetFragment.isAdded()){
            transaction.hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }else {
            transaction.hide(currentFragment)
                    .add(R.id.work_list_framelayout, targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }
}
