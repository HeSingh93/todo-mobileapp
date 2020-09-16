package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;

public class OrderDetail extends AppCompatActivity {

    private WorkOrder currentWorkOrder;
    private ArrayList<WorkOrder> workOrders;

    private TextView dateTextView;
    private TextView companyTextView;
    private TextView addressTextView;
    private TextView workDescriptionTextView;
    private TextView contactInfoTextView;

    private Button acceptButton;
    private Button declineButton;
    private Button jobFinishedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        currentWorkOrder = getIntent().getParcelableExtra("CURRENT_WORKORDER_MESSAGE");
        workOrders = getIntent().getParcelableArrayListExtra("WORKORDERS_LIST_MESSAGE");

        dateTextView = findViewById(R.id.dateTextViewOrderDetails);
        companyTextView = findViewById(R.id.customerTextViewOrderDetails);
        addressTextView = findViewById(R.id.addressTextViewOrderDetails);
        workDescriptionTextView = findViewById(R.id.workDescriptionTextViewOrderDetails);
        contactInfoTextView = findViewById(R.id.contactInfoTextViewOrderDetails);

        acceptButton = findViewById(R.id.acceptButtonOrderDetails);
        declineButton = findViewById(R.id.declineButtonOrderDetails);
        jobFinishedButton = findViewById(R.id.finishedButtonOrderDetails);

        dateTextView.setText(currentWorkOrder.getDateTimeInfo());
        companyTextView.setText(currentWorkOrder.getCustomer().getCompanyName());
        addressTextView.setText(currentWorkOrder.getAddress());
        workDescriptionTextView.setText(currentWorkOrder.getWorkDescription());
        contactInfoTextView.setText(currentWorkOrder.getContactInfo());

        jobFinishedButton.setEnabled(false);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWorkOrder.setStatus(2);
                acceptButton.setEnabled(false);
                acceptButton.setVisibility(View.INVISIBLE);
                declineButton.setEnabled((false));
                declineButton.setVisibility(View.INVISIBLE);
                jobFinishedButton.setEnabled(true);
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWorkOrder.setEmployee(null);
                workOrders.remove(currentWorkOrder);

                Intent declineIntent = new Intent(OrderDetail.this, OrderList.class);
                declineIntent.putParcelableArrayListExtra("WORKORDERS_LIST_MESSAGE", workOrders);
                startActivity(declineIntent);
            }
        });

        jobFinishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWorkOrder.setStatus(3);
                Intent jobFinishedIntent = new Intent(OrderDetail.this, TimeAndExpenses.class);
                jobFinishedIntent.putParcelableArrayListExtra("WORKORDERS_LIST_MESSAGE", workOrders);
                jobFinishedIntent.putExtra("CURRENT_WORKORDER_MESSAGE", currentWorkOrder);
                startActivity(jobFinishedIntent);
            }
        });
    }
}