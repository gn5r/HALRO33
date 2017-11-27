package com.example.shelleden.myapplication;

import android.os.AsyncTask;
import android.widget.TextView;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class AsyncSocket extends AsyncTask<String ,Void ,String> {
    private String mIpAddress = null;
    private Integer mPortNo = 0;
    private TextView mText = null;

    public AsyncSocket(String ipStr ,String portStr ,TextView text) {
        mIpAddress = ipStr;
        mPortNo = Integer.parseInt(portStr);
        mText = text;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            Socket socket = new Socket(mIpAddress ,mPortNo);
            OutputStream out = socket.getOutputStream();
            out.write(params[0].getBytes());
            out.flush();
            out.close();
            socket.close();
            return params[0];
        } catch (IOException e) {
            e.printStackTrace();
            return "Failure";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        mText.setText(s);
    }
}