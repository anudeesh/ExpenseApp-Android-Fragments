package com.example.anudeesh.inclass08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anudeesh on 10/17/2016.
 */
public class ExpenseAdapter extends ArrayAdapter<Expense> {
    List<Expense> mData;
    Context mContext;
    int mResource;
    public ExpenseAdapter(Context context, int resource, List<Expense> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        Expense exp = mData.get(position);

        TextView en = (TextView) convertView.findViewById(R.id.textViewEName);
        TextView ea = (TextView) convertView.findViewById(R.id.textViewEAmount);

        en.setText(exp.getExp_name());
        ea.setText("$"+exp.getExp_amount());
        return convertView;
    }
}
