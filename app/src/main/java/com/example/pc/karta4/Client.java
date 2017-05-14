package com.example.pc.karta4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by PC on 13.05.2017.
 */

public class Client extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);


        Button timer = (Button) findViewById(R.id.button);
        Button jurdge = (Button) findViewById(R.id.button2);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        Intent intent = new Intent(Client.this, Timer.class);
                        startActivity(intent);
                        break;
                    case R.id.button2:
                        Intent intent2 = new Intent(Client.this, Jurdge.class);
                        startActivity(intent2);
                        break;
                }
            }};
        timer.setOnClickListener(onClickListener);
        jurdge.setOnClickListener(onClickListener);
    }
}
