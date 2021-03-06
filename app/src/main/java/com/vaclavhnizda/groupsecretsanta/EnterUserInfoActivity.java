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

import com.vaclavhnizda.groupsecretsanta.Data.DBConnection;
import com.vaclavhnizda.groupsecretsanta.Data.SecretSantaContact;

import java.util.Iterator;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterUserInfoActivity extends AppCompatActivity {

    private int numberOfPeople = 0;
    private final String COUNTKEY = "total_users";
    private final String SUBKEYNAME = "userName";
    private final String SUBKEYEMAIL = "userEmail";

    //Strings to use
    @BindString(R.string.user_data_instructions_part1) String numPeopleInstructionTxt1;
    @BindString(R.string.user_data_instructions_part2) String numPeopleInstructionTxt2;
    @BindString(R.string.user_name_hint_text) String nameHintText;
    @BindString(R.string.user_email_hint_text) String emailHintText;
    @BindView(R.id.user_list_layout) LinearLayout userListLayout; //list for adding users

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        numberOfPeople = intent.getIntExtra(MainActivity.PEOPLE_COUNT,1);

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
            personsName.setId(500 + rowNumber);

            //Column for email address
            EditText emailAddress = new EditText(context);
            emailAddress.setBackground(getBorderStyle());
            emailAddress.setHint(emailHintText);
            emailAddress.setTextColor(Color.BLACK);
            emailAddress.setHintTextColor(Color.GRAY);
            emailAddress.setId(1500 + rowNumber);

            //Add all values to the row
            rowLayout.addView(numbering);   //add value to the row
            rowLayout.addView(personsName); //add value to the row
            rowLayout.addView(emailAddress);

            mainLayout.addView(rowLayout);  //add complete row to the list
        }

        //TODO load saved data from previous session!
        loadData();

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
        editor.putInt(COUNTKEY,numberOfPeople);

        for(int count = 1; count <= numberOfPeople; count++){
            EditText name = (EditText)findViewById(500+count);
            EditText email = (EditText)findViewById(1500+count);
            editor.putString(SUBKEYNAME + count, name.getText().toString());
            editor.putString(SUBKEYEMAIL + count, email.getText().toString());
        }

        editor.commit();
    }

    private void loadData(){
        //setup local resource
        String key = getResources().getString(R.string.preference_file_key);
        SharedPreferences sharedPref = getApplication().getSharedPreferences(key, Context.MODE_PRIVATE);

        int savedCount = sharedPref.getInt(COUNTKEY,numberOfPeople);
        if (savedCount > numberOfPeople)
            savedCount = numberOfPeople;
        for(int count = 1; count <= numberOfPeople; count++){
            //check if data was saved
            if(sharedPref.contains(SUBKEYNAME + count)){
                //update name field
                String name = sharedPref.getString(SUBKEYNAME + count,"");
                EditText nameField = (EditText)findViewById(500+count);
                nameField.setText(name);

                //update email field
                String email = sharedPref.getString(SUBKEYEMAIL + count, "");
                EditText emailField = (EditText)findViewById(1500+count);
                emailField.setText(email);


            }
        }

        //TODO remove residual data, saves of data higher than current user count could still be out there..
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
