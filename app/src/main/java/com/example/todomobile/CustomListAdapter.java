package com.example.todomobile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todomobile.R;
import com.example.todomobile.models.WorkOrder;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;
    private List<WorkOrder> workOrders;

    public CustomListAdapter(Activity context, List<WorkOrder> workOrders) {
        super(context, R.layout.listview_row, workOrders);

        this.context = context;
        this.workOrders = workOrders;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);

        TextView dateTextViewID = (TextView) rowView.findViewById(R.id.dateTextViewID);
        TextView companyNameTextViewID = (TextView) rowView.findViewById(R.id.companyNameTextViewID);
        TextView addressTextViewID = (TextView) rowView.findViewById(R.id.addressTextviewID);

        dateTextViewID.setText(workOrders.get(position).getDateTimeInfo());
        companyNameTextViewID.setText(workOrders.get(position).getCustomer().getCompanyName());
        addressTextViewID.setText(workOrders.get(position).getAddress());

        return rowView;
    }
}
