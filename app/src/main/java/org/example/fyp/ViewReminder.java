package org.example.fyp;

import android.app.Activity;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static org.example.fyp.ReminderActivity.getDate;

public class ViewReminder extends Activity {
    private static final String TAG="ListDataActivity";
    ReminderDatabase ReminderDatabase;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);
        listView = (ListView) findViewById(R.id.list_view);
        ReminderDatabase = new ReminderDatabase(this);

        try {
            ViewList();

        }catch (NullPointerException e){
            Log.d("FYP","null");
        }
    }
    private void ViewList() {


        Log.d(TAG, "ViewList: Displaying data in the ListView.");

        if(!getDate.isEmpty()) {
            String date = getDate.toString(); //gets all information from selected date
            Cursor dataDate = ReminderDatabase.getDate(date);

            //get the data and append to a list

            ArrayList<String> listData = new ArrayList<>();


            while (dataDate.moveToNext()) {
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(dataDate.getString(2) + " , " + dataDate.getString(3));
            }

            //create the list adapter and set the adapter

            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            Collections.sort(listData); // sorts time in ascending order
            listView.setAdapter(adapter); //shows the data in list view

        }else{
            Toast.makeText(getApplicationContext(),"Select a Date to view list of reminders",Toast.LENGTH_SHORT);
        }
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }





}
