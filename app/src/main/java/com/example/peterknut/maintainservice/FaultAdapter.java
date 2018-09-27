package com.example.peterknut.maintainservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;

//读取各种故障并显示到故障列表中
public class FaultAdapter extends BaseAdapter{

    private LinkedList<Fault> mFault;
    private Context mContext;

    public FaultAdapter(LinkedList<Fault> mFault, Context mContext) {
        this.mFault = mFault;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mFault.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_fault,parent,false);

        TextView faultPhenomenonTextView = convertView.findViewById(R.id.faultPhenomenonTextView);
        TextView faultTypeTextView = convertView.findViewById(R.id.faultTypeTextView);
        Button detailButton = convertView.findViewById(R.id.detailButton);

        faultPhenomenonTextView.setText(mFault.get(position).getPhenomenon());
        //服务器定义的是long类型
        // TODO: 2018/9/27 根据故障类型编号，设置对应为文本信息 
        faultTypeTextView.setText(mFault.get(position).getPhenomenon());
        // TODO: 2018/9/27 点击查看详情按钮跳转到详情页面


        return convertView;
    }
}
