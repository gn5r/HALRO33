 package via.gn5r.com.arduinosample;

import android.content.Context;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hoho.android.usbserial.driver.*;


import java.io.IOException;

 public class MainActivity extends AppCompatActivity {

     private UsbSerialDriver usbSerialDriver;
     private Handler serialHndler;
     private ParcelFileDescriptor fileDescriptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        UsbManager manager = (UsbManager)getSystemService(Context.USB_SERVICE);
        UsbAccessory accessory = (UsbAccessory)getIntent().getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
        serialHndler = new Handler();

        SerialReadThread();
    }

    public void SerialReadThread() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                        byte buf[] = new byte[256];
                        int index = usbSerialDriver.read(buf,buf.length);
                        if(index > 0){
                            Log.d("info",new String(buf,0,index));
                            viewText(new String(buf,0,index));
                            Thread.sleep(100);
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void viewText(final String text){
        serialHndler.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText(text);
            }
        });
    }
}
