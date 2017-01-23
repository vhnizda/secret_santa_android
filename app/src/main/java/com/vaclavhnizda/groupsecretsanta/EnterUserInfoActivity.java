package com.vaclavhnizda.groupsecretsanta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        //Create a list of text fields for entering all the user data.
        Context context = getApplicationContext();
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.user_list_layout);


        int rowNumber = 1;
        LinearLayout rowLayout = new LinearLayout(context);
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView numbering = new TextView(context);
        numbering.setText(rowNumber + ". ");
        numbering.setTextColor(Color.BLACK);
        rowLayout.addView(numbering);

        EditText userInfo = new EditText(context);
        userInfo.setHint("<enter name here>");
        userInfo.setTextColor(Color.BLACK);
        userInfo.setHintTextColor(Color.GRAY);

        rowLayout.addView(userInfo);

        mainLayout.addView(rowLayout);
        //TODO add a button to move to next fields (and save data to database)
    }
}
