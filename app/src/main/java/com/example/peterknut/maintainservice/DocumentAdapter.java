package com.example.peterknut.maintainservice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class DocumentAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<Document> mDocument;

    public DocumentAdapter(LinkedList<Document> mDocument,Context mContext) {
        this.mContext = mContext;
        this.mDocument = mDocument;
    }

    @Override
    public int getCount() {
        return mDocument.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_document,parent,false);

        TextView documentIdTextView = convertView.findViewById(R.id.documentIdTextView);
        TextView doctypeIdTextView = convertView.findViewById(R.id.doctypeIdTextView);
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TableLayout viewDetail = convertView.findViewById(R.id.viewDetail);

        documentIdTextView.setText(String.valueOf(GlobalVariablies.documentLinkedList.get(position).getDocumentId()));
        doctypeIdTextView.setText(String.valueOf(GlobalVariablies.documentLinkedList.get(position).getDoctypeId()));
        titleTextView.setText(GlobalVariablies.documentLinkedList.get(position).getTitle());
        viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DocumentDetailActivity.class);
                mContext.startActivity(intent);
                GlobalVariablies.documentPosition = position;
            }
        });

        return convertView;
    }
}
