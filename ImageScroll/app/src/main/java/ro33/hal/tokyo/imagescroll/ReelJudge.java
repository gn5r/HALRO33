package ro33.hal.tokyo.imagescroll;

import android.util.Log;

/**
 * Created by shelleden on 2018/01/22.
 */

public class ReelJudge {

    int now;
    int remainder;

    public int Judge(String role) {


        if (role == "ハズレ") {

            if (1020 >= now && now >= 980) {
                remainder = now - 980;
            } else if (960 >= now && now >= 840) {
                remainder = now - 840;
            } else if (820 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 540) {
                remainder = now - 540;
            } else if (520 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 340) {
                remainder = now - 340;
            } else if (320 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 60) {
                remainder = now - 60;
            } else if (now == 40) {
                remainder = 80;
            } else if (now == 20) {
                remainder = 60;
            } else if (now == 0) {
                remainder = 40;
            }
        }
        if (role == "リプレイ") {
            if (now == 1020) {
                remainder = 0;
            } else if (1000 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 780) {
                remainder = now - 780;
            } else if (760 >= now && now >= 540) {
                remainder = now - 540;
            } else if (520 >= now && now >= 400) {
                remainder = now - 400;
            } else if (380 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 60) {
                remainder = now - 60;
            } else if (now == 40) {
                remainder = 40;
            } else if (now == 20) {
                remainder = 20;
            }
        }
        if (role == "ベル") {
            if (1000 >= now && now >= 980) {
                remainder = now - 980;
            } else if (960 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 540) {
                remainder = now - 540;
            } else if (520 >= now && now >= 480) {
                remainder = now - 480;
            } else if (460 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 400) {
                remainder = now - 400;
            } else if (380 >= now && now >= 340) {
                remainder = now - 340;
            } else if (320 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 60) {
                remainder = now - 60;
            } else if (now == 40) {
                remainder = 100;
            } else if (now == 20) {
                remainder = 60;
            } else if (now == 0) {
                remainder = 40;
            }

        }

        remainder = remainder / 20;
        return remainder;
    }

    public void setNow(int now) {

        this.now = now;
    }


}
