package com.example.peterknut.maintainservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FinishedOrderDetailActivity extends AppCompatActivity {

        private Toolbar mToolbar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_finished_order_detail);
            initView();
            initListener();
        }

        private void initView(){
            mToolbar = findViewById(R.id.finishedToolbar);
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