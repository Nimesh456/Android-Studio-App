package org.example.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class ReminderActivity extends AppCompatActivity {
    private Button buttoncreate;
    private Button buttonview;
    private Button buttondel;
    private Button buttonsearch;
    public static String getDate;
    public static CalendarView Calendar;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        mToolbar = (findViewById(R.id.reminder_toolbar));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reminder");

        buttoncreate = (Button) findViewById(R.id.createbtn);
        buttonview = (Button) findViewById(R.id.viewbtn);
        buttondel = (Button) findViewById(R.id.deletebtn);
        buttonsearch = (Button) findViewById(R.id.searchbtn);
        Calendar = (CalendarView) findViewById(R.id.calendar);

        Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int monthextra = month+1;
                Toast.makeText(getBaseContext(), "Selected date " + dayOfMonth + "/" + monthextra + "/" + year, Toast.LENGTH_LONG).show();
                getDate = year + "/" + monthextra + "/" + dayOfMonth;
            }

        });
        buttoncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReminderActivity.this, CreateReminder.class);
                startActivity(intent);
            }
        });
        buttonview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(ReminderActivity.this, ViewReminder.class);
                startActivity(intent);
            }
        });
        buttondel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(ReminderActivity.this, DeleteReminder.class);
                startActivity(intent);
            }
        });
        buttonsearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(ReminderActivity.this, SearchReminder.class);
                startActivity(intent);
            }
        });
    }
}
