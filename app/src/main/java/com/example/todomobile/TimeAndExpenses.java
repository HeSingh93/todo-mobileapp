package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TimeAndExpenses extends ToDoActivity {

    private TextView labelWorkTime;
    private TextView labelExpences;

    private EditText textTravelTime;
    private EditText textWorkStartTime;
    private EditText textWorkEndTime;
    private EditText textCost;
    private EditText textDescriptionOfCost;
    private EditText textNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_expenses);

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
    }
}