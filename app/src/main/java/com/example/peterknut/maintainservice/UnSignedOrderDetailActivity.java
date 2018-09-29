package com.example.peterknut.maintainservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import javax.microedition.khronos.opengles.GL;

import okhttp3.Call;
import okhttp3.MediaType;

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
    private TextView clientNoteTExtView;
    private ImageView imageDescriptionImageView;
    private EditText acceptNoteEditText;

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
        clientNoteTExtView = findViewById(R.id.clientNoteTextView);
        imageDescriptionImageView = findViewById(R.id.imageDescribe);
        acceptNoteEditText = findViewById(R.id.acceptRemarksEditText);
        getImage();

        orderIdTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getOrderId());
        repairTimeTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getRepairTime().toString());
        expectedStartTimeTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime().toString());
        clientNameTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getClientName());
        clientAddressTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getRepairAddress());
        contactNameTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getContactName());
        contactPhoneTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getContactMobile());
        deviceIdTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getDeviceId().toString());
        // TODO: 2018/9/28 设置设备名 
        //      deviceNameTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getDeviceName());
        orderStatusTextView.setText("待签收");
        faultTypeTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getFaultTypeName());
        faultDescriptionTextView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getFaultDescription());
        // TODO: 2018/9/28 设置客户备注 
        //        clientNoteTExtView.setText(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getRemarks());
        // TODO: 2018/9/28 设置图片描述


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
        // TODO: 2018/9/28 将预计维修时间以及预计结束客户备注上传到服务器
        commitSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/9/28 将预估维修时间和结束时间保存到该订单中

                //签收备注和订单状态更新
                GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).setAcceptRemark(String.valueOf(acceptNoteEditText.getText()));
                GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).setStatus(2);
                //上传到服务器
                updateOrder();
                //将该工单从待签收工单列表中移除，并添加到待签到工单中
                GlobalVariablies.unCheckInOrder.add(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition));
                GlobalVariablies.unSignedInOrder.remove(GlobalVariablies.orderPosition);
                //更改签收备注和订单详情
                GlobalVariablies.orderStatus = 2;
                GlobalVariablies.orderPosition = GlobalVariablies.unCheckInOrder.size() - 1;
                //跳转到之后的详情页面
                Intent intent = new Intent(UnSignedOrderDetailActivity.this, UnchekinOrderDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //将其上传到服务器
    private void updateOrder() {

        OkHttpUtils.post()
                .url(GlobalVariablies.UPDATE_ORDER_URL)
                .addParams("orderId", GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getOrderId())
                .addParams("estimatedStartTime", String.valueOf(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime()))
                .addParams("estimatedEndTime", String.valueOf(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getEstimatedEndTime()))
                .addParams("status", "2")
//                // TODO: 2018/9/28 将接受时间添加
                //           .addParams("acceptTime", String.valueOf(new Date()))
                .addParams("acceptRemark", String.valueOf(acceptNoteEditText.getText()))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getImage(){
        OkHttpUtils.get()
                .url(GlobalVariablies.GET_IMAGE_URL)
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
