package ro33.hal.tokyo.imagescroll;

import android.os.AsyncTask;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by gn5r on 2017/10/25.
 */

public class UDPSendTask extends AsyncTask<String, Void, String> {
    private InetAddress inetAddress;
    private int udpPort;
    private MainActivity mainActivity;

    public UDPSendTask(MainActivity mainActivity, String inetAddress, int udpPort) throws Exception {
        this.inetAddress = InetAddress.getByName(inetAddress);
        this.udpPort = udpPort;
        this.mainActivity = mainActivity;
    }

    protected String doInBackground(String... strings) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] buf = strings[0].getBytes("UTF-8");

            DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, udpPort);
            datagramSocket.send(packet);
        } catch (Exception e) {

        }
        return strings[0];
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
