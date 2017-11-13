package via.gn5r.com.androidserialsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.hardware.usb.UsbManager.EXTRA_ACCESSORY;

public class MainActivity extends AppCompatActivity {

    private final String ACTION_USB_PERMISSION = "via.gn5r.com.androidserialsample.USB_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        UsbManager manager = (UsbManager)getSystemService(Context.USB_SERVICE);

        BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
                    UsbAccessory accessory = (UsbAccessory)intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                    if (accessory != null) {
                        // call your method that cleans up and closes communication with the accessory
                    }
                }
            }
        };
    }


}
