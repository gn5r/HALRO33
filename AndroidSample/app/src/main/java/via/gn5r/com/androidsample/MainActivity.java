package via.gn5r.com.androidsample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Handler handler;
    private String IPAddress, Port;
    private UDPReceiveThread receiveThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        handler = new Handler();

        SharedPreferences info = getSharedPreferences("Information", MODE_PRIVATE);

        setIPAddress(info.getString("IPAddress",null));
        setPort(info.getString("comPort",null));

        if(!TextUtils.isEmpty(this.IPAddress) && !TextUtils.isEmpty(this.Port)){

            if(receiveThread != null){
                receiveThread.onStop();
                receiveThread = null;
            }else {
                receiveThread = new UDPReceiveThread(this, Integer.parseInt(Port));
                receiveThread.start();
                viewIPAddress();
            }

        }else{
            CustomDialog customDialog = new CustomDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("MainActivity",MainActivity.this);
            customDialog.setArguments(bundle);
            customDialog.show(getFragmentManager(),"Settings");

        }
    }

    @Override
    protected void onDestroy() {
        receiveThread.onStop();

        super.onDestroy();
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {

        // バックキーの長押しに対する処理
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            showText("終了します");
            Process.killProcess(Process.myPid());
            return true;
        }

        return super.onKeyLongPress(keyCode, event);
    }

    public void viewIPAddress() {
         /* IPアドレスの表示  */
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifIinfo = wifiManager.getConnectionInfo();
        int address = wifIinfo.getIpAddress();
        String IPAddress = ((address >> 0) & 0xFF) + "."
                + ((address >> 8) & 0xFF) + "." + ((address >> 16) & 0xFF)
                + "." + ((address >> 24) & 0xFF);
        TextView ipView = (TextView) findViewById(R.id.ip_address);
        ipView.setText("IPアドレス:" + IPAddress + "\nポート:" + Port);
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editMessage);
        String message = editText.getText().toString();
        try {
            new UDPSendTask(MainActivity.this, IPAddress, Integer.parseInt(Port)).execute(message);
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

                if(text.matches("test")){
                    showText("テスト");
                }else{
                    showText("受信データ:" + text);
                }
            }
        });
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void setPort(String port) {
        this.Port = port;
    }

    public void saveInformation(String IPAddress,String comPort) {
        SharedPreferences preferences = getSharedPreferences("Information", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("IPAddress", this.IPAddress);
        editor.putString("comPort",this.Port);
        editor.apply();
    }
}
