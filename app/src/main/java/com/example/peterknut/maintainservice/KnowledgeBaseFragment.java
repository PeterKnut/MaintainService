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


public class KnowledgeBaseFragment extends Fragment {


    //标题栏
    private Toolbar toolbar;
    //左滑菜单栏
    private DrawerLayout drawerLayout;
    //各按钮
    private Button faultLibraryButton;
    private Button dataBaseButton;
    private Button equipmentFileButton;
    private Button maintainHistoryButton;


    //页面视图
    private View view;
    //要切换的页面
    private FaultLibraryFragment faultLibraryFragment;
    private DataLibraryFragment dataLibraryFragment;
    private EquipmentFileFragment equipmentFileFragment;
    private MaintainHistoryFragment maintainHistoryFragment;


    //当前页面
    private Fragment currentFragment, targetFragment;
    private FragmentTransaction transaction;
    //菜单栏按钮点击事件监听器
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.faultLibraryButton:
                    if(faultLibraryFragment == null)
                        switchFragment(new FaultLibraryFragment());
                    else
                        switchFragment(faultLibraryFragment);
                    break;
                case R.id.databaseButton:
                    if (dataLibraryFragment == null)
                        switchFragment(new DataLibraryFragment());
                    else
                        switchFragment(dataLibraryFragment);
                    break;
                case R.id.equipmentFileButton:
                    if (equipmentFileFragment == null)
                        switchFragment(new EquipmentFileFragment());
                    else
                        switchFragment(equipmentFileFragment);
                    break;
                case R.id.maintainHistoryButton:
                    if (maintainHistoryFragment == null)
                        switchFragment(new MaintainHistoryFragment());
                    else
                        switchFragment(maintainHistoryFragment);
                    break;

            }
            drawerLayout.closeDrawers();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        init(inflater, container);
        return view;
    }


    //绑定控件
    public void init(LayoutInflater inflater, ViewGroup container){

        view = inflater.inflate(R.layout.fragment_knowledge_base, container, false);
        drawerLayout = view.findViewById(R.id.knowledgeBaseDrawerlayout);
        toolbar = view.findViewById(R.id.knowledge_base_toolbar);
        faultLibraryButton = view.findViewById(R.id.faultLibraryButton);
        dataBaseButton = view.findViewById(R.id.databaseButton);
        equipmentFileButton = view.findViewById(R.id.equipmentFileButton);
        maintainHistoryButton = view.findViewById(R.id.maintainHistoryButton);


        //为各按钮添加事件监听器
        faultLibraryButton.setOnClickListener(clickListener);
        dataBaseButton.setOnClickListener(clickListener);
        equipmentFileButton.setOnClickListener(clickListener);
        maintainHistoryButton.setOnClickListener(clickListener);

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
        faultLibraryFragment = new FaultLibraryFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.knowledge_base_framelayout, faultLibraryFragment).commit();

        currentFragment = faultLibraryFragment;
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
                    .add(R.id.knowledge_base_framelayout, targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }

}
