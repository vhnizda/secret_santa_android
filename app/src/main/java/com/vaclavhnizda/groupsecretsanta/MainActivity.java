package com.vaclavhnizda.groupsecretsanta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    public static String PEOPLE_COUNT = "com.vaclavhnizda.groupsecretsanta.MESSAGE";
    private int minCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton;

        //iterate over all buttons - we have 8 of them pre-created
        for(int x = 0; x < 8; x++){
            //Used to get resource id based on "button" + int ID
            int ResID = getResources().getIdentifier("button" + (x + 1),"id",getPackageName());
            //Retrieve button from ID found
            myButton = (Button)findViewById(ResID);
            //Set the text of the button
            myButton.setText((minCount + x) + " people");
            //Set a tag for later retrieval
            myButton.setTag(minCount + x);
            //Create a listener link to the button click
            myButton.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        //Only buttons are linked currently so it's safe to assume this is a button
        Button buttonClicked = (Button)view;
        //Get my custom int tag representing the number of people they want to include in secret santa
        int personCount = (int)buttonClicked.getTag();
        //TODO This is where the next view is launched for the app.
        //TODO currently it changes the text of the button for testing purposes.
        buttonClicked.setText("Button " + (personCount - minCount + 1) + " OK.");
        Intent intent = new Intent(this, EnterUserInfoActivity.class);
        intent.putExtra(PEOPLE_COUNT,personCount);
        startActivity(intent);
    }
}
