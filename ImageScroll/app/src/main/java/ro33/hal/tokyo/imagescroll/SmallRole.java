package ro33.hal.tokyo.imagescroll;

import java.util.Random;

/**
 * Created by shelleden on 2018/01/26.
 */

public class SmallRole implements Runnable {

    String role;
    Random random = new Random();

    @Override
    public void run() {

        int iRand = random.nextInt(65535);

        if (iRand >= 0 && iRand <= 39844) {
            role = "ハズレ";
        } else if (iRand >= 39845 && iRand <= 56228) {
            role = "リプレイ";
        } else if (iRand >= 56289 && iRand <= 62781) {
            role = "ベル";
        } else if (iRand >= 62782 && iRand <= 62831) {
            role = "強ベル";
        } else if (iRand >= 62832 && iRand <= 64001) {
            role = "スイカ";
        } else if (iRand >= 64002 && iRand <= 64216) {
            role = "強スイカ";
        } else if (iRand >= 64217 && iRand <= 64884) {
            role = "チェリー";
        } else if (iRand >= 64885 && iRand <= 65200) {
            role = "強チェリー";
        } else if (iRand >= 65201 && iRand <= 65363) {
            role = "チャンス目A";
        } else if (iRand >= 65364 && iRand <= 65526) {
            role = "チャンス目B";
        } else if (iRand >= 65527 && iRand <= 65535) {
            role = "中チェ";
        }
    }

    public String getSmallRole() {
        role = "ベル";
        return role;
    }
}