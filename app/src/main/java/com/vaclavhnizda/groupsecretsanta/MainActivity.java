package com.vaclavhnizda.groupsecretsanta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.NumberPicker;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    public static String PEOPLE_COUNT = "com.vaclavhnizda.groupsecretsanta.MESSAGE";
    private int minCount = 3;
    @BindView(R.id.numberPicker) NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Button myButton;

        NumberPicker myNumPicker = (NumberPicker)findViewById(R.id.numberPicker);
        myNumPicker.setMinValue(3);
        myNumPicker.setMaxValue(100);

        //TODO create a theme to auto colorize this, if possible and set font
//        TextView tv1 = (TextView)myNumPicker.getChildAt(0);
//        tv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24.f);

//        mainButton.setOnClickListener(this);

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
            //myButton.setOnClickListener(this);
        }
    }


    @OnClick(R.id.button_main_activity)
    public void onClick(View view){
        int numberOfPeople = numberPicker.getValue();
        //This is where the next activity is launched for the app.
        Intent intent = new Intent(this, EnterUserInfoActivity.class);
        intent.putExtra(PEOPLE_COUNT,numberOfPeople);
        startActivity(intent);
    }
//    @Override
//    public void onClick(View view) {
//        //Only buttons are linked currently so it's safe to assume this is a button
//        Button buttonClicked = (Button)view;
//        //Get my custom int tag representing the number of people they want to include in secret santa
//        int personCount = (int)buttonClicked.getTag();
//
//        //This is where the next activity is launched for the app.
//        Intent intent = new Intent(this, EnterUserInfoActivity.class);
//        intent.putExtra(PEOPLE_COUNT,personCount);
//        startActivity(intent);
//    }
}
