package com.example.todomobile.api.json;

import android.os.AsyncTask;
import android.util.Log;

import com.example.todomobile.Login;
import com.example.todomobile.ToDoActivity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * @author Karin
 */
public class APIRequester extends AsyncTask<String, Void, String> {

    private static final String TAG = "APIRequester";

    private WeakReference<ToDoActivity> context;
    private String message;


    public APIRequester(ToDoActivity context, String message) {
        this.context =  new WeakReference<>(context);
        this.message = message;
    }



    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: start");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(strings[0]);
            Log.d(TAG, "doInBackground: url is: " + url);

            connection =  (HttpURLConnection) url.openConnection();
            if ((message.equals(ToDoActivity.LOGIN_MESSAGE)) || (message.equals(ToDoActivity.ACCEPT_ORDER_MESSAGE))
                || (message.equals(ToDoActivity.DECLINE_ORDER_MESSAGE))){
                connection.setRequestMethod("POST");
            } /*else {
                connection.setRequestMethod("POST");
            }*/
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");

            connection.setDoOutput(true);
            connection.connect();

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

            if (strings.length > 1) {
                wr.write(strings[1]);
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
            InputStream inputStream = responseCode/100 == 2 ? connection.getInputStream() : connection.getErrorStream();
            reader = new BufferedReader((new InputStreamReader(inputStream)));

            StringBuilder resultSb = new StringBuilder();
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                resultSb.append(line).append("\n");
            }

            if (responseCode/100 == 2) {
                return resultSb.toString();
            } else {
                throw new IOException((resultSb.toString()));
            }


        } catch(MalformedURLException e) {
            Log.e(TAG, "doInBackground: Invalid URL " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: IOException reading data: " + e.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: Problem closing stream " + e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        if(context.get() != null) {
            try {
                context.get().onDownloadComplete(s, message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
