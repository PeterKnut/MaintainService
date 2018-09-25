package com.example.peterknut.maintainservice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Entity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import static android.Manifest.permission.READ_CONTACTS;
// TODO: 2018/9/14  运行之后首页面修改为此
// TODO: 2018/9/16 测试session是否持久化

//
/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {





    // 界面控件
    private AutoCompleteTextView mPhoneView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 绑定控件并设置监听器
        mPhoneView = (AutoCompleteTextView) findViewById(R.id.phone);


        mPasswordView = (EditText) findViewById(R.id.passwordEditText);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override

            //回车相应处理
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }

    private void attemptLogin() {

        //session 持久化
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        // Reset errors.
        mPhoneView.setError(null);
        mPasswordView.setError(null);

        // 获取用户输入的用户名和登陆密码
        String username = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

//        // 用户输入密码后，检测其格式是否正确
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }
//
//        // 检测输入手机号是否有效
//        if (TextUtils.isEmpty(username)) {
//            mPhoneView.setError(getString(R.string.error_field_required));
//            focusView = mPhoneView;
//            cancel = true;
//        } else if (!isPhoneValid(username)) {
//            mPhoneView.setError(getString(R.string.error_invalid_phone));
//            focusView = mPhoneView;
//            cancel = true;
//        }

        //判断用户身份是否合法
        login(username, password);

    }

    private void login(String username, String password) {

        OkHttpUtils.post()
                .url(GlobalVariablies.LOGIN_URL)
                .addParams("username", username)
                .addParams("password",password)
                .build().execute( new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = new JSONObject(response);
                    if(json.getString("msg").equals("操作成功")){

                        // TODO: 2018/9/16 进行该用户个人信息初始化
                        initGlobalVariables();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        // TODO: 2018/9/16 弹出一个提示框，提示手机号或者密码错误
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    //从服务器获取用户的信息并对其进行初始化
    private void initUser(){

        OkHttpUtils.get()
                .url(GlobalVariablies.GET_USER_DETAIL_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject json = null;
                        try {
                            json = new JSONObject(response);
                            GlobalVariablies.user = new User(
                                    json.getString("username"),
                                    json.getString("name"),
                                    json.getString("password"),
                                    json.getString("mobile"),
                                    json.getInt("status"),
                                    json.getLong("sex"),
                                    json.getString("liveAddress"),
                                    json.getString("province"),
                                    json.getString("city"),
                                    json.getString("district")
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }

    //从服务器获取用户的订单信息并对其进行初始化
    private void initOrder(){
        OkHttpUtils.get()
                .url(GlobalVariablies.GET_ORDER_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject json = new JSONObject(response);
                            String jsonArray = json.getString("rows");
                            GlobalVariablies.allWorkOrder = new LinkedList<>(JSON.parseArray(jsonArray,Order.class));
                            System.out.println(GlobalVariablies.allWorkOrder.get(1).getOrderId());
                            System.out.println(jsonArray);
                            //初始化各状态工单
                            for(int i =0; i<GlobalVariablies.allWorkOrder.size();i++){
                                switch (GlobalVariablies.allWorkOrder.get(i).getStatus()){
                                    case 0:
                                        GlobalVariablies.unSignedInOrder.add(GlobalVariablies.allWorkOrder.get(i));
                                        break;
                                    case 1:
                                        GlobalVariablies.unCheckInOrder.add(GlobalVariablies.allWorkOrder.get(i));
                                        break;
                                    case 2:
                                        GlobalVariablies.unFinishedOrder.add(GlobalVariablies.allWorkOrder.get(i));
                                        break;
                                    case 3:
                                        GlobalVariablies.unCommentOrder.add(GlobalVariablies.allWorkOrder.get(i));
                                        break;
                                    case 4:
                                        GlobalVariablies.finishedOrder.add(GlobalVariablies.allWorkOrder.get(i));
                                        break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //判断输入的手机号是否有效
    private boolean isPhoneValid(String phone) {
        return phone.length() == 13;
    }

    //判断输入的密码是否符合格式（六个字母以上）
    // TODO: 2018/9/18
    private boolean isPasswordValid(String password) {
        boolean containLowerCase = false;
        boolean containUpperCase = false;
        boolean containNumber = false;

        for(int i=0;i<password.length();i++){
            if(Character.isLowerCase(password.charAt(i))){
                containLowerCase = true;
            }else if(Character.isUpperCase(password.charAt(i))){
                containUpperCase =  true;
            }else if(Character.isDigit(password.charAt(i))){
                containNumber = true;
            }else{
                return false;
            }
        }
        return containLowerCase && containNumber && containUpperCase;
    }


    //登陆成功后进行全局变量初始化
    // TODO: 2018/9/26 用户工单初始化，个人信息初始化
    public void initGlobalVariables(){
        initUser();
        initOrder();


    }




}

