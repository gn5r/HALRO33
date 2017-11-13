package via.gn5r.com.androidsample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void sendMessage(View view){
        EditText editText = (EditText)findViewById(R.id.editMessage);
        String message = editText.getText().toString();
        try {
            new UDPSendTask(MainActivity.this,"192.168.0.176", 5555).execute(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        editText.setText("");
    }

    public void showText(final String text){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
