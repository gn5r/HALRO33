package via.gn5r.com.androidsample;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by gn5r on 17/11/16.
 */

public class UDPReceiveThread extends Thread {
    private MainActivity mainActivity = null;
    private DatagramSocket socket = null;

    public UDPReceiveThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        try {
            socket = new DatagramSocket(5555);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        try{
            while (true) {
                socket.receive(packet);
                String receive = new String(packet.getData(),0,packet.getLength());
                mainActivity.viewText(receive);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        socket.close();
        socket = null;
        mainActivity = null;
        packet = null;
        buf = null;
    }
}
