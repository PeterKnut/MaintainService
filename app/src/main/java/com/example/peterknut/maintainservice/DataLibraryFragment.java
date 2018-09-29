package com.example.peterknut.maintainservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


public class DataLibraryFragment extends Fragment {



    //存储所有的订单
    private List<Fault> mFault = null;
    //当前上下文环境
    private Context mContext;
    //订单适配器
    private DocumentAdapter mAdapter = null;
    private ListView list_document;

    //当前页面视图
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_data_library, container, false);
        list_document = view.findViewById(R.id.document_list);
        mContext = this.getActivity();

        mAdapter = new DocumentAdapter(GlobalVariablies.documentLinkedList, mContext);
        list_document.setAdapter(mAdapter);

        return view;

    }



}
