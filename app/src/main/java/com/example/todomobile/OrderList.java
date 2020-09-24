package com.example.todomobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todomobile.models.Employee;
import com.example.todomobile.models.WorkOrder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderList extends ToDoActivity {
    private static final String TAG = "OrderList";

    private ArrayList<WorkOrder> orderlistActiveEmployee;
    private ArrayList<WorkOrder> orderListNotAccepted;
    private ArrayList<WorkOrder> orderListAccepted;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Log.d(TAG, "onCreate: OrderList started");

        final Intent intent = getIntent();
        intent.setExtrasClassLoader(WorkOrder.class.getClassLoader());
        orderlistActiveEmployee = intent.getParcelableArrayListExtra(WORKORDER_LIST_MESSAGE);

        orderListAccepted = new ArrayList<>();
        orderListNotAccepted = new ArrayList<>();

        for ( WorkOrder workOrder : orderlistActiveEmployee ) {
            if ( workOrder.getStatus() == STATUS_ASSIGNED ) {
                orderListNotAccepted.add(workOrder);
            } else if ( workOrder.getStatus() == STATUS_ACCEPTED ) {
                orderListAccepted.add(workOrder);
            }
        }

        final ListView orderListNotAcceptedView = findViewById(R.id.listviewNotAcceptedWorkorderID);
        CustomListAdapter adapterNotAccepted = new CustomListAdapter(this, orderListNotAccepted);
        orderListNotAcceptedView.setAdapter(adapterNotAccepted);
        orderListNotAcceptedView.setMinimumHeight(getListViewHeight(orderListNotAcceptedView));

        final ListView orderListView = findViewById(R.id.listviewID);
        CustomListAdapter adapter = new CustomListAdapter(this, orderListAccepted);
        orderListView.setAdapter(adapter);
        orderListView.setMinimumHeight(getListViewHeight(orderListView));


        Collections.sort(orderListNotAccepted, new Comparator<WorkOrder>() {
            @Override
            public int compare(WorkOrder o1, WorkOrder o2) {
                return o1.getDateTimeInfo().compareTo(o2.getDateTimeInfo());
            }
        });

        Collections.sort(orderListAccepted, new Comparator<WorkOrder>() {
            @Override
            public int compare(WorkOrder o1, WorkOrder o2) {
                return o1.getDateTimeInfo().compareTo(o2.getDateTimeInfo());
            }
        });

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                intent.putExtra(CURRENT_WORKORDER_MESSAGE, (WorkOrder) parent.getItemAtPosition(position));
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, orderlistActiveEmployee);
                startActivity(intent);
            }
        });

        orderListNotAcceptedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                intent.putExtra(CURRENT_WORKORDER_MESSAGE, (WorkOrder) parent.getItemAtPosition(position));
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, orderlistActiveEmployee);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(OrderList.this, Login.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public int getListViewHeight(ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));

        return par.height;
    }

    @Override
    public void onDownloadComplete(String results, String message) throws JSONException {

    }
}