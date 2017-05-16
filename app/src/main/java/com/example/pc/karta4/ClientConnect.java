package com.example.pc.karta4;

import android.text.Editable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by PC on 13.05.2017.
 */

public class ClientConnect implements Runnable {
    String a;
    String pass;
    String mac;
    boolean gg = true;


    public ClientConnect(Editable text, String mac) {
        pass = String.valueOf(text);
        this.mac = mac;
    }

    public ClientConnect(String green, boolean b, String macAddr) {
        a = green;
        gg = b;
        this.mac = macAddr;
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName("192.168.1.100");
            Socket socket = new Socket(serverAddr, 4444);
            PrintWriter out = new
                    PrintWriter(socket.getOutputStream(),true);
            if (gg) {
                out.println(mac+pass);
                gg=false;
            } else  out.println(mac+a);

            Thread.interrupted();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
