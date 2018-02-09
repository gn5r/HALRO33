package ro33.hal.tokyo.imagescroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Handler handler;
    private String IPAddress, comPort, myIPAddress;
    private ArrayList<UDPData> list;
    private ScrollSurfaceView scrollSurfaceView;
    private ScrollSurfaceView2 scrollSurfaceView2;
    private ScrollSurfaceView3 scrollSurfaceView3;
    private SmallRole smallRole;
    public SetPlace setPlace = new SetPlace();
    public boolean replay;
    String role;

    public boolean left;
    public boolean right;
    public boolean center;
    byte cnt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        LinearLayout linearLayout = findViewById(R.id.leftreel);
        LinearLayout linearLayout1 = findViewById(R.id.centerreel);
        LinearLayout linearLayout2 = findViewById(R.id.rightreel);
        scrollSurfaceView = new ScrollSurfaceView(this, setPlace);
        scrollSurfaceView2 = new ScrollSurfaceView2(this, setPlace);
        scrollSurfaceView3 = new ScrollSurfaceView3(this, setPlace);
        linearLayout.addView(scrollSurfaceView);
        linearLayout1.addView(scrollSurfaceView2);
        linearLayout2.addView(scrollSurfaceView3);
        smallRole = new SmallRole();

        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
        schedule.scheduleWithFixedDelay(smallRole, 0, 1, TimeUnit.MILLISECONDS);

    }

    public void onClick(View view) {
        scrollSurfaceView.stop(role);
    }

    public void onClick_C(View view) {
        scrollSurfaceView2.stop(role);
    }

    public void onClick_R(View view) {
        scrollSurfaceView3.stop(role);
    }

    public void leverOn(View view) {
        replay = false;
        role = smallRole.getSmallRole();
        scrollSurfaceView.leverOn();
        scrollSurfaceView2.leverOn();
        scrollSurfaceView3.leverOn();
        if (role == "リプレイ") {
            replay = true;
        }
    }

    public void onClick() {
        if (left == true) {
            cnt++;
            scrollSurfaceView.stop(role);
            left = false;
        }
    }

    public void onClick_C() {
        if (center == true) {
            cnt++;
            scrollSurfaceView2.stop(role);
            center = false;
        }
    }

    public void onClick_R() {
        if (right == true) {
            cnt++;
            scrollSurfaceView3.stop(role);
            right = false;
        }
    }

    public void leverOn() {
        replay = false;
        left = true;
        right = true;
        center = true;
        cnt = 0;
        role = smallRole.getSmallRole();
        scrollSurfaceView.leverOn();
        scrollSurfaceView2.leverOn();
        scrollSurfaceView3.leverOn();
        if (role == "リプレイ") {
            replay = true;
        }
    }


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
        ((TextView) findViewById(R.id.myIP)).setText("IPAddress:" + myIPAddress);
    }

    public void sendMessage(View view) {
        try {
            new UDPSendTask(MainActivity.this, IPAddress, Integer.parseInt(comPort)).execute("connect");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendReplayFlag() {
        try {
            new UDPSendTask(MainActivity.this, IPAddress, Integer.parseInt(comPort)).execute("Replay");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    ((TextView) findViewById(R.id.message)).setText(text);

                    switch (text) {
                        case "maxbet":
                            showText("レバーに気合を入れろ！");
                            break;
                        case "lever":
                            if (replay == true){
                                replay = false;
                            }
                            leverOn();
                            break;
                        case "left":
                            onClick();
                            break;
                        case "center":
                            onClick_C();
                            if (cnt == 3){
                                sendReplayFlag();
                            }
                            break;
                        case "right":
                            onClick_R();
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

