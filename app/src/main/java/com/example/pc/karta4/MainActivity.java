package com.example.pc.karta4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button client = (Button) findViewById(R.id.button6);
        Button server = (Button) findViewById(R.id.button7);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6:
                Intent intent = new Intent(MainActivity.this, Client.class);
                startActivity(intent);
                break;
            case R.id.button7:
                Intent intent2 = new Intent(MainActivity.this, Server.class);
                startActivity(intent2);
                break;
        }
    }};
        client.setOnClickListener(onClickListener);
                server.setOnClickListener(onClickListener);
    }
}
