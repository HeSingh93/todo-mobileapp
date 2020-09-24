package com.example.todomobile;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.todomobile.models.WorkOrder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class OrderDetail extends ToDoActivity {

    private static final String TAG = "OrderDetail";

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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        currentWorkOrder = getIntent().getParcelableExtra(CURRENT_WORKORDER_MESSAGE);
        workOrders = getIntent().getParcelableArrayListExtra(WORKORDER_LIST_MESSAGE);

        for (WorkOrder workOrder : workOrders) {
            if (workOrder.equals(currentWorkOrder)){
                currentWorkOrder = workOrder;
                break;
            }
        }

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

        if(currentWorkOrder.getStatus() == STATUS_ACCEPTED) {
            acceptButton.setEnabled(false);
            acceptButton.setVisibility(View.INVISIBLE);
            declineButton.setEnabled(false);
            declineButton.setVisibility(View.INVISIBLE);
            jobFinishedButton.setEnabled(true);
        } else {
            jobFinishedButton.setEnabled(false);
        }


        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentWorkOrder.setStatus(STATUS_ACCEPTED);
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
                currentWorkOrder.setStatus(STATUS_UNASSIGNED);
                currentWorkOrder.setEmployeeId(0);
                workOrders.remove(currentWorkOrder);

                Intent declineIntent = new Intent(OrderDetail.this, OrderList.class);
                declineIntent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrders);
                startActivity(declineIntent);
            }
        });

        jobFinishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jobFinishedIntent = new Intent(OrderDetail.this, TimeAndExpenses.class);
                jobFinishedIntent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrders);
                jobFinishedIntent.putExtra(CURRENT_WORKORDER_MESSAGE, currentWorkOrder);
                startActivity(jobFinishedIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackClicked();
            finish();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(OrderDetail.this, Login.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackClicked(){
        Intent intent = new Intent(this, OrderList.class);
        intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrders);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public void onDownloadComplete(String results, String message) throws JSONException {

    }
}
