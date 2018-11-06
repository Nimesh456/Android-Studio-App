package org.example.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nimesh on 22/04/2018.
 */

public class SearchEvent extends Activity {
    private static final String TAG = "EditDataActivity";

    private TextView title, date, time, details;
    ReminderDatabase ReminderDatabase;

    private String selectTitle,selectTime,selectDate, selectDetail;
    private int selectID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);


        date = (TextView) findViewById(R.id.date);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        details = (TextView) findViewById(R.id.details);
        ReminderDatabase = new ReminderDatabase(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectID = receivedIntent.getIntExtra("id",-1); // -1 is just the default value

        //now get the name we passed as an extra
        selectDate=receivedIntent.getStringExtra("date");
        selectTitle = receivedIntent.getStringExtra("title");
        selectTime = receivedIntent.getStringExtra("time");
        selectDetail = receivedIntent.getStringExtra("detail");

        //set the text to show the current selected name
        title.setText(selectTitle);
        time.setText(selectTime);
        date.setText(selectDate);
        details.setText(selectDetail);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
