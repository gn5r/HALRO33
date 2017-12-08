package shangyuan.tuolang.slot;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import shangyuan.tuolang.slot.udp.CustomDialog;
import shangyuan.tuolang.slot.udp.UDPData;

public class MainActivity extends AppCompatActivity implements Serializable{

    int nope;
    boolean replay = false;
    int wmelon;
    int wcherry;
    int rand;
    String reel;
    MediaPlayer mediaPlayer;
    boolean leverOn = false;
    boolean maxbetOn = false;
    int MaxBet,Reel_Start,Reel_Stop,Replay;
    SoundPool soundPool;
    AudioAttributes audioAttributes;
    AnimationDrawable left,center,right;

    private Handler handler;
    private String IPAddress, comPort;
    private ArrayList<UDPData> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setSoundPool();

        handler = new Handler();

        SharedPreferences preferences = getSharedPreferences("UDPData", MODE_PRIVATE);

        list = new ArrayList<>();
        for (int i = preferences.getInt("count", 0); i > 0; i--) {
            UDPData udpData = new UDPData();
            udpData.setIPAddress(preferences.getString("IP" + String.valueOf(i), null));
            udpData.setComPort(preferences.getString("Port" + String.valueOf(i), null));
            list.add(udpData);
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("MainActivity", this);
        CustomDialog customDialog = new CustomDialog();
        customDialog.setArguments(bundle);
        customDialog.show(getFragmentManager(), "List");
    }

    public int GetRandom(){
        Random random = new Random();
        int iRand = random.nextInt(65535);

        return iRand + 1;
    }

    public void setFlag(int random){

        if(rand >= 0 && rand <= 39844){
            reel = "ハズレ";
        }else if(rand >= 39845 && rand <= 56228){
            reel = "リプ";
            replay = true;
        }else if(rand >= 56289 && rand <= 62781) {
            reel = "ベル";
        }else if(rand >= 62782 && rand <= 62831){
            reel = "強ベル";
        }else if(rand >= 62832 && rand <= 64001){
            reel = "スイカ";
        }else if(rand >= 64002 && rand <= 64216){
            reel = "強スイカ";
        }else if(rand >= 64217 && rand <= 64884){
            reel = "チェリー";
        }else if(rand >= 64885 && rand <= 65200){
            reel = "強チェリー";
        }else if(rand >= 65201 && rand <= 65363){
            reel = "チャンス目A";
        }else if(rand >= 65364 && rand <= 65526){
            reel = "チャンス目B";
        }else if(rand >= 65527 && rand <= 65535){
            reel = "中チェ";
            mp3Start();
        }
    }

    public void mp3Start() {
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.start();
    }

    public void onClick(View view){

        switch (view.getId()){

            case R.id.maxbet:
                if(maxbetOn){
                    break;
                }else{
                    maxbetOn = true;
                    getSoundPool(1);
                    break;
                }

            case R.id.start:

                if(maxbetOn == true && !leverOn || replay){
                    if(replay)replay =false;
                    rand = GetRandom();
                    setFlag(rand);
                    leverOn = true;
                    getSoundPool(2);
                    leftReel();
                    centerReel();
                    rightReel();
                    left.start();
                    center.start();
                    right.start();
                }else{
                    break;
                }
                maxbetOn = false;
                break;

            case R.id.stop1:
                leverOn = false;
                getSoundPool(3);
                left.stop();
                break;

            case R.id.stop2:
                getSoundPool(3);
                center.stop();
                break;

            case R.id.stop3:
                getSoundPool(3);
                right.stop();
                leverOn = false;
                if(replay){
                    getSoundPool(4);
                    getSoundPool(1);
                    maxbetOn = true;
                }
                break;

            default:break;
        }

    }

    public void setSoundPool(){

        soundPool = null;

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build();
        MaxBet = soundPool.load(this,R.raw.maxbet,1);
        Reel_Start = soundPool.load(this,R.raw.reel_start,1);
        Reel_Stop = soundPool.load(this,R.raw.reel_stop,1);
        Replay = soundPool.load(this,R.raw.replay,1);
    }

    public void getSoundPool(int soundID){

        switch (soundID){

            case 1:soundPool.play(MaxBet,1f,1f,0,0,1);
                break;

            case 2:soundPool.play(Reel_Start,1f,1f,0,0,1);
                break;

            case 3:soundPool.play(Reel_Stop,1f,1f,0,0,1);
                break;

            case 4:soundPool.play(Replay,1f,1f,0,0,1);
                break;

            default:break;
        }
    }

    public void leftReel(){
        ImageView imageView = (ImageView)findViewById(R.id.left_reel);

        imageView.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.left_reel));
        left = (AnimationDrawable)imageView.getBackground();

    }

    public void centerReel(){
        ImageView imageView = (ImageView)findViewById(R.id.center_reel);

        imageView.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.left_reel));
        center = (AnimationDrawable)imageView.getBackground();

    }

    public void rightReel(){
        ImageView imageView = (ImageView)findViewById(R.id.right_reel);

        imageView.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.left_reel));
        right = (AnimationDrawable)imageView.getBackground();

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
        String IPAddress = ((address >> 0) & 0xFF) + "."
                + ((address >> 8) & 0xFF) + "."
                + ((address >> 16) & 0xFF) + "."
                + ((address >> 24) & 0xFF);
//        TextView ipView = (TextView) findViewById(R.id.ip_address);
//        ipView.setText("IPアドレス:" + IPAddress + "\nポート:" + comPort);
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

    public void showText(final String text) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
