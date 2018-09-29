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

import java.util.LinkedList;

/**
 * 将各个Order列表添加到相应的ListView控件上
 */

public class OrderAdapter extends BaseAdapter {
    //存放要显示的订单列表
    private LinkedList<Order> mOrder;
    private Context mContext;

    public OrderAdapter(LinkedList<Order> mOrder, Context mContext){
        this.mOrder = mOrder;
        this.mContext = mContext;
    }

    //返回要显示的订单列表中订单数量
    @Override
    public int getCount() {
        return mOrder.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO: 2018/9/22 将list中的order中信息显示到各控件，并给
    //listView控件的item
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_order, parent, false);

        TextView orderId = convertView.findViewById(R.id.orderId);
        TextView repairTime = convertView.findViewById(R.id.repairTime);
        TextView clientName = convertView.findViewById(R.id.clientName);
        TextView contactName = convertView.findViewById(R.id.contactName);
        TextView equipmentName = convertView.findViewById(R.id.equipmentName);
        TextView orderStatus = convertView.findViewById(R.id.orderStatus);
        TableLayout viewDetail = convertView.findViewById(R.id.viewDetail);

        orderId.setText(mOrder.get(position).getOrderId());
        repairTime.setText(mOrder.get(position).getRepairTime().toString());
        clientName.setText(mOrder.get(position).getClientName());
        contactName.setText(mOrder.get(position).getContactName());
        equipmentName.setText(mOrder.get(position).getDeviceName());
        String status = "";
        switch (mOrder.get(position).getStatus()){
            case 1:
                status = "待签收";
                break;
            case 2:
                status = "待签到";
                break;
            case 3:
                status = "待完工";
                break;
            case 4:
                status = "未评价";
                break;
            case 5:
                status = "已完成";
                break;
        }
        orderStatus.setText(status);

        viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (mOrder.get(position).getStatus()){
                    case 1:
                        intent = new Intent(mContext, UnSignedOrderDetailActivity.class);
                        GlobalVariablies.orderStatus = 1;
                        break;
                    case 2:
                        intent = new Intent(mContext, UnchekinOrderDetailActivity.class);
                        GlobalVariablies.orderStatus = 2;
                        break;
                    case 3:
                        intent = new Intent(mContext, UnfinishedOrderDetailActivity.class);
                        GlobalVariablies.orderStatus = 3;
                        break;
                    case 4:
                        intent = new Intent(mContext, UncommentOrderDetailActivity.class);
                        GlobalVariablies.orderStatus = 4;
                        break;
                    case 5:
                        intent = new Intent(mContext, FinishedOrderDetailActivity.class);
                        GlobalVariablies.orderStatus = 5;
                        break;
                }
                GlobalVariablies.orderPosition = position;
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
