package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todomobile.models.Expense;
import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class TimeAndExpenses extends ToDoActivity {

    private WorkOrder finishedWorkOrder;
    private ArrayList<WorkOrder> workOrderList;
    private List<Expense> expenses;

    private TextView labelWorkTime;
    private TextView labelExpences;

    private EditText textTravelTime;
    private EditText textWorkStartTime;
    private EditText textWorkEndTime;
    private EditText textCost;
    private EditText textDescriptionOfCost;
    private EditText textNotes;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_expenses);

        final Intent intent = getIntent();
        intent.setExtrasClassLoader(WorkOrder.class.getClassLoader());

        finishedWorkOrder = intent.getParcelableExtra(CURRENT_WORKORDER_MESSAGE);
        workOrderList = intent.getParcelableArrayListExtra(WORKORDER_LIST_MESSAGE);

        for (WorkOrder workOrder : workOrderList) {
            if (workOrder.equals(finishedWorkOrder)){
                finishedWorkOrder = workOrder;
                break;
            }
        }

        labelWorkTime = findViewById(R.id.labelWorkTime);
        labelExpences = findViewById(R.id.labelWorkTime);

        textTravelTime = findViewById(R.id.textTravelTime);
        textWorkStartTime = findViewById(R.id.textWorkStartTime);
        textWorkEndTime = findViewById(R.id.textWorkEndTime);
        textCost = findViewById(R.id.textCost);
        textDescriptionOfCost = findViewById(R.id.textDecriptionCost);
        textNotes = findViewById(R.id.textNotes);

        textTravelTime.setText("");
        textWorkStartTime.setText("");
        textWorkEndTime.setText("");
        textCost.setText("");
        textDescriptionOfCost.setText("");
        textNotes.setText("");

        buttonSave = findViewById(R.id.buttonSaveTimeAndExpences);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishedWorkOrder.setStatus(STATUS_DONE);
                finishedWorkOrder.setTravelHours(Double.parseDouble(textTravelTime.getText().toString()));
                finishedWorkOrder.setTimeStarted(textWorkStartTime.getText().toString());
                finishedWorkOrder.setTimeFinished(textWorkEndTime.getText().toString());
                Expense jobExpenses = new Expense(Double.parseDouble((textCost.getText().toString())), textDescriptionOfCost.getText().toString());
                finishedWorkOrder.addExpense(jobExpenses);
                finishedWorkOrder.setComment(textNotes.getText().toString());

                Intent timeAndExpencesFinishedIntent = new Intent(TimeAndExpenses.this, OrderList.class);
                timeAndExpencesFinishedIntent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrderList);
                startActivity(timeAndExpencesFinishedIntent);
            }
        });
    }


}