package com.vaclavhnizda.groupsecretsanta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EnterUserInfoActivity extends AppCompatActivity {

    private int numberOfPeople = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_info);

        Intent intent = getIntent();
        numberOfPeople = intent.getIntExtra(MainActivity.PEOPLE_COUNT,1);

        //Strings to use
        String numPeopleInstructionTxt1 = getResources().getString(R.string.user_data_instructions_part1);
        String numPeopleInstructionTxt2 = getResources().getString(R.string.user_data_instructions_part2);
        String emailHintText = " <" + getResources().getString(R.string.user_email_hint_text) + "> ";
        String nameHintText =  " <" + getResources().getString(R.string.user_name_hint_text)  + "> ";

        TextView textView = (TextView)findViewById(R.id.user_instructions);
        textView.setText(numPeopleInstructionTxt1 + " " + numberOfPeople + " " + numPeopleInstructionTxt2);

        //Create a list of text fields for entering all the user data.
        Context context = getApplicationContext();
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.user_list_layout);

        for(int rowNumber = 1; rowNumber <= numberOfPeople; rowNumber++) {
            LinearLayout rowLayout = new LinearLayout(context);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);

            //Numbering for each row
            TextView numbering = new TextView(context);
            //temporary fix for numbering under double digit, might need to change layout to grid.. for better organization..
            String rowNumFormatted = (rowNumber < 10 ? "  " : "") + rowNumber;
            numbering.setText(rowNumFormatted + ". ");
            numbering.setTextColor(Color.BLACK);

            //Column for persons name
            EditText personsName = new EditText(context);
            personsName.setBackground(getBorderStyle());
            personsName.setHint(nameHintText);
            personsName.setTextColor(Color.BLACK);
            personsName.setHintTextColor(Color.GRAY);

            //Column for email address
            EditText emailAddress = new EditText(context);
            emailAddress.setBackground(getBorderStyle());
            emailAddress.setHint(emailHintText);
            emailAddress.setTextColor(Color.BLACK);
            emailAddress.setHintTextColor(Color.GRAY);

            //Add all values to the row
            rowLayout.addView(numbering);   //add value to the row
            rowLayout.addView(personsName); //add value to the row
            rowLayout.addView(emailAddress);


            mainLayout.addView(rowLayout);  //add complete row to the list
        }

        //TODO load saved data from previous session!

        //TODO add a button to move to next fields (and save data to database)
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        
        //setup local resource
        String key = getResources().getString(R.string.preference_file_key);
        SharedPreferences sharedPref = getApplication().getSharedPreferences(key, Context.MODE_PRIVATE);
        //edit data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("total_users",numberOfPeople);


        editor.commit();
    }

    private ShapeDrawable getBorderStyle(){
        // Create a border programmatically
        ShapeDrawable customShape = new ShapeDrawable(new RectShape());
        customShape.getPaint().setColor(Color.BLACK);
        customShape.getPaint().setStyle(Paint.Style.STROKE);
        customShape.getPaint().setStrokeWidth(3);
        customShape.setPadding(10,10,10,10);
        return customShape;
    }
}
