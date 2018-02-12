package ro33.hal.tokyo.imagescroll;

/**
 * Created by shelleden on 2018/02/02.
 */

public class SetPlace {

    int left;
    int left_num;
    int right;
    int right_num;
    int center;
    int center_num;

    public void setLeft(int left) {
        this.left = left;
        if (left == 980) {
            left_num = 2;
        } else if (left == 920) {
            left_num = 3;
        } else if (left == 880) {
            left_num = 4;
        } else if (left == 840) {
            left_num = 5;
        } else if (left == 780) {
            left_num = 6;
        } else if (left == 740) {
            left_num = 7;
        } else if (left == 680) {
            left_num = 8;
        } else if (left == 640) {
            left_num = 9;
        } else if (left == 580) {
            left_num = 10;
        } else if (left == 540) {
            left_num = 11;
        } else if (left == 480) {
            left_num = 12;
        } else if (left == 440) {
            left_num = 13;
        } else if (left == 400) {
            left_num = 14;
        } else if (left == 340) {
            left_num = 15;
        } else if (left == 300) {
            left_num = 16;
        } else if (left == 240) {
            left_num = 17;
        } else if (left == 200) {
            left_num = 18;
        } else if (left == 140) {
            left_num = 19;
        } else if (left == 100) {
            left_num = 20;
        } else if (left == 60) {
            left_num = 21;
        } else {
            left_num = 1;
        }
    }

    public void setRight(int right) {
        this.right = right;
        if (right == 980) {
            right_num = 2;
        } else if (right == 920) {
            right_num = 3;
        } else if (right == 880) {
            right_num = 4;
        } else if (right == 840) {
            right_num = 5;
        } else if (right == 780) {
            right_num = 6;
        } else if (right == 740) {
            right_num = 7;
        } else if (right == 680) {
            right_num = 8;
        } else if (right == 640) {
            right_num = 9;
        } else if (right == 580) {
            right_num = 10;
        } else if (right == 540) {
            right_num = 11;
        } else if (right == 480) {
            right_num = 12;
        } else if (right == 440) {
            right_num = 13;
        } else if (right == 400) {
            right_num = 14;
        } else if (right == 340) {
            right_num = 15;
        } else if (right == 300) {
            right_num = 16;
        } else if (right == 240) {
            right_num = 17;
        } else if (right == 200) {
            right_num = 18;
        } else if (right == 140) {
            right_num = 19;
        } else if (right == 100) {
            right_num = 20;
        } else if (right == 60) {
            right_num = 21;
        } else {
            right_num = 1;
        }
    }

    public void setCenter(int center) {
        if (center == 980) {
            center_num = 2;
        } else if (center == 920) {
            center_num = 3;
        } else if (center == 880) {
            center_num = 4;
        } else if (center == 840) {
            center_num = 5;
        } else if (center == 780) {
            center_num = 6;
        } else if (center == 740) {
            center_num = 7;
        } else if (center == 680) {
            center_num = 8;
        } else if (center == 640) {
            center_num = 9;
        } else if (center == 580) {
            center_num = 10;
        } else if (center == 540) {
            center_num = 11;
        } else if (center == 480) {
            center_num = 12;
        } else if (center == 440) {
            center_num = 13;
        } else if (center == 400) {
            center_num = 14;
        } else if (center == 340) {
            center_num = 15;
        } else if (center == 300) {
            center_num = 16;
        } else if (center == 240) {
            center_num = 17;
        } else if (center == 200) {
            center_num = 18;
        } else if (center == 140) {
            center_num = 19;
        } else if (center == 100) {
            center_num = 20;
        } else if (center == 60) {
            center_num = 21;
        } else {
            center_num = 1;
        }
        this.center = center;
    }

    public int getLeft() {


        return left_num;
    }

    public int getRight() {
        return right_num;
    }
}
