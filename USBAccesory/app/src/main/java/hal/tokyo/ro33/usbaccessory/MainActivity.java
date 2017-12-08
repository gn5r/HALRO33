package hal.tokyo.ro33.usbaccessory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private UsbManager usbManager;
    private UsbAccessory usbAccessory;
    private final String USB_PERMISSION = "hal.tokyo.ro33.usbaccessory.USB_PERMISSION";

    private ParcelFileDescriptor descriptor;
    private FileInputStream inputStream;
    private FileOutputStream outputStream;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usbManager = (UsbManager) getSystemService(USB_SERVICE);
        IntentFilter filter = new IntentFilter(USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        registerReceiver(usbReceiver,filter);

        setContentView(R.layout.main);
        handler = new Handler();
    }

    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    usbAccessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (usbAccessory != null) {
                            openAccessory(usbAccessory);
                        }
                    } else {
                        Log.d("info", "permission denied" + usbAccessory);
                    }
                }
            }
        }
    };

    private void openAccessory(UsbAccessory accessory){
        descriptor = usbManager.openAccessory(accessory);
        if(descriptor != null){
            usbAccessory = accessory;
            FileDescriptor fileDescriptor = descriptor.getFileDescriptor();
            inputStream = new FileInputStream(fileDescriptor);
            outputStream = new FileOutputStream(fileDescriptor);
            Thread thread = new USBAccessoryThread(outputStream,inputStream,MainActivity.this);
            thread.start();
            Toast.makeText(MainActivity.this,"Threadスタート！",Toast.LENGTH_SHORT).show();
        }
    }

    public void showText(final String text){
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView)findViewById(R.id.viewText);
                textView.setText(text);
            }
        });
    }
}
