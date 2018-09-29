package com.example.peterknut.maintainservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class FaultDetailActivity extends AppCompatActivity {


    private TextView faultIdTextView;
    private TextView deviceTypeTextView;
    private TextView deviceSpecificationTextView;
    private TextView faultTypeIdTextView;
    private TextView phenomenonTextView;
    private TextView reasonTextView;
    private TextView solutionTextView;
    private TextView remarkTextView;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_detail);

        faultIdTextView = findViewById(R.id.faultIdTextView);
        deviceTypeTextView = findViewById(R.id.deviceTypeTextView);
        deviceSpecificationTextView = findViewById(R.id.deviceSpecificationTextView);
        faultTypeIdTextView = findViewById(R.id.faultTypeTextView);
        phenomenonTextView = findViewById(R.id.phenomenonTextView);
        reasonTextView = findViewById(R.id.reasonTextView);
        solutionTextView = findViewById(R.id.solutionTextView);
        remarkTextView = findViewById(R.id.remarkTextView);
        mToolbar = findViewById(R.id.mToolbar);


        faultIdTextView.setText(String.valueOf(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getFaultId()));
        deviceTypeTextView.setText(String.valueOf(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getDeviceTypeId()));
        deviceSpecificationTextView.setText(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getDeviceSpecification());
        faultTypeIdTextView.setText(String.valueOf(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getFaultTypeId()));
        phenomenonTextView.setText(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getPhenomenon());
        reasonTextView.setText(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getReason());
        solutionTextView.setText(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getSolution());
        remarkTextView.setText(GlobalVariablies.faultLinkedList.get(GlobalVariablies.faultPosition).getRemark());

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
