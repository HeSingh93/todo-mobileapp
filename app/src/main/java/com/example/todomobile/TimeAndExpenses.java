package com.example.todomobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todomobile.models.Expense;
import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TimeAndExpenses extends ToDoActivity {

    private static final String TAG = "TimeAndExpenses";

    private WorkOrder finishedWorkOrder;
    private ArrayList<WorkOrder> workOrderList;
    private List<Expense> expenses;

    private LinearLayout mainLayout;
    private LinearLayout expenseLayout;

    private TextView labelWorkTime;
    private TextView labelExpences;

    private EditText textTravelTime;
    private EditText textWorkStartTime;
    private EditText textWorkEndTime;
    private EditText textCost;
    private EditText textDescriptionOfCost;
    private EditText textNotes;

    private Button buttonAddExpense;
    private Button buttonSave;

    private List<EditText> amountEditTexts;
    private List<EditText> descriptionEditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_expenses);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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

        mainLayout = findViewById(R.id.linearlayout3);
        expenseLayout = findViewById(R.id.expenseLayout);

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

        amountEditTexts = new ArrayList<>();
        amountEditTexts.add(textCost);

        descriptionEditTexts = new ArrayList<>();
        descriptionEditTexts.add(textDescriptionOfCost);

        buttonSave = findViewById(R.id.buttonSaveTimeAndExpences);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishedWorkOrder.setStatus(STATUS_DONE);
                finishedWorkOrder.setTravelHours(Double.parseDouble(textTravelTime.getText().toString()));
                finishedWorkOrder.setTimeStarted(textWorkStartTime.getText().toString());
                finishedWorkOrder.setTimeFinished(textWorkEndTime.getText().toString());

                for (int i = 0; i < amountEditTexts.size(); i++) {
                    Expense jobExpenses = new Expense(Double.parseDouble((amountEditTexts.get(i).getText().toString())),
                            descriptionEditTexts.get(i).getText().toString(), finishedWorkOrder.getId());
                    finishedWorkOrder.addExpense(jobExpenses);
                }

                finishedWorkOrder.setComment(textNotes.getText().toString());

                Log.d(TAG, "onClick: FinishedWorkOrder: " + finishedWorkOrder.toString());

                Intent timeAndExpencesFinishedIntent = new Intent(TimeAndExpenses.this, OrderList.class);
                timeAndExpencesFinishedIntent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrderList);
                startActivity(timeAndExpencesFinishedIntent);
            }
        });

        buttonAddExpense = findViewById(R.id.extraExpenseButton);
        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: Extra expense button clicked");

                int amountWidth = dpToPixelsConversion(90);
                int descriptionWidth = dpToPixelsConversion(200);
                int height = dpToPixelsConversion(50);
                int marginLeftRight = dpToPixelsConversion(10);
                int marginTop = dpToPixelsConversion(5);
                int padding = dpToPixelsConversion(5);

                EditText newAmountEditText = new EditText(TimeAndExpenses.this);
                newAmountEditText.setHint(R.string.cost);

                LinearLayout.LayoutParams layoutParamsAmount = new LinearLayout.LayoutParams(amountWidth, height);
                layoutParamsAmount.setMargins(marginLeftRight, marginTop, marginLeftRight, 0);

                newAmountEditText.setLayoutParams(layoutParamsAmount);
                newAmountEditText.setPadding(padding,0,0,0);
                newAmountEditText.setBackgroundResource(R.drawable.border_style);
                newAmountEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

                amountEditTexts.add(newAmountEditText);

                EditText newDescriptionEditText = new EditText(TimeAndExpenses.this);
                newDescriptionEditText.setHint(R.string.description);

                LinearLayout.LayoutParams layoutParamsDescription = new LinearLayout.LayoutParams(descriptionWidth, height);
                layoutParamsDescription.setMargins(marginLeftRight, marginTop, 0, 0);

                newDescriptionEditText.setLayoutParams(layoutParamsDescription);
                newDescriptionEditText.setPadding(padding,0,0,0);
                newDescriptionEditText.setBackgroundResource(R.drawable.border_style);

                descriptionEditTexts.add(newDescriptionEditText);

                LinearLayout newLayout = new LinearLayout(TimeAndExpenses.this);
                newLayout.setOrientation(LinearLayout.HORIZONTAL);
                newLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                newLayout.addView(newAmountEditText);
                newLayout.addView(newDescriptionEditText);

                expenseLayout.addView(newLayout);
                expenseLayout.invalidate();
                mainLayout.invalidate();
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
            startActivity(new Intent(TimeAndExpenses.this, Login.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackClicked(){
        Intent intent = new Intent(this, OrderDetail.class);
        intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, workOrderList);
        intent.putExtra(CURRENT_WORKORDER_MESSAGE, finishedWorkOrder);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    private int dpToPixelsConversion(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(dp*scale + 0.5f);
    }
}