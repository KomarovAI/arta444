package com.example.pc.karta4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by PC on 13.05.2017.
 */

public class Server extends Activity {
    int width;
    int height;
    ClientServer mt;
    boolean Jud1 = false;
    boolean Jud2 = false;
    boolean Jud3 = false;
    boolean timer = false;
    int second=10000;

    String JudF1;
    String JudF2;
    String JudF3;

    int count = 0;

    String mac1;
    String mac2;
    String mac3;
    String mac4;

    String pas1;
    String pas2;
    String pas3;
    String pas4;

    Object object[] = { "" };



    String red = "red";
    String blue = "blue";
    String yellow = "yellow";
    String green = "green";

    final Context context = this;
    BufferedReader in = null;
    ServerSocket servers = null;
    Socket fromclient = null;
    String input;
//    ImageView b1;
//    ImageView b2;
//    ImageView b3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(new DrawView(this));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;  // deprecated
        height = size.y;  // deprecated
        for (int i = 1; i < 5; i++) {
            setPassword(i);
        }




//        b1 = (ImageView) findViewById(R.id.imageView5);
//        b2 = (ImageView) findViewById(R.id.imageView4);
//        b3 = (ImageView) findViewById(R.id.imageView6);
//        b1.setBackgroundColor(Color.WHITE);

        try {
            servers = new ServerSocket(4444);
        } catch (IOException e1) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        mt = new ClientServer();
       // new Thread(new Strngs()).start();
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
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                if (i == 1) {
                                    pas1 = String.valueOf(userInput.getText());
                                } else if (i == 2) {
                                    pas2 = String.valueOf(userInput.getText());
                                } else if (i == 3) {
                                    pas3 = String.valueOf(userInput.getText());
                                } else if (i==4) {
                                    pas4 = String.valueOf(userInput.getText());
                                }
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();
    }

    class DrawView extends View {

        Paint p;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect();
        }

        public DrawView(ClientServer clientServer) {
            super(context);
            p = new Paint();
            rect = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // заливка канвы цветом
            canvas.drawRGB( 0, 0, 0);

            // настройка кисти
            // красный цвет
            // толщина линии = 10
            p.setStrokeWidth(10);

                            if (Jud1 && Jud2 && Jud3) {
                    if (JudF1.equals("red")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/6, height/3, width/6, p);
                        canvas.drawCircle(width/6, height-height/5, width/12, p);
                    } else if (JudF1.equals("blue")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/6, height/3, width/6, p);
                        p.setColor(Color.BLUE);
                        canvas.drawCircle(width/6, height-height/5, width/12, p);
                    } else if (JudF1.equals("yellow")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/6, height/3, width/6, p);
                        p.setColor(Color.YELLOW);
                        canvas.drawCircle(width/6, height-height/5, width/12, p);
                    } else if (JudF1.equals("green")) {
                        p.setColor(Color.WHITE);
                        canvas.drawCircle(width/6, height/3, width/6, p);
                    } else if (JudF1.equals("red2")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/6, height/3, width/6, p);
                    }
                    if (JudF2.equals("red")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/2, height/3, width/6, p);
                        canvas.drawCircle(width/2, height-height/5, width/12, p);
                    } else if (JudF2.equals("blue")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/2, height/3, width/6, p);
                        p.setColor(Color.BLUE);
                        canvas.drawCircle(width/2, height-height/5, width/12, p);
                    } else if (JudF2.equals("yellow")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/2, height/3, width/6, p);
                        p.setColor(Color.YELLOW);
                        canvas.drawCircle(width/2, height-height/5, width/12, p);
                    } else if (JudF2.equals("green")) {
                        p.setColor(Color.WHITE);
                        canvas.drawCircle(width/2, height/3, width/6, p);
                    } else if (JudF2.equals("red2")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width/2, height/3, width/6, p);
                    }
                    if (JudF3.equals("red")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width-width/6, height/3, width/6, p);
                        canvas.drawCircle(width-width/6, height-height/5, width/12, p);
                    } else if (JudF3.equals("blue")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width-width/6, height/3, width/6, p);
                        p.setColor(Color.BLUE);
                        canvas.drawCircle(width-width/6, height-height/5, width/12, p);
                    } else if (JudF3.equals("yellow")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width-width/6, height/3, width/6, p);
                        p.setColor(Color.YELLOW);
                        canvas.drawCircle(width-width/6, height-height/5, width/12, p);
                    } else if (JudF3.equals("green")) {
                        p.setColor(Color.WHITE);
                        canvas.drawCircle(width-width/6, height/3, width/6, p);
                    } else if (JudF3.equals("red2")) {
                        p.setColor(Color.RED);
                        canvas.drawCircle(width-width/6, height/3, width/6, p);
                    }
                } else {
                                p.setColor(Color.BLACK);
                                canvas.drawCircle(width/6, height/3, width/6, p);
                                canvas.drawCircle(width/2, height/3, width/6, p);
                                canvas.drawCircle(width-width/6, height/3, width/6, p);
                }

//            // рисуем круг с центром в (100,200), радиус = 50
//
//
//            canvas.drawCircle(width/6, height-height/5, width/12, p);
//            canvas.drawCircle(width/2, height-height/5, width/12, p);
//            canvas.drawCircle(width-width/6, height-height/5, width/12, p);

        }

    }

    public class ClientServer extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {


            while (true) {

                if (Jud1 && Jud2 && Jud3) {
                    publishProgress(object);
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
                    publishProgress(object);
                }
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
                    input = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (count != 4) {
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
                    } else if (pas4.equals(input.substring(17))) {
                        mac4 = input.substring(0,17);
                        count++;
                    }
                } else {
                    String mac = input.substring(0, 17);
                    System.out.println(mac);
                    input = input.substring(17);
                    System.out.println(input);
                    if (mac.equals(mac1)) {
                        timer=false;
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
                        } else if (input.equals("red2") && !Jud1) {
                            JudF1 = "red2";
                            Jud1 = true;
                        }
                    }  if (mac.equals(mac2)) {
                        timer=false;
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
                        }else if (input.equals("red2") && !Jud2) {
                            JudF2 = "red2";
                            Jud2 = true;
                        }
                    }  if (mac.equals(mac3)) {
                        timer=false;
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
                        } else if (input.equals("red2") && !Jud3) {
                            JudF3 = "red2";
                            Jud3 = true;
                        }
                    }  if (mac.equals(mac4)) {
                        second = Integer.parseInt(input);
                        timer = true;
                        publishProgress(object);
                    }

                }

            }
        }


        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);

            if (timer) {
                setContentView(R.layout.timer_nagib);
                final TextView mTextField = (TextView) findViewById(R.id.textView5);
                mTextField.setTextSize(height/3);
                final CountDownTimer cT =  new CountDownTimer(second*1000, 1000) {

                    public void onTick(long millisUntilFinished) {


                        String v = String.format("%02d", millisUntilFinished/60000);
                        int va = (int)( (millisUntilFinished%60000)/1000);
                        mTextField.setText(v+":"+String.format("%02d",va));
                    }

                    public void onFinish() {
                        mTextField.setTextColor(Color.RED);
                        mTextField.setText("00:00");
                    }
                };
                cT.start();
            } else {
                setContentView(new DrawView(this));
            }
        }

            }
        }

