package org.example.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by nimesh on 05/03/2018.
 */

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    private Toolbar mToolbar;

    private FirebaseRecyclerAdapter<Disease,ViewHolder> searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Disease");


        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mToolbar = (findViewById(R.id.search_toolbar));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Symptom");


        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });



    }

    private void firebaseUserSearch(final String searchText) {


        Query firebaseSearchQuery = mUserDatabase.orderByChild("search_name").startAt(searchText).endAt(searchText + "\uf8ff");
        searchAdapter = new FirebaseRecyclerAdapter<Disease, ViewHolder>(

                Disease.class,
                R.layout.list_layout,
                ViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, final Disease model, int position) {

                viewHolder.setDetails(model.getSearch_name());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(SearchActivity.this);
                        } else {
                            builder = new AlertDialog.Builder(SearchActivity.this);
                        }
                        builder.setTitle("Recommended Medicines")
                                .setMessage(model.getDetails().toString())

                                .setPositiveButton(Html.fromHtml("<font color='#303f9f'>OK</font>"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                                    }

                                })

                                .setIcon(R.drawable.ic_healing_black_24dp)
                                .show();
                    }
                });

            }
        };

        mResultList.setAdapter(searchAdapter);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setDetails(String Name){
            TextView name = (TextView)mView.findViewById(R.id.name_text);

            name.setText(Name);
        }
//


    }

}
