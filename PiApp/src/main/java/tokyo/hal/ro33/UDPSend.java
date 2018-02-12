/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author pi
 */
public class UDPSend {

    private DatagramSocket socket;
    private InetAddress connectIP;
    private int comPort;

    public UDPSend(String connectIP, int comPort) throws UnknownHostException, SocketException {
        this.connectIP = InetAddress.getByName(connectIP);
        this.comPort = comPort;
        socket = new DatagramSocket();
    }

    public void Send(String message) throws Exception {
        byte[] buf = message.getBytes("UTF-8");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, connectIP, comPort);
        socket.send(packet);
    }

    public void close() {
        this.socket.close();
    }
}
