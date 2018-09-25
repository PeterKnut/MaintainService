package com.example.peterknut.maintainservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class AllWorkOrderFragment extends Fragment {

    //存储所有的订单
    private List<Order> mAllOrder = null;
    //当前上下文环境
    private Context mContext;
    //订单适配器
    private OrderAdapter mAdapter = null;
    private ListView list_all_order;
    
    //当前页面视图
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        view = inflater.inflate(R.layout.fragment_all_work_order, container, false);
        // 渲染这个页面布局
        //获得当前Fragment的上下文环境
        mContext = this.getActivity();
        list_all_order = view.findViewById(R.id.all_order_list);
        // TODO: 2018/9/24 用户登录后获取其订单列表，声明一个公共变量存储，在这里修改适配器初始化为那个公共的变量 
        mAllOrder = new LinkedList<Order>();
        mAllOrder.add(new Order("123da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("124da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("125da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("126da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("123da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("124da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("125da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("126da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("123da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("124da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("125da", new Date(), "zhang", "guan", "Devices", 2));
        mAllOrder.add(new Order("126da", new Date(), "zhang", "guan", "Devices", 2));
//         等到传递信息无空值，可以使用这句
//        mAdapter = new OrderAdapter(GlobalVariablies.allWorkOrder, mContext);
        mAdapter = new OrderAdapter((LinkedList<Order>) mAllOrder, mContext);
        list_all_order.setAdapter(mAdapter);
        return view;

    }


}
