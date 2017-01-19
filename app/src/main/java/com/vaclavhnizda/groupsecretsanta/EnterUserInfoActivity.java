package com.vaclavhnizda.groupsecretsanta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EnterUserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_info);

        Intent intent = getIntent();
        int numberOfPeople = intent.getIntExtra(MainActivity.PEOPLE_COUNT,1);

        TextView textView = (TextView)findViewById(R.id.user_instructions);
        textView.setText("Please fill out the information for all " + numberOfPeople + " people.");

        //TODO create a list of text fields for entering all the user data.
        //TODO add a button to move to next fields (and save data to database)
    }
}
