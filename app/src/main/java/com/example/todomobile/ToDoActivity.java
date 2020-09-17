package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ToDoActivity extends AppCompatActivity {

    protected String WORKORDER_LIST_MESSAGE = "WORKORDER_LIST_MESSAGE";
    protected String CURRENT_WORKORDER_MESSAGE = "CURRENT_WORKORDER_MESSAGE";

    protected int STATUS_UNASSIGNED = 1;
    protected int STATUS_ASSIGNED = 2;
    protected int STATUS_ACCEPTED = 3;
    protected int STATUS_DONE = 4;

}
