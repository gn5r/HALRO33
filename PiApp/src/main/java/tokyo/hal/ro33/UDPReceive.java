/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author pi
 */
public class UDPReceive extends Thread {

    /*  privateパラメータ  */
    private DatagramSocket socket;
    private DatagramPacket packet;
    private Main main;

    /*  コンストラクタでUDPSocketのインスタンスを生成し、UDPポートを決定  */
    public UDPReceive(int comPort) throws Exception, SocketException {
        this.socket = new DatagramSocket(comPort);
        this.main = new Main();
    }

    @Override
    public void run() {

        try {
            byte[] buf = new byte[1024];
            this.packet = new DatagramPacket(buf, buf.length);

            /*  whileループで常時受信、受信データがあればMainクラスにデータを投げる  */
            while (true) {
                socket.receive(this.packet);
                String receive = new String(packet.getData(), 0, packet.getLength());
                main.Receive(receive);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*  データ送信元のIP Addressを取得  */
    public String getConnectIP() {
        return packet.getAddress().toString().replaceFirst(".*" + "/", "");
    }

}
