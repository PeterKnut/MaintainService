package com.example.peterknut.maintainservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;


public class FaultLibraryFragment extends Fragment {


    //存储所有的订单
    private List<Fault> mFault = null;
    //当前上下文环境
    private Context mContext;
    //订单适配器
    private FaultAdapter mAdapter = null;
    private ListView list_fault;

    //当前页面视图
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_fault_library, container, false);
        list_fault = view.findViewById(R.id.fault_list);
        mContext = this.getActivity();

        mAdapter = new FaultAdapter(GlobalVariablies.faultLinkedList, mContext);
        list_fault.setAdapter(mAdapter);

        return view;
    }



}
