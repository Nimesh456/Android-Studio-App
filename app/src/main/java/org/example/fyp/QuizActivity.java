package org.example.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.example.fyp.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nimesh on 11/03/2018.
 */

public class QuizActivity extends AppCompatActivity {

    // private QuestionBank mQuestionLibrary = new QuestionBank();
    // private TextView mScoreView;   // view for current total score
    private Toolbar mToolbar;
    private TextView question, score;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private QuestionBank mQuestions = new QuestionBank();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLength = mQuestions.mQuestions.length;

    Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mToolbar = (findViewById(R.id.quiz_toolbar));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quiz");

        r = new Random();

        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);

        score = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);

        updateQuestion(r.nextInt(mQuestionsLength));


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice1.getText() == mAnswer) {
                    if(mScore<9) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }else{
                        mScore++;
                        gamewon();
                    }
                } else {
                    gameOver();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice2.getText() == mAnswer) {
                    if(mScore<9) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }else{
                        mScore++;
                        gamewon();
                    }
                } else {
                    gameOver();
                }

            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice3.getText() == mAnswer) {
                    if(mScore<9) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }else{
                        mScore++;
                        gamewon();
                    }
                } else {
                    gameOver();
                }

            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice4.getText() == mAnswer) {
                    if(mScore<9) {
                        mScore++;
                        score.setText("Score: " + mScore);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }else{
                        mScore++;
                        gamewon();
                    }
                } else {
                    gameOver();
                }

            }
        });

    }


    private void updateQuestion(int num) {
        question.setText(mQuestions.getQuestion(num));
        mButtonChoice1.setText(mQuestions.getChoice1(num));
        mButtonChoice2.setText(mQuestions.getChoice2(num));
        mButtonChoice3.setText(mQuestions.getChoice3(num));
        mButtonChoice4.setText(mQuestions.getChoice4(num));

        mAnswer = mQuestions.getCorrectAnswer(num);

    }

    private void gameOver() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
            alertDialogBuilder
                    .setMessage("Game Over! Your score is " + mScore + " points.")
                    .setCancelable(false)
                    .setPositiveButton("NEW GAME",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                                }
                            })
                    .setNegativeButton("EXIT",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    finish();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_orange_light);
        }

        private void gamewon() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
            alertDialogBuilder
                    .setMessage("Congratulation You won! Your score is " + mScore + " points.")
                    .setCancelable(false)
                    .setPositiveButton("NEW GAME",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                                }
                            })
                    .setNegativeButton("EXIT",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    finish();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_orange_light);
        }


        }


