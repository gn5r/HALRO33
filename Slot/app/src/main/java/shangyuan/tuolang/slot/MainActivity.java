package shangyuan.tuolang.slot;

import android.graphics.drawable.AnimationDrawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setSoundPool();
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
}
