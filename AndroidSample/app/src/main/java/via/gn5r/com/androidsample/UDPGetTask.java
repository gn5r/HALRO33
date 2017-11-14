package via.gn5r.com.androidsample;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by gn5r on 17/11/13.
 */

public class UDPGetTask extends AsyncTask<Void, Void, String> {

    private MainActivity mainActivity;

    public UDPGetTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try {

            DatagramSocket socket = new DatagramSocket(5555);

            socket.receive(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!TextUtils.isEmpty(new String(packet.getData()))) {
            return new String(packet.getData());
        }else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String packet) {
        if(!TextUtils.isEmpty(packet)) {
            mainActivity.viewText("受信データ" + packet);
        }
    }
}
