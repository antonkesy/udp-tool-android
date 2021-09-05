package com.antonkesy.udptool.udp;

import android.util.Log;

import com.antonkesy.udptool.util.Utils;

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

    private final ISocketResponses socketResponseHandler;

    public UDPSendReceive(int localPort, int remotePort, InetAddress remoteAddress, boolean isTimeoutEnabled, int timeoutTime, int bufferSize, ISocketResponses socketResponseHandler) {
        this.localPort = localPort;
        this.remotePort = remotePort;
        this.remoteAddress = remoteAddress;
        this.isTimeoutEnabled = isTimeoutEnabled;
        this.timeoutTime = timeoutTime;
        this.bufferSize = bufferSize;
        this.socketResponseHandler = socketResponseHandler;
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
                    socketResponseHandler.dataReceived(Utils.byteToByte(packet.getData()));
                } catch (SocketTimeoutException e) {
                    Log.e("Timeout Exception", "UDP Connection:", e);
                    socketResponseHandler.socketTimeOut();
                } catch (IOException e) {
                    Log.e("UDP client IOException", "error: ", e);
                    isRunning = false;
                    udpSocket.close();
                    socketResponseHandler.ioException();
                }
            }
        } catch (SocketException e) {
            Log.e("Socket Open:", "Error:", e);
            socketResponseHandler.socketException();
        } catch (IOException e) {
            e.printStackTrace();
            socketResponseHandler.ioException();
        }
    }

    public void kill() {
        isRunning = false;
    }

}
