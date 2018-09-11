package com.example.peterknut.maintainservice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {


    /**
     * 几个主要的Fragment
     * 全部工单页面、新增工单页面、故障库页面、我的信息页面
     */
    private AllWorkOrderFragment allWorkOrderFragment;
    private FaultLibraryFragment faultLibraryFragment;
    private AddNewOrderFragment addNewOrderFragment;
    private MyInformationFragment myInformationFragment;

    /**
     *用于Fragment切换
     */
    private FragmentTransaction transaction;

    /**
     * 当前页面，以及要切换的页面
     */
    private Fragment currentFragment, targetFragment;

    /**
     * 该页面该有的控件
     */
    private BottomNavigationView bottomNavigationView;


    /**
     * 底部导航栏菜单选择事件监听器
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_work_list:
                    switchFragment(allWorkOrderFragment);
                    return true;
                case R.id.navigation_add_work_order:
                    switchFragment(addNewOrderFragment);
                    return true;
                case R.id.navigation_knowledge_base:
                    switchFragment(faultLibraryFragment);
                    return true;
                case R.id.navigation_my_information:
                    switchFragment(myInformationFragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }


    /**
     * 初始化各控件 及变量
     */
    public void init(){
        //将各控件变量与布局中控件绑定
        bottomNavigationView = findViewById(R.id.navigation);
        //为各控件添加需要的事件监听器
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //初始化各变量
        allWorkOrderFragment = new AllWorkOrderFragment();
        faultLibraryFragment = new FaultLibraryFragment();
        myInformationFragment = new MyInformationFragment();
        addNewOrderFragment = new AddNewOrderFragment();

        currentFragment = allWorkOrderFragment;

        //为这个活动设置主页面
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, currentFragment).commit();


    }

    /**
     * 点击底部导航栏相应按钮切换到对应的Fragment
     */
    public void switchFragment(Fragment targetFragment){
        transaction = getSupportFragmentManager().beginTransaction();
        System.out.println("我进来了");
        if(targetFragment.isAdded()){
            transaction.hide(currentFragment)
                    .show(targetFragment)
                    .commit();
            System.out.println("天际过了");
        }else{
            transaction.hide(currentFragment)
                    .add(R.id.fragment, targetFragment)
                    .commit();
            System.out.println("未添加");
        }
        currentFragment = targetFragment;

    }
}
