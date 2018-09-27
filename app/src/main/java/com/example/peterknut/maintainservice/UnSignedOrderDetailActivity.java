package com.example.peterknut.maintainservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//待签收工单详情页面
public class UnSignedOrderDetailActivity extends AppCompatActivity {

    private Toolbar mToobar;
    private TextView orderIdTextView;
    private TextView repairTimeTextView;
    private TextView expectedStartTimeTextView;
    private TextView clientNameTextView;
    private TextView clientAddressTextView;
    private TextView contactNameTextView;
    private TextView contactPhoneTextView;
    private TextView deviceIdTextView;
    private TextView deviceNameTextView;
    private TextView orderStatusTextView;
    private TextView faultTypeTextView;
    private TextView faultDescriptionTextView;
    private TextView clietNoteTExtView;
    private ImageView imageDescriptionImageView;

    private Button cancelSignButton;
    private Button commitSignButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsigned_order_detail);
        initView();
        initListener();
    }

    //初始化各控件
    private void initView(){
        mToobar = findViewById(R.id.unsignedToolbar);

        orderIdTextView = findViewById(R.id.orderIdTextView);
        repairTimeTextView = findViewById(R.id.repairTimeTextView);
        expectedStartTimeTextView = findViewById(R.id.expectedStartTimeTextView);
        clientNameTextView = findViewById(R.id.clientNameTextView);
        clientAddressTextView = findViewById(R.id.clientAddressTextView);
        contactNameTextView = findViewById(R.id.contactNameTextView);
        contactPhoneTextView = findViewById(R.id.contactPhoneTextView);
        deviceIdTextView = findViewById(R.id.deviceIdTextView);
        deviceNameTextView = findViewById(R.id.deviceNameorderIdTextView);
        orderStatusTextView = findViewById(R.id.orderStatusTextView);
        faultTypeTextView = findViewById(R.id.faultTypeTextView);
        faultDescriptionTextView = findViewById(R.id.faultDescriptionTextView);
        clietNoteTExtView = findViewById(R.id.clientNoteTextView);
        imageDescriptionImageView = findViewById(R.id.imageDescribe);

        orderIdTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getOrderId());
        repairTimeTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getRepairTime().toString());
        expectedStartTimeTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime().toString());
        clientNameTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getClientName());
        clientAddressTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getRepairAddress());





        cancelSignButton = findViewById(R.id.unsignedButton);
        commitSignButton = findViewById(R.id.signButton);

    }

    //为各控件添加响应事件
    private void initListener(){
        mToobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // TODO: 2018/9/28 取消工单，并把该工单返回给服务器
        cancelSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // TODO: 2018/9/28 将工单状态改为待签到，并从待签收工单中移除，转到待签到工单详情页面
        commitSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
