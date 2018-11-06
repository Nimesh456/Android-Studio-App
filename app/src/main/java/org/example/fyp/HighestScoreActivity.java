package org.example.fyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nimesh on 11/03/2018.
 */

public class HighestScoreActivity extends AppCompatActivity {

//    Button btn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_highest_score);
//
//        TextView txtScore = (TextView) findViewById(R.id.textScore);
//        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
//        btn = (Button) findViewById(R.id.mainmenubtn);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent (HighestScoreActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });
//        // receive the score from last activity by Intent
//        Intent intent = getIntent();
//        int score = intent.getIntExtra("score", 0);
//        // display current score
//        txtScore.setText("Your score: " + score);
//
//        // use Shared preferences to save the best score
//        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
//        int highscore = mypref.getInt("highscore",0);
//        if(highscore>= score)
//            txtHighScore.setText("High score: "+highscore);
//        else
//        {
//            txtHighScore.setText("New highscore: "+score);
//            SharedPreferences.Editor editor = mypref.edit();
//            editor.putInt("highscore", score);
//            editor.commit();
//        }
//    }
//
//    public void onClick(View view) {
//        Intent intent = new Intent(HighestScoreActivity.this, QuizActivity.class);
//        startActivity(intent);
//    }
}



