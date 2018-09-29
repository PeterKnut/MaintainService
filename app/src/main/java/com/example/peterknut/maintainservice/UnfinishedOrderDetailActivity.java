package com.example.peterknut.maintainservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


public class UnfinishedOrderDetailActivity extends AppCompatActivity {

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
    private VideoView videoView;

    private Button cancelCheckinButton;
    private Button finishedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_order_detail);
        initView();
        initListener();
    }


    private void initView(){
        mToolbar = findViewById(R.id.unfinishedToolbar);


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
        videoView = findViewById(R.id.videoDescriptionVideo);
        videoView.setVideoURI(Uri.parse(GlobalVariablies.GET_Video_URL1));
        getImage();

        orderIdTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getOrderId());
        repairTimeTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getRepairTime().toString());
        expectedStartTimeTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime().toString());
        clientNameTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getClientName());
        clientAddressTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getRepairAddress());
        contactNameTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getContactName());
        contactPhoneTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getContactMobile());
        deviceIdTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getDeviceId().toString());
        // TODO: 2018/9/28 设置设备名
//              deviceNameTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getDeviceName());
        orderStatusTextView.setText("未完工");
        faultTypeTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getFaultTypeName());
        faultDescriptionTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getFaultDescription());
        // TODO: 2018/9/28 设置客户备注
        //        clientNoteTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getRemarks());
        acceptNoteTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getAcceptRemark());
        videoDiagnoseTextView.setText(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getSiginRemark());
        
        cancelCheckinButton = findViewById(R.id.cancelCheckinButton);
        finishedButton = findViewById(R.id.finishedOrderButton);
    }
    
    private void initListener(){
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (GlobalVariablies.orderStatus){
                    case 1:
                        intent = new Intent(UnfinishedOrderDetailActivity.this, UnSignedOrderDetailActivity.class);
                        break;
                    case 2:
                        intent = new Intent(UnfinishedOrderDetailActivity.this, UnchekinOrderDetailActivity.class);
                        break;
                    case 4:
                        intent = new Intent(UnfinishedOrderDetailActivity.this, UncommentOrderDetailActivity.class);
                        break;
                    case 5:
                        intent = new Intent(UnfinishedOrderDetailActivity.this, FinishedOrderDetailActivity.class);
                        break;
                }
                startActivity(intent);
                finish();
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });
        //确认完工 
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/9/29 完成工单
                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId",GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        .addParams("status","4")
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });

                GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).setStatus(4);
                GlobalVariablies.unCommentOrder.add(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition));
                GlobalVariablies.unFinishedOrder.remove(GlobalVariablies.orderPosition);

                GlobalVariablies.orderStatus = 4;
                GlobalVariablies.orderPosition = GlobalVariablies.unCommentOrder.size() - 1;


                Intent intent = new Intent(UnfinishedOrderDetailActivity.this, WorkSummaryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //取消签到
        cancelCheckinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId",GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        .addParams("status","2")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }
                            @Override
                            public void onResponse(String response, int id) {
                            }
                        });

                GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition).setStatus(2);
                GlobalVariablies.unCheckInOrder.add(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition));
                GlobalVariablies.unFinishedOrder.remove(GlobalVariablies.orderPosition);

                GlobalVariablies.orderStatus = 2;
                GlobalVariablies.orderPosition = GlobalVariablies.unCheckInOrder.size() - 1;

                Intent intent = new Intent(UnfinishedOrderDetailActivity.this, UnchekinOrderDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }


    //获取图像
    private void getImage(){
        OkHttpUtils.get()
                .url(GlobalVariablies.GET_IMAGE_URL1)
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        imageDescriptionImageView.setImageBitmap(response);
                    }
                });
    }
}
