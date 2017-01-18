package com.vaclavhnizda.groupsecretsanta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private int minCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton;

        //itterate over all buttons
        for(int x = 0; x < 8; x++){
            int ResID = getResources().getIdentifier("button" + (x + 1),"id",getPackageName());
            myButton = (Button)findViewById(ResID);
            myButton.setText((minCount + x) + " people");
            myButton.setTag(minCount + x);
            myButton.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {

        Button buttonClicked = (Button)view;
        int personCount = (int)buttonClicked.getTag();
        buttonClicked.setText("Button " + (personCount - minCount + 1) + " OK.");
    }
}
