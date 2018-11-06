package org.example.fyp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchReminder extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";

    ReminderDatabase ReminderDatabase;
    EditText search;
    private ListView listView;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_reminder);
        listView = (ListView) findViewById(R.id.list_view3);
        search = (EditText) findViewById(R.id.edit_text_search);

        ReminderDatabase = new ReminderDatabase(this);


        ViewList();


        search.addTextChangedListener(new TextWatcher() { //matches the listview
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().equals("")) {
                    ViewList();
                } else {
                    adapter.getFilter().filter(s);
                    listView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void ViewList() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");


        final Cursor dataAll = ReminderDatabase.getData1();


        ArrayList<String> listData = new ArrayList<>();
        while (dataAll.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(dataAll.getString(3) +" , "+ dataAll.getString(4));
        }
        //create the list adapter and set the adapter

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        listView.setVisibility(View.INVISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String item = adapterView.getItemAtPosition(i).toString();
                String[] split = item.split(" , ");
                String head = split[0];


                Log.d(TAG, "onItemClick: You Clicked on " + item);

                Cursor getID = ReminderDatabase.getitemID(head); //get the id associated with that name
                Cursor data = ReminderDatabase.getData2(head); //getting everything related to the title of the item
                ArrayList<String> dataArray = new ArrayList<>();
                while (data.moveToNext()){
                    dataArray.add(data.getString(1) + "," + data.getString(2) + "," + data.getString(3) + "," + data.getString(4));
                }
                String s = dataArray.toString();
                String[] parts = s.split(",");
                String date = parts[0].substring(1, parts[0].length());
                String time = parts[1];
                String title = parts[2];
                String detail = parts[3].substring(0, parts[3].length()-1);
                int itemID = -1;
                while (getID.moveToNext()) {

                    itemID = getID.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(SearchReminder.this, SearchEvent.class);
                    editScreenIntent.putExtra("id", itemID);
                    editScreenIntent.putExtra("date", date);
                    editScreenIntent.putExtra("time", time);
                    editScreenIntent.putExtra("title", title);
                    editScreenIntent.putExtra("detail", detail);
                    startActivity(editScreenIntent);

                } else {
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}



