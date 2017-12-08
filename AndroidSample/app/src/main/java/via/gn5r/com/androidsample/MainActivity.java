package via.gn5r.com.androidsample;

import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Handler handler;
    private String IPAddress, comPort,myIPAddress;
    private ArrayList<UDPData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        handler = new Handler();

        SharedPreferences preferences = getSharedPreferences("UDPData", MODE_PRIVATE);

        list = new ArrayList<>();
        for (int i = preferences.getInt("count", 0); i > 0; i--) {
            UDPData udpData = new UDPData();
            showText("要素数:" + String.valueOf(i));
            udpData.setIPAddress(preferences.getString("IP" + String.valueOf(i), null));
            udpData.setComPort(preferences.getString("Port" + String.valueOf(i), null));
            list.add(udpData);
        }

        viewIPAddress();

        Bundle bundle = new Bundle();
        bundle.putSerializable("MainActivity", this);
        CustomDialog customDialog = new CustomDialog();
        customDialog.setArguments(bundle);
        customDialog.show(getFragmentManager(), "List");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {

        // バックキーの長押しに対する処理
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
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
        myIPAddress = ((address >> 0) & 0xFF) + "."
                + ((address >> 8) & 0xFF) + "."
                + ((address >> 16) & 0xFF) + "."
                + ((address >> 24) & 0xFF);
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editMessage);
        String message = editText.getText().toString();
        try {
            new UDPSendTask(MainActivity.this, IPAddress, Integer.parseInt(comPort)).execute(message);
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

                if (!TextUtils.isEmpty(text)) {
                    switch (text) {
                        case "maxbet":
                            showText("レバーに気合を入れろ！");
                            break;
                        case "lever":
                            showText("強請るな、勝ち取れ。さすれば道は開かれん！");
                            break;
                        case "left":
                            showText("左！");
                            break;
                        case "center":
                            showText("中！");
                            break;
                        case "right":
                            showText("右！");
                            break;

                        default:
                            showText("受信データ:" + text);
                            break;
                    }
                }
            }
        });
    }

    public ArrayList<UDPData> getList() {
        return list;
    }


    public void setPref(ArrayList<UDPData> udpData, int count) {
        SharedPreferences preferences = getSharedPreferences("UDPData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        for (UDPData data : udpData) {
            editor.putString("IP" + String.valueOf(count), data.getIPAddress());
            editor.putString("Port" + String.valueOf(count), data.getComPort());
            editor.putInt("count", udpData.size());
            editor.apply();
        }
    }

    public void deletePref(ArrayList<UDPData> udpData, int position) {
        SharedPreferences preferences = getSharedPreferences("UDPData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove("IP" + String.valueOf(position));
        editor.remove("Port" + String.valueOf(position));
        editor.putInt("count", udpData.size());
        editor.apply();
    }

    public void setUDPDatas(String IPAddress, String comPort) {
        this.IPAddress = IPAddress;
        this.comPort = comPort;
    }

    public String getMyIPAddress() {
        return myIPAddress;
    }
}
