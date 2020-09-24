package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public abstract class ToDoActivity extends AppCompatActivity {

    public static final String WORKORDER_LIST_MESSAGE = "WORKORDER_LIST_MESSAGE";
    public static final String CURRENT_WORKORDER_MESSAGE = "CURRENT_WORKORDER_MESSAGE";

    public static final String BASE_URL = "http://10.0.2.2:8080/api";

    public static final int STATUS_UNASSIGNED = 1;
    public static final int STATUS_ASSIGNED = 2;
    public static final int STATUS_ACCEPTED = 3;
    public static final int STATUS_DONE = 4;

     public abstract void onDownloadComplete(String results, String message) throws JSONException;
}
