package org.example.fyp;

import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import static org.example.fyp.ReminderActivity.getDate;

public class CreateReminder extends AppCompatActivity {

    ReminderDatabase ReminderDatabase;
    private static final String TAG = "MainActivity";
    private EditText textTitle;
    private EditText textTime;
    private EditText textDetails;
    Button Savebutton;
    private ListView suggList;
    private Handler guiThread;
    private ExecutorService suggThread;
    private Runnable updateTask;
    private Future<?> suggPending;
    private List<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);



        textTitle=(EditText) findViewById(R.id.editTitle);
        textTime = (EditText) findViewById(R.id.editTime);
        textDetails = (EditText) findViewById(R.id.editDetails);
        Savebutton = (Button) findViewById(R.id.Savebutton);

        ReminderDatabase = new ReminderDatabase(this);

        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = getDate.toString();
                String time = textTime.getText().toString();
                String title = textTitle.getText().toString();
                String details = textDetails.getText().toString();

                Cursor Date = ReminderDatabase.getDate(date);
                ArrayList list = new ArrayList();//moves to next step
                while (Date.moveToNext()){
                    list.add(Date.getString(3));
                }

                if(list.contains(title)) { // if title already exists display error message
                    toastMessage("Appointment already exists,Please choose a different event title");
                } else{
                    AddData(date, time, title, details);
                    textTitle.setText(null);
                    textTime.setText(null);
                    textDetails.setText(null);
                }

            }

        });

    }

    public void AddData(String date, String time, String title, String details) {
        boolean insertData = ReminderDatabase.addData(date, time, title, details);

        if (insertData) {
            toastMessage("Data Successfully SAVED!");
        } else {
            toastMessage("Something went wrong");
        }
    }


//    private void queueUpdate(long delayMillis) {
//        // Cancels previous update if it hasn't started
//        guiThread.removeCallbacks(updateTask);
//        // Starts an update if nothing happens after few seconds
//        guiThread.postDelayed(updateTask, delayMillis);
//    }




//    private void setAdapters() { //sets the adapter that provides data
//        items = new ArrayList<String>();
//        adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, items);
//    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
