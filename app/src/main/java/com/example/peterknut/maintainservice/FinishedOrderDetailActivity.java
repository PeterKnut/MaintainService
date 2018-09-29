package com.example.peterknut.maintainservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FinishedOrderDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
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
    private TextView clientNoteTextView;
    private TextView acceptNoteTextView;
    private ImageView imageDescriptionImageView;
    private TextView videoDiagnoseTextView;
    private TextView costTiemTextView;
    private TextView repairFinishedTimeTextView;
    private TextView repairStartTimeTextView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_finished_order_detail);
            initView();
            initListener();
        }

        private void initView(){
            mToolbar = findViewById(R.id.finishedToolbar);


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
            clientNoteTextView = findViewById(R.id.clientNoteTextView);
            imageDescriptionImageView = findViewById(R.id.imageDescribe);
            acceptNoteTextView = findViewById(R.id.acceptNoteTextView);
            videoDiagnoseTextView = findViewById(R.id.videoDiagnoseTextView);

            orderIdTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getOrderId());
            repairTimeTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getRepairTime().toString());
            expectedStartTimeTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime().toString());
            clientNameTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getClientName());
            clientAddressTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getRepairAddress());
            contactNameTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getContactName());
            contactPhoneTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getContactMobile());
            deviceIdTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getDeviceId().toString());
            // TODO: 2018/9/28 设置设备名
//              deviceNameTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getDeviceName());
            orderStatusTextView.setText("未评价");
            faultTypeTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getFaultTypeName());
            faultDescriptionTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getFaultDescription());
            // TODO: 2018/9/28 设置客户备注
            //        clientNoteTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getRemarks());
            acceptNoteTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getAcceptRemark());
//        videoDiagnoseTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getSiginRemark());
        }
        private void initListener(){
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
}
