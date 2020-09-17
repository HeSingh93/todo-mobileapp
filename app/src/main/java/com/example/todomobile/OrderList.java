package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todomobile.models.Employee;
import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends ToDoActivity {

    private ArrayList<WorkOrder> OrderlistActiveEmployee;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        final Intent intent = getIntent();
        intent.setExtrasClassLoader(WorkOrder.class.getClassLoader());
        OrderlistActiveEmployee = intent.getParcelableArrayListExtra(WORKORDER_LIST_MESSAGE);

        final ListView orderListView = findViewById(R.id.listviewID);
        CustomListAdapter adapter = new CustomListAdapter(this, OrderlistActiveEmployee);
        orderListView.setAdapter(adapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                intent.putExtra(CURRENT_WORKORDER_MESSAGE, (WorkOrder) parent.getItemAtPosition(position));
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, OrderlistActiveEmployee);
                startActivity(intent);
            }
        });
    }
}