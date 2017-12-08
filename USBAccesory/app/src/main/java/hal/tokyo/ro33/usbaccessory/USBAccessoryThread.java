package hal.tokyo.ro33.usbaccessory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gn5r on 17/12/04.
 */

public class USBAccessoryThread extends Thread {
    private FileOutputStream outputStream;
    private FileInputStream inputStream;
    private MainActivity mainActivity;


    public USBAccessoryThread(FileOutputStream outputStream, FileInputStream inputStream, MainActivity mainActivity) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {

        try{
            InputStreamReader reader = new InputStreamReader(inputStream);
//            StringBuilder builder = new StringBuilder();
//            char[] buf = new char[256];
//            int read;
//            while (0 <= (read = reader.read(buf))){
//                builder.append(buf,0,read);
//            }
            byte[] buf = new byte[256];
            int length = inputStream.read(buf);
            mainActivity.showText(new String(buf,0,length,"UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
