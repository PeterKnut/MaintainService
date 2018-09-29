package com.example.peterknut.maintainservice;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

public class UncommentOrderDetailActivity extends AppCompatActivity {

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
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncomment_order_detail);
        initView();
        initListener();
    }



    private void initView() {
        mToolbar = findViewById(R.id.uncommentToolbar);


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
        videoDiagnoseTextView = findViewById(R.id.checkinNoteTextView);
        videoView = findViewById(R.id.videoDescriptionVideo);
        getImage();

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
        videoView.setVideoURI(Uri.parse(GlobalVariablies.GET_Video_URL1));

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
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
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
