package com.example.peterknut.maintainservice;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 个人基础信息查看修改页面
 */
// TODO: 2018/9/24  页面包含个人的头像、姓名、员工编号、综合评价、联系电话、家庭住址，其中头像、联系电话和家庭住址可以修改 
public class PersonalInfromationActivity extends AppCompatActivity {


    private TextView myPhotoTextView;
    private TextView myNameTextView;
    private TextView myIdTextView;
    private TextView myScoreTextView;
    private TextView myPhoneTextView;
    private TextView myAddressTextView;
    private TextView myUserNameTextView;
    private TextView myEmailTextView;

    //保存图像头片
    private Bitmap head;
    //sd路径
    String path = "/sdcard";


    //点击事件响应
    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myPhotoTextView:
                    // TODO: 2018/9/24 跳转到更换头像页面，并保存到服务器
                    showTypeDialog();
                    System.out.println("点击头像按钮");
                    break;
                case R.id.myPhoneTextView:
                    // TODO: 2018/9/24 跳转到修改电话界面 
                    System.out.println("点击我的电话按钮");
                    break;
                case R.id.myAddressTextView:
                    // TODO: 2018/9/24 点击按钮转到修改地址的页面
                    System.out.println("点击我的地址按钮");
                    break;
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    // TODO: 2018/9/24 裁剪图片
                    cropPhoto(data.getData());// 裁剪图片


                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    // TODO: 2018/9/24 裁剪图片
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片

                }
                break;
            case 3:
                if(data != null){
                    //Bundle extras = data.getExtras();
                    //head = extras.getParcelable("data");
                    try {
                        head =MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                data.getData());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    if(head != null){
                        /**
                         * todo 上传服务器
                         */
                        setPictoView(head);    //保存在SD卡中
                        myPhotoTextView.setCompoundDrawables(null, null, new BitmapDrawable(head), null);
                    }
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infromation);
        //解决File冲突
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        init();

    }


    //初始化控件
    public void init() {

        myPhotoTextView = findViewById(R.id.myPhotoTextView);
        myNameTextView = findViewById(R.id.myNameTextView);
        myIdTextView = findViewById(R.id.myIdTextView);
        myScoreTextView = findViewById(R.id.myEmailTextView);
        myPhoneTextView = findViewById(R.id.myPhoneTextView);
        myAddressTextView = findViewById(R.id.myAddressTextView);
        myUserNameTextView = findViewById(R.id.myUserNameTextView);

        myUserNameTextView.setText(GlobalVariablies.user.getUsername());
        myIdTextView.setText(String.valueOf(GlobalVariablies.user.getUserId()));
        myNameTextView.setText(GlobalVariablies.user.getName());
        myPhoneTextView.setText(GlobalVariablies.user.getMobile());
        myAddressTextView.setText(GlobalVariablies.user.getLiveAddress());
   //         myEmailTextView.setText(GlobalVariablies.user.getEmail());

        myPhoneTextView.setOnClickListener(myClickListener);
        myAddressTextView.setOnClickListener(myClickListener);


    }





    //弹出选择图片方式的对话框
    public void showTypeDialog(){
        //显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfromationActivity.this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(PersonalInfromationActivity.this, R.layout.dialog_select_photo, null);
        TextView selectGalleryTextView = view.findViewById(R.id.tv_select_gallery);
        TextView selectCameraTextView = view.findViewById(R.id.tv_select_camera);
        //点击选择相册之后弹出相册选择
        selectGalleryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "iamge/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        //点击照相之后弹出拍摄图片
        selectCameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PersonalInfromationActivity.this,
                        new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);
                dialog.dismiss();

            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 调用系统的裁剪功能
     * @param uri
     */
    public void cropPhoto(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //aspectX aspectY是宽高的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //outputX outPutY 是裁剪图片宽高
        intent.putExtra("outputX",250);
        intent.putExtra("outputY",250);


        intent.putExtra("return-data",false);



        startActivityForResult(intent,3);
    }

    //把图片保存到SD卡中
    private void setPictoView(Bitmap mBitmap){
        String sdStadus = Environment.getExternalStorageState();
        //检测SD卡是否可用
        if(!sdStadus.equals(Environment.MEDIA_MOUNTED)){
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        //创建文件夹
        file.mkdirs();

        String fileName = path + "head.jpg";
        try {
            b = new FileOutputStream(fileName);
            //把数据写入文件
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100,b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
}
