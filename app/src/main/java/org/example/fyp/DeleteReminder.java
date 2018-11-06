package org.example.fyp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static org.example.fyp.ReminderActivity.getDate;

public class DeleteReminder extends AppCompatActivity {


    ReminderDatabase ReminderDatabase;
    private ListView listView;
    private EditText textDelete;
    private Button btnDelete;
    private int itemID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_reminder);
        listView = (ListView) findViewById(R.id.listView);
        textDelete = (EditText) findViewById(R.id.edit_text_search);
        btnDelete = (Button) findViewById(R.id.Delbtn);
        ReminderDatabase = new ReminderDatabase(this);

        try {
            ViewList();

        } catch (NullPointerException e){
            Log.d("FYP","NULL POINTER");
        }

        // deletes everything from database


    }


    private void ViewList() {
        Log.d(TAG, "populateViewList: Displaying data in the ListView.");

        if(!getDate.isEmpty()) {
            String date = getDate.toString();
            Cursor dataDate = ReminderDatabase.getDate(date);

            //get the data and append to a list
            ArrayList<String> listData = new ArrayList<>();


            while (dataDate.moveToNext()) {
                //gets the value from the database in column 1
                //then adds it to the ArrayList
                listData.add(dataDate.getString(2) + " , " + dataDate.getString(3));
            }

            final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"Select a Date to view list of reminders",Toast.LENGTH_SHORT);
        }
        // set an onItemClickListener to the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String[] parts = item.split(" , ");
                String time = parts[0];
                final String title = parts[1];


                Log.d(TAG, "onItemClick: You Clicked on " + title);
                Cursor data = ReminderDatabase.getitemID2(time, title);//get ID assosciated with time and title
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    textDelete.setText(title);
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(DeleteReminder.this);
                            builder.setTitle("Alert Dialog");
                            //Set alert icon
                            //Set Dialog Message
                            builder.setMessage("Would you like to delete the event?");
                            //Button function
                            builder.setPositiveButton(Html.fromHtml("<font color='#303f9f'>Yes</font>"), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ReminderDatabase.DeleteData(itemID, title);
                                    Intent intent = new Intent(DeleteReminder.this, ReminderActivity.class);
                                    startActivity(intent);

                                }
                            });
                            builder.setNegativeButton(Html.fromHtml("<font color='#303f9f'>No</font>"), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Do nothing
                                }
                            });

                            AlertDialog alertDialog = builder.create(); //create alert dialog

                            alertDialog.show();//show alert dialog


                        }
                    });

                }
            }
        });
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}



