package com.example.pc.karta4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by PC on 13.05.2017.
 */

public class Server extends Activity{
    ClientServer mt;

    boolean Jud1 = false;
    boolean Jud2 = false;
    boolean Jud3 = false;

    String JudF1;
    String JudF2;
    String JudF3;

    int count = 0;
    boolean gg=false;

    String mac1;
    String mac2;
    String mac3;

    String pas1;
    String pas2;
    String pas3;


    String red = "red";
    String blue = "blue";
    String yellow = "yellow";
    String green ="green";

    final Context context = this;
    BufferedReader in = null;
    ServerSocket servers = null;
    Socket fromclient = null;
    String input;
    ImageView b1;
    ImageView b2;
    ImageView b3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_serv);
        for (int i = 1; i <4 ; i++) {
            setPassword(i);
        }
        b1 = (ImageView) findViewById(R.id.imageView2);
        b2 = (ImageView) findViewById(R.id.imageView4);
        b3 = (ImageView) findViewById(R.id.imageView3);

        mt = new ClientServer();
        new Thread(new Strngs()).start();
                mt.execute();

//        new Thread(new ClientServer()).start();


    }

    void setPassword(final int i) {
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
                                if (i==1) {
                                    pas1 = String.valueOf(userInput.getText());
                                } else if (i==2) {
                                    pas2 = String.valueOf(userInput.getText());
                                } else if (i==3) {
                                    pas3 = String.valueOf(userInput.getText());
                                }
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

public class Strngs extends Thread {
    public void run() {
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e1) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }
        while (true) {
            try {
                //    System.out.print("Waiting for a client...");
                fromclient = servers.accept();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println("Can't accept");
                System.exit(-1);
            }

            try {
                in = new BufferedReader(new
                        InputStreamReader(fromclient.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                input = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (count != 3) {
                if (pas1.equals(input.substring(17))) {
                    System.out.println("Пароль верен!");
                    mac1 = input.substring(0, 17);
                    count++;
                } else if (pas2.equals(input.substring(17))) {
                    System.out.println("Пароль верен!");
                    mac2 = input.substring(0, 17);
                    count++;
                } else if (pas3.equals(input.substring(17))) {
                    System.out.println("Пароль верен!");
                    mac3 = input.substring(0, 17);
                    count++;
                }
            } else {
                String mac = input.substring(0, 17);
                System.out.println(mac);
                input = input.substring(17);
                System.out.println(input);
                if (mac.equals(mac1)) {
                    if (red.equals(input) && !Jud1) {
                        JudF1 = red;
                        Jud1 = true;
                    } else if (blue.equals(input) && !Jud1) {
                        JudF1 = blue;
                        Jud1 = true;
                    } else if (yellow.equals(input) && !Jud1) {
                        JudF1 = yellow;
                        Jud1 = true;
                    } else if (green.equals(input) && !Jud1) {
                        JudF1 = green;
                        Jud1 = true;
                    }
                } else if (mac.equals(mac2)) {
                    if (red.equals(input) && !Jud2) {
                        JudF2 = red;
                        Jud2 = true;
                    } else if (blue.equals(input) && !Jud2) {
                        JudF2 = blue;
                        Jud2 = true;
                    } else if (yellow.equals(input) && !Jud2) {
                        JudF2 = yellow;
                        Jud2 = true;
                    } else if (green.equals(input) && !Jud2) {
                        JudF2 = green;
                        Jud2 = true;
                    }
                } else if (mac.equals(mac3)) {
                    if (red.equals(input) && !Jud3) {
                        JudF3 = red;
                        Jud3 = true;
                    } else if (blue.equals(input) && !Jud3) {
                        JudF3 = blue;
                        Jud3 = true;
                    } else if (yellow.equals(input) && !Jud3) {
                        JudF3 = yellow;
                        Jud3 = true;
                    } else if (green.equals(input) && !Jud3) {
                        JudF3 = green;
                        Jud3 = true;
                    }
                }

            }
        }
    }
}

    public class ClientServer extends AsyncTask<Void,Void,Void> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }


        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Jud1 && Jud2 && Jud3) {
                    if (JudF1.equals("red")) {
                        b1.setBackgroundColor(Color.RED);
                    } else if (JudF1.equals("blue")){
                        b1.setBackgroundColor(Color.BLUE);
                    } else if (JudF1.equals("yellow")) {
                        b1.setBackgroundColor(Color.YELLOW);
                    } else if (JudF1.equals("green")) {
                        b1.setBackgroundColor(Color.WHITE);
                    }
                    if (JudF2.equals("red")) {
                        b2.setBackgroundColor(Color.RED);
                    } else if (JudF2.equals("blue")){
                        b2.setBackgroundColor(Color.BLUE);
                    } else if (JudF2.equals("yellow")) {
                        b2.setBackgroundColor(Color.YELLOW);
                    } else if (JudF2.equals("green")) {
                        b2.setBackgroundColor(Color.WHITE);
                    }
                    if (JudF3.equals("red")) {
                        b3.setBackgroundColor(Color.RED);
                    } else if (JudF3.equals("blue")){
                        b3.setBackgroundColor(Color.BLUE);
                    } else if (JudF3.equals("yellow")) {
                        b3.setBackgroundColor(Color.YELLOW);
                    } else if (JudF3.equals("green")) {
                        b3.setBackgroundColor(Color.WHITE);
                    }
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Jud1 = false;
                    Jud2 = false;
                    Jud3 = false;
                    JudF1 = null;
                    JudF2 = null;
                    JudF3 = null;
                    b1.setBackgroundColor(Color.BLACK);
                    b2.setBackgroundColor(Color.BLACK);
                    b3.setBackgroundColor(Color.BLACK);
                }
            }
            }
        }
    }

