package com.example.pc.karta4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by PC on 13.05.2017.
 */

public class Jurdge extends Activity {
    final Context context = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.jurdge);

        setPassword();
        Button red = (Button) findViewById(R.id.button11);
        Button red2 = (Button) findViewById(R.id.button12);
        Button white = (Button) findViewById(R.id.button8);
        Button yellow = (Button) findViewById(R.id.button9);
        Button blue = (Button) findViewById(R.id.button10);



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button11:
                        new Thread(new ClientConnect("red", false, getMacAddr())).start();
                        break;
                    case R.id.button10:
                        new Thread(new ClientConnect("blue", false, getMacAddr())).start();
                        break;
                    //tvOut.setText("Нажата кнопка Кансель!");
                    case R.id.button9:
                        new Thread(new ClientConnect("yellow", false, getMacAddr())).start();
                        break;
                    case R.id.button8:
                        new Thread(new ClientConnect("green", false, getMacAddr())).start();
                        break;
                    case R.id.button12:
                        new Thread(new ClientConnect("red2",false,getMacAddr())).start();
                        break;
                }
            }
        };

        red.setOnClickListener(onClickListener);
        white.setOnClickListener(onClickListener);
        yellow.setOnClickListener(onClickListener);
        blue.setOnClickListener(onClickListener);
        red2.setOnClickListener(onClickListener);
    }

    void setPassword() {
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                new Thread(new ClientConnect(userInput.getText(),getMacAddr())).start();
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}
