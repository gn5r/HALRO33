package via.gn5r.com.androidsample;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        handler = new Handler();
        viewIPAddress();

        UDPReceiveThread receiveThread = new UDPReceiveThread(this);
        receiveThread.start();
    }

    private void viewIPAddress() {
         /* IPアドレスの表示  */
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifIinfo = wifiManager.getConnectionInfo();
        int address = wifIinfo.getIpAddress();
        String IPAddress = ((address >> 0) & 0xFF) + "."
                + ((address >> 8) & 0xFF) + "." + ((address >> 16) & 0xFF)
                + "." + ((address >> 24) & 0xFF);
        TextView ipView = (TextView) findViewById(R.id.ip_address);
        ipView.setText("IPアドレス:" + IPAddress + "\nポート:5555");
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editMessage);
        String message = editText.getText().toString();
        try {
            new UDPSendTask(MainActivity.this, "192.168.0.189", 5555).execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        editText.setText("");
    }

    public void showText(final String text) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewText(final String text) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(text);
                showText("受信データ:" + text);
            }
        });
    }
}
