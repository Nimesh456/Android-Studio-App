package org.example.fyp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by nimesh on 19/02/2018.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

        private FirebaseAuth mAuth;
        private CardView locatecard,searchcard,quizcard,newscard,remindercard;
        private Toolbar mToolbar;
        private TextView useremail;



@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        locatecard = (CardView) findViewById(R.id.locate_card);
        searchcard = (CardView) findViewById(R.id.search_card);
        quizcard = (CardView) findViewById(R.id.quiz_card);
        newscard = (CardView) findViewById(R.id.news_card);
        remindercard = (CardView) findViewById(R.id.reminder_card);
        useremail = (TextView) findViewById(R.id.useremail);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() !=null){

        }

        FirebaseUser user = mAuth.getCurrentUser();
        useremail.setText("Welcome: "+user.getEmail());


        mToolbar = (Toolbar) findViewById(R.id.home_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("                         MyHealth");


        locatecard.setOnClickListener(this);
        searchcard.setOnClickListener(this);
        quizcard.setOnClickListener(this);
        newscard.setOnClickListener(this);
        remindercard.setOnClickListener(this);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.home_menu, menu);
                return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
        case R.id.item1:
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
         startActivity(new Intent(this, LoginActivity.class));
         return true;
        case R.id.item2:
            startActivity(new Intent(this, ResetActivity.class));
            return true;
         default:
             return super.onOptionsItemSelected(item);
    }

    }

    @Override
public void onClick (View v){
        Intent i;


        switch (v.getId()) {
        case R.id.locate_card: i = new Intent(this, MapsActivity.class); startActivity(i); break;
                case R.id.search_card: i = new Intent(this, SearchActivity.class); startActivity(i); break;
                case R.id.quiz_card: i = new Intent(this, QuizActivity.class); startActivity(i); break;
                case R.id.news_card: i = new Intent(this, NewsActivity.class); startActivity(i); break;
                case R.id.reminder_card: i = new Intent(this, ReminderActivity.class); startActivity(i); break;
        }
        }
        }

