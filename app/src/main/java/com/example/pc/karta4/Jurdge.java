package com.example.pc.karta4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by PC on 13.05.2017.
 */

public class Jurdge extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jurdge);

        Button red = (Button) findViewById(R.id.button11);
        Button red2 = (Button) findViewById(R.id.button12);
        Button white = (Button) findViewById(R.id.button8);
        Button yellow = (Button) findViewById(R.id.button9);
        Button blue = (Button) findViewById(R.id.button10);



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button8:
                        new Thread(new ClientConnect("red")).start();
                        break;
                    case R.id.button2:
                        Intent intent2 = new Intent(Jurdge.this, Jurdge.class);
                        startActivity(intent2);
                        break;
                    case R.id.button9:
                        new Thread(new ClientConnect("REED")).start();
                }
            }};
        red.setOnClickListener(onClickListener);
        white.setOnClickListener(onClickListener);
        yellow.setOnClickListener(onClickListener);
        blue.setOnClickListener(onClickListener);
        red2.setOnClickListener(onClickListener);
    }
}
