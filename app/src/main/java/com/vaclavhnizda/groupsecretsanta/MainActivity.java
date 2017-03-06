package com.vaclavhnizda.groupsecretsanta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.NumberPicker;

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

    }


    @OnClick(R.id.button_main_activity)
    public void onClick(View view){
        int numberOfPeople = numberPicker.getValue();
        //This is where the next activity is launched for the app.
        Intent intent = new Intent(this, EnterUserInfoActivity.class);//Create intent of new activity
        intent.putExtra(PEOPLE_COUNT,numberOfPeople);//Passing extra values
        startActivity(intent);//start activity
    }
}
