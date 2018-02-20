package ro33.hal.tokyo.imagescroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private StateLottery stateLottery;
    private SmallRolePay smallRolePay;
    private CreditCalculation creditCalculation = new CreditCalculation();
    public SetPlace setPlace = new SetPlace();
    public boolean replay;
    String role;

    private boolean BOUNUS;
    private boolean BOUNUS_LAST;
    TextView artcnt;
    TextView artcoincount;
    TextView coincount;
    TextView setpay;
    TextView totalgamecount;
    TextView gamecount;
    TextView smallsisa;
    private int ART;
    private boolean left;
    private boolean right;
    private boolean center;
    private boolean lever;
    private boolean maxBet;
    private boolean error;
    private byte state;
    private int stock;
    private int credit;
    private int coin;
    private int totalgame;
    private int gamenum;
    private int pay;
    private int artcoin;

    private byte cnt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        BOUNUS = false;
        ART = 0;
        left = false;
        right = false;
        center = false;
        lever = false;
        maxBet = true;
        error = false;
        state = 1;
        coin = 0;
        stock = 1;
        credit = 0;
        totalgame = 0;
        artcoin = 0;


        setpay = findViewById(R.id.pay);
        artcoincount = findViewById(R.id.artcoin);
        artcnt = (TextView) findViewById(R.id.artcount);
        coincount = (TextView) findViewById(R.id.coincount);
        totalgamecount = findViewById(R.id.totalcount);
        gamecount = findViewById(R.id.count);
        smallsisa=findViewById(R.id.smallrolesisa);

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
        smallRolePay = new SmallRolePay();
        stateLottery = new StateLottery();

        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(2);
        schedule.scheduleWithFixedDelay(smallRole, 0, 1, TimeUnit.MILLISECONDS);
        schedule.scheduleWithFixedDelay(stateLottery, 0, 2, TimeUnit.SECONDS);

    }

    public void onClick(View view) {
        onClick();
        if (cnt == 3) {
            sendNextgame();
            maxBet = true;
            if (role == "7" || role == "V" || role == "バケ") {
                BOUNUS = true;
                ART = 50;
                if (role == "バケ") {
                    ART = 30;
                }
            }
            artcnt.setText(String.valueOf(ART));
        }
    }

    public void onClick_C(View view) {
        onClick_C();
        if (cnt == 3) {
            if (role == "リプレイ" && error == false) {
                replay = true;
                sendReplayFlag();
                maxBet=false;
                lever=true;
            } else {
                sendNextgame();
                maxBet = true;
            }

            if (role == "7" || role == "V" || role == "バケ") {
                BOUNUS = true;
                ART = 5;
                if (role == "バケ") {
                    ART = 30;
                }
                artcnt.setText(String.valueOf(ART));
            }
        }
    }

    public void onClick_R(View view) {
        onClick_R();
        if (cnt == 3) {
            if (role == "7" || role == "V" || role == "バケ") {
                BOUNUS = true;
                ART = 50;
                if (role == "バケ") {
                    ART = 30;
                }
                artcnt.setText(String.valueOf(ART));
            }
            sendNextgame();
            maxBet = true;
        }
    }

    public void leverOn(View view) {
        if (replay == true) {
            replay = false;
        }
        leverOn();
    }

    public void maxBetOn(View view) {
        maxBetOn();
    }

    private void onClick() {
        if (role == "7" || role == "バケ" || role == "V") {
            cnt++;
            scrollSurfaceView.stop(role);
            left = false;
        } else if (left == true) {
            if (cnt != 0) {
                error = true;
            }
            cnt++;
            scrollSurfaceView.stop(role);
            left = false;
        }
    }

    private void onClick_C() {
        if (role == "7" || role == "バケ" || role == "V") {
            cnt++;
            scrollSurfaceView2.stop(role);
            center = false;
        } else if (center == true) {
            if (cnt != 2) {
                error = true;
            }
            cnt++;
            scrollSurfaceView2.stop(role);
            if (role == "スイカ" || role == "強スイカ") {
                int l = setPlace.getLeft();
                int r = setPlace.getRight();
                int c = setPlace.getCenter();
                byte ls;
                byte rs;
                byte cs;
                if (l == 5 || l == 12 || l == 15) {//上
                    ls = 1;
                } else if (l == 6 || l == 13 || l == 16) {//中
                    ls = 2;
                } else if (l == 7 || l == 17) {//下
                    ls = 3;
                } else {//ハズレ
                    ls = 4;
                }
                if (r == 4 || r == 8 || r == 13 || r == 17 || r == 21) {
                    rs = 1;
                } else if (r == 1 || r == 5 || r == 9 || r == 14 || r == 18) {
                    rs = 2;
                } else if (r == 2 || r == 6 || r == 10 || r == 15 || r == 19) {
                    rs = 3;
                } else {
                    rs = 4;
                }
                if (c == 1 || c == 14) {
                    cs = 1;
                } else if (c == 2 || c == 15) {
                    cs = 2;
                } else if (c == 3 || c == 16) {
                    cs = 3;
                } else {
                    cs = 4;
                }

                if ((ls == 1 && rs == 1 && ls == 1) || (ls == 3 && rs == 3 && cs == 3) ||
                        ((ls == 1 || ls == 2 || ls == 3) && (rs == 1 || rs == 2 || rs == 3) && cs == 2)) {
                    pay = smallRolePay.pay(role);
                } else {
                    pay = smallRolePay.pay("ハズレ");
                }

            } else {
                pay = smallRolePay.pay(role);
            }
            if (BOUNUS) {
                artcoin += pay;
                artcoincount.setText(String.valueOf(artcoin));
            }
            setpay.setText(String.valueOf(pay));
            creditCalculation.centerButton(pay);
            coin += pay;
            coincount.setText(String.valueOf(coin));
            credit();
            center = false;
        }
    }

    private void onClick_R() {
        if (role == "7" || role == "バケ" || role == "V") {
            cnt++;
            scrollSurfaceView3.stop(role);
            right = false;
        } else if (right == true) {
            if (cnt != 1) {
                error = true;
            }
            cnt++;
            scrollSurfaceView3.stop(role);
            right = false;
        }
    }

    private void leverOn() {
        if (lever == true) {
            lever = false;
            replay = false;
            left = true;
            right = true;
            center = true;
            error = false;
            cnt = 0;
            totalgame += 1;
            gamenum += 1;
            if (BOUNUS == true) {
                gamenum = 0;
            }
            totalgamecount.setText(String.valueOf(totalgame));
            gamecount.setText(String.valueOf(gamenum));
            if (BOUNUS_LAST == true) {
                BOUNUS_LAST=false;
                state = stateLottery.getNextState();
                stateLottery.nextState=1;
                if (state == 1 && 0 < stock) {
                    state = stateLottery.getStockStateLotterry();
                    stock -= 1;
                }
                if (state!=6&&state!=7&&state!=8){
                    artcoin=0;
                    artcoincount.setText(String.valueOf(artcoin));
                }
            }
            switch (state) {
                case 1:
                    role = smallRole.getSmallRole();
                    state = stateLottery.getState(role);
                    if (role == "中断チェリー") {
                        stock += 5;
                    }
                    break;
                case 2:
                    role = smallRole.getSmallRole();
                    state = stateLottery.getState(role);
                    if (role == "中断チェリー") {
                        stock += 5;
                    }
                    break;
                case 3:
                    role = smallRole.getSmallRole();
                    state = stateLottery.getState(role);
                    if (role == "中断チェリー") {
                        stock += 5;
                    }
                    break;
                case 4:
                    role = smallRole.getSmallRole();
                    state = stateLottery.getState(role);
                    if (role == "中断チェリー") {
                        stock += 5;
                    }
                    break;//通常時　case 1 - 4
                case 6:
                    role = smallRole.getSmallRole();
                    if (BOUNUS == false) {
                        role = "7";
                    } else {
                        ART -= 1;
                        if (role == "ハズレ") {
                            role = smallRole.getBounusRole();
                        }
                        if (ART == 0) {
                            BOUNUS_LAST = true;
                            stateLottery.endBOUNUS(role);
                            BOUNUS = false;
                        }
                        artcnt.setText(String.valueOf(ART));
                    }
                    stock += stateLottery.stockLottery(role);
                    break;
                case 7:
                    role = smallRole.getSmallRole();
                    if (BOUNUS == false) {
                        role = "V";
                    } else {
                        ART -= 1;
                        if (role == "ハズレ") {
                            role = smallRole.getBounusRole();
                        }
                        if (ART == 0) {
                            BOUNUS_LAST = true;
                            stateLottery.endBOUNUS(role);
                            BOUNUS = false;
                        }
                        artcnt.setText(String.valueOf(ART));
                    }
                    stock += stateLottery.stockLottery(role);
                    break;
                case 8:
                    role = smallRole.getSmallRole();
                    if (BOUNUS == false) {
                        role = "バケ";
                    } else {
                        ART -= 1;
                        if (role == "ハズレ") {
                            role = smallRole.getBounusRole();
                        }
                        if (ART == 0) {
                            BOUNUS_LAST = true;
                            stateLottery.endBOUNUS(role);
                            BOUNUS = false;
                        }
                        artcnt.setText(String.valueOf(ART));
                    }
                    stock += stateLottery.stockLottery(role);
                    break;
            }
            if (role=="ハズレ"||role=="ベル"||role=="リプレイ"||role=="スイカ"){
                smallsisa.setText("　" );
            }else if (role=="7"||role=="V"||role=="バケ"){
                smallsisa.setText("当");
            }else {
                smallsisa.setText("!");
            }
            scrollSurfaceView.leverOn();
            scrollSurfaceView2.leverOn();
            scrollSurfaceView3.leverOn();

        }
    }

    private void maxBetOn() {
        if (maxBet == true) {
            maxBet = false;
            coin -= 3;
            coincount.setText(String.valueOf(coin));
            lever = true;
        }
        if (replay == true) {
            replay = false;
            lever = true;
        } else {
            creditCalculation.maxBet();
            credit();
        }
        Log.d("MAX",String.valueOf(lever));
    }

    private void credit() {
        credit = creditCalculation.getCredit();
        if (credit < 0) {
            credit = 0;
        }
        if (credit > 50) {
            credit = 50;
        }
        TextView view = (TextView) findViewById(R.id.credit);
        view.setText(String.valueOf(credit));
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
            new UDPSendTask(MainActivity.this, IPAddress, Integer.parseInt(comPort)).execute("replay");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNextgame() {
        try {
            new UDPSendTask(MainActivity.this, IPAddress, Integer.parseInt(comPort)).execute("next");
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
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    switch (text) {
                        case "maxbet":
                            maxBetOn();
                            break;
                        case "lever":
                            if (replay == true) {
                                replay = false;
                            }
                            leverOn();
                            break;
                        case "left":
                            onClick();
                            if (cnt == 3) {
                                sendNextgame();
                                maxBet = true;
                                if (role == "7" || role == "V" || role == "バケ") {
                                    BOUNUS = true;
                                    ART = 50;
                                    if (role == "バケ") {
                                        ART = 30;
                                    }
                                }
                                artcnt.setText(String.valueOf(ART));
                            }
                            break;
                        case "center":
                            onClick_C();
                            if (cnt == 3) {
                                if (role == "リプレイ" && error == false) {
                                    replay = true;
                                    sendReplayFlag();
                                    maxBet=false;
                                    lever=true;
                                } else {
                                    coincount.setText(String.valueOf(coin));
                                    sendNextgame();

                                    maxBet = true;
                                }
                                if (role == "7" || role == "V" || role == "バケ") {
                                    BOUNUS = true;
                                    ART = 50;
                                    if (role == "バケ") {
                                        ART = 30;
                                    }
                                    Log.d("V",String.valueOf(maxBet));
                                    artcnt.setText(String.valueOf(ART));
                                    Log.d("V",String.valueOf(maxBet));
                                    maxBet = true;
                                }
                            }
                            break;
                        case "right":
                            onClick_R();
                            if (cnt == 3) {
                                if (role == "7" || role == "V" || role == "バケ") {
                                    BOUNUS = true;
                                    ART = 50;
                                    if (role == "バケ") {
                                        ART = 30;
                                    }
                                    artcnt.setText(String.valueOf(ART));
                                }
                                sendNextgame();
                                maxBet = true;
                            }
                            break;
                        default:
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

