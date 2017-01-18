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
        Button[] myButtons = new Button[8];
        for(int x = 0; x < 8; x++){
            int ResID = getResources().getIdentifier("button" + (x + 1),"id",getPackageName());
            myButtons[x] = (Button)findViewById(ResID);
            myButtons[x].setText((minCount + x) + " people");
            myButtons[x].setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {


        switch(view.getId())
        {
            case R.id.button1:
                ((Button)view).setText("Button 1 pressed!");
                break;
            case R.id.button2:
                ((Button)view).setText("Button 2 pressed!");
                break;
            case R.id.button3:
                ((Button)view).setText("Button 3 pressed!");
                break;
            case R.id.button4:
                ((Button)view).setText("Button 4 pressed!");
                break;
            case R.id.button5:
                ((Button)view).setText("Button 5 pressed!");
                break;
            case R.id.button6:
                ((Button)view).setText("Button 6 pressed!");
                break;
            case R.id.button7:
                ((Button)view).setText("Button 7 pressed!");
                break;
            case R.id.button8:
                ((Button)view).setText("Button 8 pressed!");
                break;
        }
    }
}
