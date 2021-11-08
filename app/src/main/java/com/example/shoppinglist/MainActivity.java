package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView[] textArray;
    public static final int TEXT_REQUEST = 1;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textArray = new TextView[10];
        for(int i = 1; i <=10; i++){
            String id = "textView" + i;
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            textArray[i-1]= findViewById(resID);

        }
        if(savedInstanceState != null){
            String[] values = savedInstanceState.getStringArray("values");
            for(int i =0; i < 10; i++){
                textArray[i].setText(values[i]);
            }
            count = savedInstanceState.getInt("count");
        }

    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(Buttons.EXTRA_REPLY);
                if(count<10) {
                    textArray[count].setText(reply);
                    count++;
                }
            }
        }
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, Buttons.class);
        startActivityForResult(intent,TEXT_REQUEST);
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if(textArray[0].getText().length()>0){
            String[] values = new String[10];
            for(int i = 0; i < 10; i++){

                values[i] = (String) textArray[i].getText();
            }
            outState.putStringArray("values",values);
            outState.putInt("count", count);
        }
    }
}