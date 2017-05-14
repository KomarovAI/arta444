package com.example.pc.karta4;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by PC on 14.05.2017.
 */

public class ClientServer extends Activity implements Runnable {
    BufferedReader in = null;
    ServerSocket servers = null;
    Socket fromclient = null;
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
            String input = null;
            try {
                input = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (input.equals("REED")) {
                ImageView img = (ImageView) findViewById(R.id.imageView9);
                img.setBackgroundColor(Color.YELLOW);
            }
        }

    }
}
