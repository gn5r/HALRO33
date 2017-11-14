package via.gn5r.com.androidsample;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Handler handler =new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                (new UDPGetTask(MainActivity.this)).execute();
                handler.postDelayed(this,1000);
                Log.d("info","実行した");
            }
        };
        handler.post(runnable);
//        new UDPGetTask(this).execute();
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editMessage);
        String message = editText.getText().toString();
        try {
            new UDPSendTask(MainActivity.this, "192.168.0.176", 5555).execute(message);
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

    public void viewText(final String text){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText(text);
            }
        });
    }
}
