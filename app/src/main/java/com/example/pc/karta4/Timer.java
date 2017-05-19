package com.example.pc.karta4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by PC on 13.05.2017.
 */

public class Timer extends Activity {
    final Context context = this;
    EditText seconds;
    Button start;
    TextView secondsView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.timer);
        setPassword();

        seconds = (EditText) findViewById(R.id.editText2);
        start = (Button) findViewById(R.id.button3);
        secondsView = (TextView) findViewById(R.id.textView8);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button3:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Timer.this);
                        // Указываем Title
                        alertDialog.setTitle("Подтвердить выбор...");

                        // Указываем текст сообщение
                        alertDialog.setMessage("Вы уверены, что хотите задать это время: " + seconds.getText() + " Секунд");

                        // задаем иконку
                        alertDialog.setIcon(R.drawable.shape);

                        // Обработчик на нажатие ДА
                        alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Код который выполнится после закрытия окна
                                if (seconds.getText()!=null) {
                                    secondsView.setText(seconds.getText());
                                    new Thread(new ClientConnect(String.valueOf(secondsView.getText()),false,getMacAddr())).start();
                                } else {
                                    if (secondsView.getText()!=null) {
                                        new Thread(new ClientConnect(String.valueOf(secondsView.getText()),false,getMacAddr())).start();
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "Вы нажали ДА, заданное время "+seconds.getText()+" Cекунд", Toast.LENGTH_SHORT).show();
                            }
                        });

                        // Обработчик на нажатие НЕТ
                        alertDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Код который выполнится после закрытия окна
                                Toast.makeText(getApplicationContext(), "Вы нажали НЕТ", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                        // показываем Alert
                        alertDialog.show();

            }
            }
        };
        start.setOnClickListener(onClickListener);


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
