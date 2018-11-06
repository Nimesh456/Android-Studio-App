package org.example.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    private EditText editTextResetEmail;
    private Button buttonReset, buttonback;
    private FirebaseAuth mAuth;
    //private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        editTextResetEmail = (EditText) findViewById(R.id.editTextResetEmail);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonback = (Button) findViewById(R.id.buttonback);
        mAuth = FirebaseAuth.getInstance();


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = editTextResetEmail.getText().toString().trim();

                if (useremail.equals("")) {
                    Toast.makeText(ResetActivity.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetActivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ResetActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(ResetActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                openLogin();
            }
        });

    }
        private void openLogin() {
          Intent intent = new Intent(this, LoginActivity.class);
          startActivity(intent);

    }

    }

