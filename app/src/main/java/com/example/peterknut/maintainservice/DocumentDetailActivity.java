package com.example.peterknut.maintainservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DocumentDetailActivity extends AppCompatActivity {


    private TextView documentIdTextView;
    private TextView doctypeTextView;
    private TextView titleTextView;
    private TextView contentTextView;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail);

        documentIdTextView = findViewById(R.id.documentIdTextView);
        doctypeTextView = findViewById(R.id.doctypeTextView);
        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        mToolbar = findViewById(R.id.mToolbar);

        documentIdTextView.setText(String.valueOf(GlobalVariablies.documentLinkedList.get(GlobalVariablies.documentPosition).getDocumentId()));
        doctypeTextView.setText(String.valueOf(GlobalVariablies.documentLinkedList.get(GlobalVariablies.documentPosition).getDoctypeId()));
        titleTextView.setText(GlobalVariablies.documentLinkedList.get(GlobalVariablies.documentPosition).getTitle());
        contentTextView.setText(GlobalVariablies.documentLinkedList.get(GlobalVariablies.documentPosition).getContent());
       mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }

}
