package com.example.peterknut.maintainservice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TableLayout;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_fault,parent,false);

        TextView faultPhenomenonTextView = convertView.findViewById(R.id.phenomenonTextView);
        TextView faultTypeTextView = convertView.findViewById(R.id.faultTypeTextView);
        TextView faultIdTextView = convertView.findViewById(R.id.faultIdTextView);
        TableLayout tableLayout = convertView.findViewById(R.id.viewDetail);


        faultIdTextView.setText(String.valueOf(mFault.get(position).getFaultId()));
        faultPhenomenonTextView.setText(mFault.get(position).getPhenomenon());
        // TODO: 2018/9/27 根据故障类型编号，设置对应为文本信息 
        faultTypeTextView.setText(String.valueOf(mFault.get(position).getFaultTypeId()));


        tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,FaultDetailActivity.class);
                mContext.startActivity(intent);
                GlobalVariablies.faultPosition = position;
            }
        });

        return convertView;
    }
}
