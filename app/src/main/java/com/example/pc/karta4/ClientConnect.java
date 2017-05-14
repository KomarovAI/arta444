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
    String str;
    String pass;
    String mac;
    boolean gg = true;


    public ClientConnect(Editable text, String mac) {
        pass = String.valueOf(text);
        this.mac = mac;
    }

//    public ClientConnect(String green, boolean b, String macAddr) {
//        str = green;
//        gg = b;
//        this.mac = macAddr;
//    }
//
//    public ClientConnect(String green, boolean b, String macAddr) {
//        str = green;
//        gg = b;
//        this.mac = macAddr;
//    }

    public ClientConnect(String red) {
        str =red;
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName("192.168.1.100");
            Socket socket = new Socket(serverAddr, 4444);
            PrintWriter out = new
                    PrintWriter(socket.getOutputStream(),true);
                out.println(str);

            Thread.interrupted();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
