package com.example.peterknut.maintainservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class WorkSummaryActivity extends AppCompatActivity {
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
    private EditText workSummaryEditText;

    private Button submitButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_summary);
        initView();
        initListener();
    }

    private void initView(){
        mToolbar = findViewById(R.id.summaryToolbar);


        orderIdTextView = findViewById(R.id.orderIdTextView);
        repairTimeTextView = findViewById(R.id.repairTimeTextView);
        expectedStartTimeTextView = findViewById(R.id.expectedStartTimeTextView);
        clientNameTextView = findViewById(R.id.clientNameTextView);
        clientAddressTextView = findViewById(R.id.clientAddressTextView);
        contactNameTextView = findViewById(R.id.contactNameTextView);
        contactPhoneTextView = findViewById(R.id.contactPhoneTextView);
        deviceIdTextView = findViewById(R.id.deviceIdTextView);
        deviceNameTextView = findViewById(R.id.deviceNameTextView);
        orderStatusTextView = findViewById(R.id.orderStatusTextView);
        faultTypeTextView = findViewById(R.id.faultTypeTextView);
        faultDescriptionTextView = findViewById(R.id.faultDescriptionTextView);
        clientNoteTextView = findViewById(R.id.clientNoteTextView);
        imageDescriptionImageView = findViewById(R.id.imageDescribe);
        acceptNoteTextView = findViewById(R.id.acceptNoteTextView);
        videoDiagnoseTextView = findViewById(R.id.videoDiagnoseTextView);
        workSummaryEditText = findViewById(R.id.workSummaryEditText);

        submitButton = findViewById(R.id.submitButton);
        cancelButton = findViewById(R.id.cancelButton);

        orderIdTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getOrderId());
        repairTimeTextView.setText(GlobalVariablies.SDF.format(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getRepairTime()));
        expectedStartTimeTextView.setText(GlobalVariablies.SDF.format(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime()));
        clientNameTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getClientName());
        clientAddressTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getRepairAddress());
        contactNameTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getContactName());
        contactPhoneTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getContactMobile());
        deviceIdTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getDeviceId().toString());
        deviceNameTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getDeviceName());
        orderStatusTextView.setText("未评价");
        faultTypeTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getFaultTypeName());
        faultDescriptionTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getFaultDescription());
        clientNoteTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getRemarks());
        acceptNoteTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getAcceptRemark());
        videoDiagnoseTextView.setText(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getSiginRemark());
    }
    private void initListener(){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //提交
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).setCompletedRemark(String.valueOf(workSummaryEditText.getText()));
                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId",GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        .addParams("completedRemark",GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getCompletedRemark())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
                Intent intent = new Intent(WorkSummaryActivity.this, UncommentOrderDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = null;
                if(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).isCheckin()){
                    //签过到表明从未完工过来
                    status = "3";
                    GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).setStatus(3);
                    GlobalVariablies.unFinishedOrder.add(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition));
                    GlobalVariablies.unCommentOrder.remove(GlobalVariablies.orderPosition);
                }else{
                    //未签过到表明从未签到过来
                    status = "2";
                    GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).setStatus(2);
                    GlobalVariablies.unCheckInOrder.add(GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition));
                    GlobalVariablies.unCommentOrder.remove(GlobalVariablies.orderPosition);
                }
                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId",GlobalVariablies.unCommentOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        .addParams("status",status)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
                finish();
            }
        });
    }
}
