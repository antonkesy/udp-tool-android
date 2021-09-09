package com.antonkesy.udptool.udp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Queue;

public class UDPSendReceive implements Runnable {
    private boolean isRunning = true;

    private final int localPort;
    private final int remotePort;
    private final InetAddress remoteAddress;
    private final boolean isTimeoutEnabled;
    private final int timeoutTime;
    private final int bufferSize;
    private final int listenInterval;
    private final boolean isListening;
    private final boolean isListeningInterval;

    private final ISocketResponses socketResponseHandler;

    private DatagramSocket udpSocket;
    byte[] buffer;
    DatagramPacket packet;

    private final Queue<byte[]> messageQue = new LinkedList<>();

    public UDPSendReceive(int localPort, int remotePort, InetAddress remoteAddress, boolean isTimeoutEnabled, int timeoutTime, int bufferSize, int listenInterval, boolean isListening, boolean isListeningInterval, ISocketResponses socketResponseHandler) {
        this.localPort = localPort;
        this.remotePort = remotePort;
        this.remoteAddress = remoteAddress;
        this.isTimeoutEnabled = isTimeoutEnabled;
        this.timeoutTime = timeoutTime;
        this.bufferSize = bufferSize;
        this.listenInterval = listenInterval;
        this.isListening = isListening;
        this.isListeningInterval = isListeningInterval;
        this.socketResponseHandler = socketResponseHandler;
    }

    @Override
    public void run() {
        try {
            if (udpSocket != null) {
                udpSocket.close();
            }
            udpSocket = new DatagramSocket(localPort);
            socketResponseHandler.socketStart();
            buffer = new byte[bufferSize];
            packet = new DatagramPacket(buffer, buffer.length, remoteAddress, remotePort);
            while (isRunning) {
                if (messageQue.size() > 0) {
                    sendNextMessage();
                }
                try {
                    listenForMessages();
                } catch (SocketTimeoutException e) {
                    socketResponseHandler.socketTimeOut(TimeOutReason.RECEIVE_TIMEOUT);
                } catch (IOException e) {
                    isRunning = false;
                    udpSocket.close();
                    socketResponseHandler.ioException();
                    socketResponseHandler.socketClosed();
                }
            }
        } catch (SocketException e) {
            socketResponseHandler.socketException();
        }
    }

    private void sendNextMessage() {
        byte[] nextMessageContent = messageQue.poll();
        assert nextMessageContent != null;
        DatagramPacket packet = new DatagramPacket(nextMessageContent, nextMessageContent.length, remoteAddress, remotePort);
        try {
            udpSocket.send(packet);
            socketResponseHandler.sendPacket(packet.getData());
            if (isTimeoutEnabled) {
                udpSocket.setSoTimeout(timeoutTime);
                udpSocket.receive(packet);
                socketResponseHandler.dataReceived(packet.getData());
            }
        } catch (SocketTimeoutException e) {
            socketResponseHandler.socketTimeOut(TimeOutReason.SEND_RESPONSE_TIMEOUT);
        } catch (IOException e) {
            socketResponseHandler.ioException();
        }
    }

    private void listenForMessages() throws IOException {
        if (isListening) {
            if (isListeningInterval) {
                udpSocket.setSoTimeout(listenInterval);
            }
            udpSocket.receive(packet);
            socketResponseHandler.dataReceived(packet.getData());
        }
    }

    public void kill() {
        if (udpSocket != null) {
            udpSocket.close();
        }
        messageQue.clear();
        isRunning = false;
    }

    public void addMessageToQue(@NotNull byte[] newMessage) {
        messageQue.add(newMessage);
    }
}
