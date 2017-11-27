package com.example.shelleden.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditText = null;
    private TextView mText = null;
    private Button mLedOn = null;
    private Button mLedOff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText)findViewById(R.id.edittext);
        mText = (TextView)findViewById(R.id.result);
        mLedOn = (Button)findViewById(R.id.ledon);
        mLedOff = (Button)findViewById(R.id.ledoff);

        mLedOn.setOnClickListener(this);
        mLedOff.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ledon:
                sendLedOperation("ON");
                Log.v("LED" ,"ON");
                break;
            case R.id.ledoff:
                sendLedOperation("OFF");
                Log.v("LED", "OFF");
                break;
            default:
        }
    }

    private void sendLedOperation(String OnOff) {
        String editText = mEditText.getText().toString();
        String ip_and_port[] = editText.split(":");
        AsyncSocket socket = new AsyncSocket(ip_and_port[0], ip_and_port[1], mText);
        socket.execute(OnOff);
        socket = null;
    }
}