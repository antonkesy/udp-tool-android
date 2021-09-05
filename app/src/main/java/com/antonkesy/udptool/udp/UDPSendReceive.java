package com.antonkesy.udptool.udp;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class UDPSendReceive implements Runnable {
    private boolean isRunning = true;

    private final int localPort;
    private final int remotePort;
    private final InetAddress remoteAddress;
    private final boolean isTimeoutEnabled;
    private final int timeoutTime;
    private final int bufferSize;

    private final IUDPThreadExceptions threadExceptionsHandler;

    public UDPSendReceive(int localPort, int remotePort, InetAddress remoteAddress, boolean isTimeoutEnabled, int timeoutTime, int bufferSize, IUDPThreadExceptions threadExceptionsHandler) {
        this.localPort = localPort;
        this.remotePort = remotePort;
        this.remoteAddress = remoteAddress;
        this.isTimeoutEnabled = isTimeoutEnabled;
        this.timeoutTime = timeoutTime;
        this.bufferSize = bufferSize;
        this.threadExceptionsHandler = threadExceptionsHandler;
    }

    @Override
    public void run() {
        try {
            Log.e("UDP", "start");
            DatagramSocket udpSocket = new DatagramSocket(localPort);
            byte[] buffer = new byte[bufferSize];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, remoteAddress, remotePort);
            udpSocket.send(packet);
            Log.e("UDP", "send packet");
            while (isRunning) {
                try {
                    if (isTimeoutEnabled) {
                        Log.e("UDP", "about to wait to receive " + timeoutTime + "ms");
                        udpSocket.setSoTimeout(timeoutTime);
                    }
                    udpSocket.receive(packet);
                    String text = new String(packet.getData(), 0, packet.getLength());
                    Log.d("Received text", text);
                } catch (SocketTimeoutException e) {
                    Log.e("Timeout Exception", "UDP Connection:", e);
                    threadExceptionsHandler.socketTimeOut();
                } catch (IOException e) {
                    Log.e("UDP client IOException", "error: ", e);
                    isRunning = false;
                    udpSocket.close();
                    threadExceptionsHandler.io();
                }
            }
        } catch (SocketException e) {
            Log.e("Socket Open:", "Error:", e);
            threadExceptionsHandler.socket();
        } catch (IOException e) {
            e.printStackTrace();
            threadExceptionsHandler.io();
        }
    }

    public void kill() {
        isRunning = false;
    }

}
