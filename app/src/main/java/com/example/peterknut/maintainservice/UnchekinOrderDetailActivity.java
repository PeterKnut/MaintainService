package com.example.peterknut.maintainservice;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.VideoView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.Call;
import okhttp3.MediaType;

public class UnchekinOrderDetailActivity extends AppCompatActivity {


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
    private EditText videoDiagnoseNoteEditText;
    private VideoView videoView;


    private Button checkInButton;
    private Button cancelSignButton;
    private Button remoteDiagnoseButton;
    private Button finishedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncheckin_order_detail);
        initView();
        initListener();
    }


    //绑定各控件
    private void initView(){
        mToolbar = findViewById(R.id.uncheckinToolbar);


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
        acceptNoteTextView = findViewById(R.id.acceptNoteView);
        videoDiagnoseNoteEditText = findViewById(R.id.videoDiagnoseNoteEditText);
        videoView = findViewById(R.id.videoDescriptionVideo);
        getImage();


        orderIdTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getOrderId());
        repairTimeTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getRepairTime().toString());
        expectedStartTimeTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getEstimatedStartTime().toString());
        clientNameTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getClientName());
        clientAddressTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getRepairAddress());
        contactNameTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getContactName());
        contactPhoneTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getContactMobile());
        deviceIdTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getDeviceId().toString());
        // TODO: 2018/9/28 设置设备名
        //      deviceNameTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getDeviceName());
        orderStatusTextView.setText("待签收");
        faultTypeTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getFaultTypeName());
        faultDescriptionTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getFaultDescription());
        // TODO: 2018/9/28 设置客户备注
        //        clientNoteTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getRemarks());
        acceptNoteTextView.setText(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getAcceptRemark());



        //设置视频路径
        videoView.setVideoURI(Uri.parse(GlobalVariablies.GET_Video_URL1));





        checkInButton = findViewById(R.id.checkinButton);
        remoteDiagnoseButton = findViewById(R.id.remoteDiagnoseButton);
        cancelSignButton = findViewById(R.id.unsignedButton);
        finishedButton = findViewById(R.id.finishedOrderButton);

    }
    //为各控件添加响应事件
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


        //  将工单改为待签收状态，添加到待签收工单列表从待签到工单列表中移除
        cancelSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//弹出输入框返回内容
//                View.OnClickListener clickListener = new View.OnClickListener() {
//                    SendnotifyDialog sendnotifyDialog;
//                    @Override
//                    public void onClick(View v) {
//                        OkHttpUtils.post()
//                                .url(GlobalVariablies.NOTIFY_SAVE)
//                                .addParams("userIds", String.valueOf(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getDealUserId()))
//                                .addParams("type","3")
//                                .addParams("title","取消签收")
//                                .addParams("content",GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getOrderId())
//                                .addParams("remark",sendnotifyDialog.remarksEditText.getText().toString())
//                                .build()
//                                .execute(new StringCallback() {
//                                    @Override
//                                    public void onError(Call call, Exception e, int id) {
//
//                                    }
//
//                                    @Override
//                                    public void onResponse(String response, int id) {
//
//                                    }
//                                });

//                        OkHttpUtils.post()
//                                .url(GlobalVariablies.NOTIFY_SAVE)
//                                .addParams("userIds", String.valueOf(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getDealUserId()))
//                                .addParams("createBy", String.valueOf(GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getRepairUserId()))
//                                .addParams("type","3")
//                                .addParams("title","拒绝签收")
//                                .addParams("content",GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getOrderId())
//                                .addParams("remark", String.valueOf(GlobalVariablies.remarks)
//                    }
//                };
//                SendnotifyDialog sendnotifyDialog = new SendnotifyDialog(UnchekinOrderDetailActivity.this,clickListener);
//                sendnotifyDialog.show();

                GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).setStatus(1);
                GlobalVariablies.unSignedInOrder.add(GlobalVariablies.unFinishedOrder.get(GlobalVariablies.orderPosition));
                GlobalVariablies.unCheckInOrder.remove(GlobalVariablies.orderPosition);

                GlobalVariablies.orderStatus = 1;
                GlobalVariablies.orderPosition = GlobalVariablies.unSignedInOrder.size() -1 ;

                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId",GlobalVariablies.unSignedInOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        .addParams("status","1")
                        .addParams("acceptRemark","")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }
                            @Override
                            public void onResponse(String response, int id) {
                            }
                        });
                Intent intent = new Intent(UnchekinOrderDetailActivity.this, UnSignedOrderDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 将工单状态改为待完工状态，添加到待完工工单列表，从待签到工单列表中移除，并提交到服务器中
        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/9/28 将预估维修时间和结束时间保存到该订单中
                //视频诊断备注和订单状态更新
                GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).setStatus(3);
                GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).setCheckin(true);
                // TODO: 2018/9/29 更新其视频诊断备注
               GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).setSiginRemark(videoDiagnoseNoteEditText.getText().toString());
                //上传到服务器
                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId", GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        // TODO: 2018/9/29  上传签到时间和签到备注（远程视频诊断备注）
                        .addParams("siginRemark",GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getSiginRemark())
   //                     .addParams("signinTime", String.valueOf(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getSigninTime()))
                        .addParams("status","3")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {

                            }
                        });
                GlobalVariablies.unFinishedOrder.add(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition));
                GlobalVariablies.unCheckInOrder.remove(GlobalVariablies.orderPosition);
                //更改签收备注和订单详情
                GlobalVariablies.orderStatus = 3;
                GlobalVariablies.orderPosition = GlobalVariablies.unFinishedOrder.size() - 1;

                //跳转到之后的详情页面
                Intent intent = new Intent(UnchekinOrderDetailActivity.this, UnfinishedOrderDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //远程视频诊断
        remoteDiagnoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/9/29 远程视频通信
          //      Intent intent = new Intent(UnchekinOrderDetailActivity.this, VideoCall.class);
          //      startActivity(intent);




            }
        });
        //完成工单
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).setSiginRemark(videoDiagnoseNoteEditText.getText().toString());

                OkHttpUtils.post()
                        .url(GlobalVariablies.UPDATE_ORDER_URL)
                        .addParams("orderId",GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getOrderId())
                        .addParams("status","4")
                        .addParams("signinRemark",GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getSiginRemark())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });

                GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).setStatus(4);
                GlobalVariablies.unCommentOrder.add(GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition));
                GlobalVariablies.unCheckInOrder.remove(GlobalVariablies.orderPosition);

                GlobalVariablies.orderStatus = 4;
                GlobalVariablies.orderPosition = GlobalVariablies.unCommentOrder.size();


                Intent intent = new Intent(UnchekinOrderDetailActivity.this, WorkSummaryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //获取图像
    private void getImage(){
//        OkHttpUtils.get()
//                .url(GlobalVariablies.GET_IMAGE_URL)
//                .addParams("limit","3")
//                .addParams("offset","0")
//                .addParams("refrenceid",GlobalVariablies.unCheckInOrder.get(GlobalVariablies.orderPosition).getOrderId())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });

        OkHttpUtils.get()
                .url(GlobalVariablies.GET_IMAGE_URL2)
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
