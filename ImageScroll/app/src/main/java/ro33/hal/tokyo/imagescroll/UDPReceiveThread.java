package ro33.hal.tokyo.imagescroll;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by gn5r on 17/11/16.
 */

public class UDPReceiveThread extends Thread {
    private MainActivity mainActivity = null;
    private DatagramSocket socket = null;
    private boolean trueConnect = false;

    public UDPReceiveThread(MainActivity mainActivity,int comPort) {
        this.mainActivity = mainActivity;
        try {

            if(socket != null){
                socket.close();
                socket = null;
                socket = new DatagramSocket(comPort);
            }else {
                socket = new DatagramSocket(comPort);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        super.start();
        trueConnect = true;
    }

    public void onStop(){
        trueConnect = false;
    }

    @Override
    public void run() {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        try{
            while (trueConnect) {
                socket.receive(packet);
                String receive = new String(packet.getData(),0,packet.getLength());
                mainActivity.viewText(receive);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        socket.close();
//        socket = null;
//        mainActivity = null;
//        packet = null;
//        buf = null;
    }
}
