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


public class UnSignedOrderFragment extends Fragment {


    //当前上下文环境
    private Context mContext;
    //订单适配器
    private OrderAdapter mAdapter = null;
    private ListView list_unsigned_order;

    //当前页面视图
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_unsigned_order,container,false);

        // 渲染这个页面布局
        //获得当前Fragment的上下文环境
        mContext = this.getActivity();
        list_unsigned_order = view.findViewById(R.id.unsigned_order_list);
        // TODO: 2018/9/24 用户登录后获取其订单列表，声明一个公共变量存储，在这里修改适配器初始化为那个公共的变量

        mAdapter = new OrderAdapter(GlobalVariablies.unSignedInOrder, mContext);
        list_unsigned_order.setAdapter(mAdapter);

        return view;
    }

}
