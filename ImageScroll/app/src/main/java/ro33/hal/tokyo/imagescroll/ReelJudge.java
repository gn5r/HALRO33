package ro33.hal.tokyo.imagescroll;

import android.util.Log;

public class ReelJudge {

    int now;
    int remainder;
    String role;
    SetPlace setPlace;

    public ReelJudge(SetPlace setPlace) {
        this.setPlace = setPlace;
    }

    public int Judge(String role, byte place) {
        this.role = role;
        switch (place) {
            case 1:
                leftReel();
                break;
            case 2:
                centerReel();
                break;
            case 3:
                rightReel();
                break;
            default:
                break;
        }
        remainder = remainder / 20;
        return remainder;
    }

    private void leftReel() {
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
            } else {
                remainder = now + 40;
            }
        } //ハズレ
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
            } else {
                remainder = now;
            }
        }//リプレイ
        if (role == "ベル" || role == "強ベル" || role == "チャンス目A") {
            if (1020 >= now && now >= 980) {
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
            } else if (380 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 60) {
                remainder = now - 60;
            } else {
                remainder = now + 40;
            }
        }//  ベル  強ベル チャンス目A)
        if (role == "スイカ") {
            if (1020 >= now && now >= 840) {
                remainder = now - 840;
            } else if (820 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 480) {
                remainder = now - 480;
            } else if (460 >= now && now >= 340) {
                remainder = now - 340;
            } else if (320 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 0) {
                remainder = now;
            }
        }//スイカ
        if (role == "強スイカ" || role == "チャンス目B") {
            if (1020 >= now && now >= 980) {
                remainder = now - 840;
            } else if (960 >= now && now >= 780) {
                remainder = now - 780;
            } else if (760 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 680) {
                remainder = now - 540;
            } else if (660 >= now && now >= 580) {
                remainder = now - 480;
            } else if (560 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 0) {
                remainder = now;
            }
        }//強スイカ  チャンス目B
        if (role == "チェリー" || role == "強チェリー") {
            if (1020 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 680) {
                remainder = now - 680;
            } else if (660 >= now && now >= 580) {
                remainder = now - 580;
            } else if (560 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 400) {
                remainder = now - 400;
            } else if (380 >= now && now >= 200) {
                remainder = now - 200;
            } else if (180 >= now && now >= 100) {
                remainder = now - 100;
            } else if (80 >= now && now >= 0) {
                remainder = now + 40;
            }
        }//チェリー  強チェリー
        if (role == "中チェ") {
            if (1020 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 840) {
                remainder = now - 680;
            } else if (820 >= now && now >= 640) {
                remainder = now - 640;
            } else if (620 >= now && now >= 580) {
                remainder = now - 580;
            } else if (560 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 400) {
                remainder = now - 200;
            } else if (380 >= now && now >= 140) {
                remainder = now - 140;
            } else if (120 >= now && now >= 100) {
                remainder = now - 100;
            } else if (80 >= now && now >= 0) {
                remainder = now + 40;
            }
        }//中チェ
    }

    private void centerReel() {
        int left = setPlace.getLeft();
        int right = setPlace.getRight();
        if (role == "ハズレ") {
            byte M;
            byte MR = 0;
            if (left == 2) {
                M = 1;
            } else if (left == 5) {
                M = 2;
            } else if (left == 15) {
                M = 3;
            } else if (left == 7 || left == 17) {
                M = 4;
            } else if (left == 11) {
                M = 5;
            } else {
                M = 6;
            }
            if (right == 1 || right == 3 || right == 5 || right == 7 || right == 9
                    || right == 11 || right == 14 || right == 16 || right == 18 || right == 20) {
                MR = 1;
            }
            if (now == 1020) {
                remainder = 0;
            } else if (1000 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 580) {
                remainder = now - 580;
            } else if (560 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 140) {
                remainder = now - 140;
            } else {
                remainder = now;
            }

            switch (M) {
                case 1:
                    if (1000 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 880) {
                        remainder = now - 740;
                    } else if (560 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 440) {
                        remainder = now - 400;
                    }
                    break;//case 1
                case 2:
                    if (1020 == now) {
                        remainder = now - 920;
                    } else if (860 >= now && now >= 740) {
                        remainder = now - 680;
                    } else if (560 >= now && now >= 480) {
                        remainder = now - 480;
                    } else if (460 >= now && now >= 440) {
                        remainder = now - 300;
                    } else if (120 >= now && now >= 100) {
                        remainder = now - 100;
                    } else if (80 >= now && now >= 0) {
                        remainder = now + 100;
                    }
                    break;//case 2
                case 3:
                    if (1020 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 880) {
                        remainder = now - 740;
                    } else if (120 >= now && now >= 0) {
                        if (MR == 1) {
                            if (120 >= now && now >= 60) {
                                remainder = now - 60;
                            } else if (40 >= now && now >= 0) {
                                remainder = now + 100;
                            }
                        } else {
                            if (120 >= now && now >= 100) {
                                remainder = now - 100;
                            } else if (100 >= now && now >= 0) {
                                remainder = now + 100;
                            }
                        }
                    }
                    if (MR == 1) {
                        if (560 >= now && now >= 540) {
                            remainder = now - 540;
                        } else if (520 >= now && now >= 440) {
                            remainder = now - 400;
                        }
                    } else {
                        if (560 >= now && now >= 480) {
                            remainder = now - 480;
                        } else if (460 >= now && now >= 440) {
                            remainder = now - 300;
                        }
                    }
                    break;//case 3
                case 4:
                    if (MR == 1) {
                        if (720 >= now && now >= 680) {
                            remainder = now - 680;
                        } else if (660 >= now && now >= 580) {
                            remainder = now - 540;
                        } else if (280 >= now && now >= 240) {
                            remainder = now - 240;
                        } else if (220 >= now && now >= 140) {
                            remainder = now - 100;
                        }
                    } else {
                        if (1000 >= now && now >= 880) {
                            if (right != 4) {
                                remainder = now - 780;
                            }
                        } else if (720 >= now && now >= 580) {
                            if (right == 4 || right == 17) {
                                if (720 >= now && now >= 640) {
                                    remainder = now - 640;
                                } else if (620 >= now && now >= 580) {
                                    remainder = now - 540;
                                }
                            }
                        } else if (420 >= now && now >= 400) {
                            remainder = now - 400;
                        }
                        if (380 >= now && now >= 300) {
                            remainder = now - 240;
                        }
                    }
                    break;//case 4
                case 5:
                    if (1020 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 880) {
                        remainder = now - 740;
                    } else if (720 >= now && now >= 580) {
                        remainder = now - 540;
                    } else if (560 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (560 >= now && now >= 340) {
                        remainder = now - 340;
                    } else if (280 >= now && now >= 140) {
                        if (MR == 1) {
                            if (280 >= now && now >= 240) {
                                remainder = now - 240;
                            } else if (220 >= now && now >= 140) {
                                remainder = now - 100;
                            }
                        }
                    } else if (120 >= now && now >= 0) {
                        if (MR == 1) {
                            if (120 >= now && now >= 100) {
                                remainder = now - 100;
                            } else if (80 >= now && now >= 0) {
                                remainder = now + 100;
                            }
                        } else {
                            remainder = now;
                        }
                    }
                    break;//case 5
                case 6:
                    if (MR == 1) {
                        if (1000 >= now && now >= 880) {
                            remainder = now - 840;
                        } else if (860 >= now && now >= 780) {
                            remainder = now - 780;
                        } else if (760 >= now && now >= 740) {
                            remainder = now - 640;
                        } else if (560 >= now && now >= 440) {
                            remainder = now - 400;
                        } else if (420 >= now && now >= 400) {
                            remainder = now - 400;
                        } else if (380 >= now && now >= 300) {
                            remainder = now - 200;
                        } else if (280 >= now && now >= 200) {
                            remainder = now - 200;
                        } else if (180 >= now && now >= 140) {
                            remainder = now - 60;
                        }
                    } else {
                        if (860 >= now && now >= 740) {
                            if (right == 4) {
                                if (860 >= now && now >= 780) {
                                    remainder = now - 780;
                                } else if (760 >= now && now >= 740) {
                                    remainder = now - 680;
                                }
                            }
                        } else if (560 >= now && now >= 480) {
                            remainder = now - 480;
                        } else if (460 >= now && now >= 440) {
                            remainder = now - 340;
                        }
                    }
                    break;//case 6
                default:
                    break;
            }
        }//ハズレ
        if (role == "リプレイ") {
            if (1020 >= now && now >= 840) {
                remainder = now - 840;
            } else if (820 >= now && now >= 640) {
                remainder = now - 640;
            } else if (620 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 200) {
                remainder = now - 200;
            } else if (180 >= now && now >= 60) {
                remainder = now - 60;
            } else {
                remainder = now+180;
            }
        }//リプレイ
        if (role == "ベル" || role == "強ベル") {
            byte BL;
            byte BR;
            byte BC;
            if (left == 13 || left == 16 || left == 21) {
                BL = 1;
            } else if (left == 7 || left == 11 || left == 14 || left == 17) {
                BL = 2;
            } else {
                BL = 3;
            }
            if (right == 1 || right == 5 || right == 9 || right == 14 || right == 18) {
                BR = 1;
            } else if (right == 2 || right == 6 || right == 10 || right == 15 || right == 19) {
                BR = 2;
            } else {
                BR = 3;
            }
            if (BL == 1 && BR == 1) {
                BC = 1;
            } else if ((BL == 1 && BR == 3) || (BL == 3 && BR == 1) || (BL == 2 && BR == 2)) {
                BC = 2;
            } else {
                BC = 3;
            }
            switch (BC) {
                case 1:
                    if (1020 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (720 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 300) {
                        remainder = now - 300;
                    } else if (280 >= now && now >= 140) {
                        remainder = now - 140;
                    } else {
                        remainder = now + 100;
                    }
                    break;
                case 2:
                    if (1020 >= now && now >= 880) {
                        remainder = now - 880;
                    } else if (860 >= now && now >= 680) {
                        remainder = now - 680;
                    } else if (660 >= now && now >= 480) {
                        remainder = now - 480;
                    } else if (460 >= now && now >= 240) {
                        remainder = now - 240;
                    } else if (220 >= now && now >= 100) {
                        remainder = now - 100;
                    } else {
                        remainder = now + 140;
                    }
                    break;
                case 3:
                    if (1020 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 640) {
                        remainder = now - 640;
                    } else if (620 >= now && now >= 440) {
                        remainder = now - 440;
                    } else if (420 >= now && now >= 200) {
                        remainder = now - 200;
                    } else if (180 >= now && now >= 60) {
                        remainder = now - 60;
                    } else {
                        remainder = now + 180;
                    }
                    break;
            }
        }//ベル  強ベル
        if (role == "チャンス目A") {
            byte CL;
            byte CR;
            byte CC;
            if (left == 13 || left == 16 || left == 21) {
                CL = 1;
            } else if (left == 7 || left == 11 || left == 14 || left == 17) {
                CL = 2;
            } else {
                CL = 3;
            }
            if (right == 1 || right == 5 || right == 9 || right == 14 || right == 18) {
                CR = 1;
            } else if (right == 2 || right == 6 || right == 10 || right == 15 || right == 19) {
                CR = 2;
            } else {
                CR = 3;
            }
            if (CL == 1 && CR == 1) {
                CC = 1;
            } else if ((CL == 1 && CR == 3) || (CL == 3 && CR == 1) || (CL == 2 && CR == 2)) {
                CC = 2;
            } else {
                CC = 3;
            }
            switch (CC) {
                case 1:
                    if (1020 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 640) {
                        remainder = now - 640;
                    } else if (620 >= now && now >= 440) {
                        remainder = now - 440;
                    } else if (420 >= now && now >= 200) {
                        remainder = now - 200;
                    } else if (180 >= now && now >= 60) {
                        remainder = now - 60;
                    } else {
                        remainder = now + 180;
                    }
                    break;
                case 2:
                    if (1020 >= now && now >= 980) {
                        remainder = now - 980;
                    } else if (960 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (720 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 340) {
                        remainder = now - 340;
                    } else if (320 >= now && now >= 300) {
                        remainder = now - 300;
                    } else if (280 >= now && now >= 0) {
                        remainder = now + 40;
                    }
                    break;
                case 3:
                    if (1020 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (720 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 300) {
                        remainder = now - 300;
                    } else if (280 >= now && now >= 140) {
                        remainder = now - 140;
                    } else {
                        remainder = now + 100;
                    }
                    break;
            }
        }//チャンス目A
        if (role == "スイカ") {
            byte WL;
            byte WR;

            if (left == 5 || left == 12 || left == 15) {//上
                WL = 1;
            } else {
                WL = 2;
            }
            if (right == 4 || right == 8 || right == 13 || right == 17 || right == 21) {//上
                WR = 1;
            } else {
                WR = 2;
            }

            if (WL == 1 && WR == 1) {
                if (1020 == now) {
                    remainder = 0;
                } else if (1000 >= now && now >= 880) {
                    remainder = now - 880;
                } else if (860 >= now && now >= 680) {
                    remainder = now - 680;
                } else if (720 >= now && now >= 580) {
                    remainder = now - 580;
                } else if (560 >= now && now >= 400) {
                    remainder = now - 400;
                } else if (380 >= now && now >= 200) {
                    remainder = now - 240;
                } else {
                    remainder = now;
                }
            } else if (WL == 2 && WR == 2) {
                if (1020 >= now && now >= 920) {
                    remainder = now - 920;
                } else if (900 >= now && now >= 840) {
                    remainder = now - 840;
                } else if (820 >= now && now >= 740) {
                    remainder = now - 740;
                } else if (720 >= now && now >= 640) {
                    remainder = now - 640;
                } else if (620 >= now && now >= 480) {
                    remainder = now - 440;
                } else if (460 >= now && now >= 300) {
                    remainder = now - 300;
                } else if (280 >= now && now >= 200) {
                    remainder = now - 200;
                } else if (180 >= now && now >= 140) {
                    remainder = now - 140;
                } else {
                    remainder = now + 40;
                }
            } else {
                if (1020 >= now && now >= 980) {
                    remainder = now - 980;
                } else if (960 >= now && now >= 840) {
                    remainder = now - 840;
                } else if (820 >= now && now >= 680) {
                    remainder = now - 740;
                } else if (660 >= now && now >= 540) {
                    remainder = now - 480;
                } else if (520 >= now && now >= 340) {
                    remainder = now - 340;
                } else if (320 >= now && now >= 200) {
                    remainder = now - 200;
                } else if (180 >= now && now >= 100) {
                    remainder = now - 100;
                } else if (80 >= now && now >= 0) {
                    remainder = now + 40;
                }
            }
        }//スイカ
        if (role == "チャンス目B") {
            if ((left == 5 || left == 12) && (right == 4 || right == 8 || right == 13 || right == 17 || right == 21)) {
                if (1020 >= now && now >= 980) {
                    remainder = now - 980;
                } else if (960 >= now && now >= 880) {
                    remainder = now - 880;
                } else if (860 >= now && now >= 680) {
                    remainder = now - 680;
                } else if (660 >= now && now >= 580) {
                    remainder = now - 580;
                } else if (560 >= now && now >= 540) {
                    remainder = now - 480;
                } else if (520 >= now && now >= 340) {
                    remainder = now - 340;
                } else if (320 >= now && now >= 240) {
                    remainder = now - 240;
                } else if (220 >= now && now >= 140) {
                    remainder = now - 140;
                } else {
                    remainder = now + 40;
                }
            } else if ((left == 7 || left == 17) && (right == 2 || right == 6 || right == 10 || right == 15 || right == 19)) {
                if (1020 >= now && now >= 980) {
                    remainder = now - 980;
                } else if (960 >= now && now >= 840) {
                    remainder = now - 840;
                } else if (820 >= now && now >= 740) {
                    remainder = now - 740;
                } else if (720 >= now && now >= 640) {
                    remainder = now - 640;
                } else if (620 >= now && now >= 540) {
                    remainder = now - 440;
                } else if (520 >= now && now >= 340) {
                    remainder = now - 340;
                } else if (320 >= now && now >= 200) {
                    remainder = now - 200;
                } else if (180 >= now && now >= 140) {
                    remainder = now - 140;
                } else {
                    remainder = now + 40;
                }
            } else if (1020 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 680) {
                remainder = now - 680;
            } else if (660 >= now && now >= 480) {
                remainder = now - 480;
            } else if (460 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 100) {
                remainder = now - 100;
            } else if (80 >= now && now >= 0) {
                remainder = now + 160;
            }
        }//チャンス目B
        if (role == "強スイカ") {
            if (left == 5 || left == 7 || left == 12 || left == 17) {
                if ((left == 5 || left == 12) && (right == 4 || right == 8 || right == 13 || right == 17 || right == 21)) {
                    if (1020 == now) {
                        remainder = 0;
                    } else if (1000 >= now && now >= 880) {
                        remainder = now - 880;
                    } else if (860 >= now && now >= 780) {
                        remainder = now - 780;
                    } else if (760 >= now && now >= 680) {
                        remainder = now - 680;
                    } else if (660 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 400) {
                        remainder = now - 400;
                    } else if (380 >= now && now >= 240) {
                        remainder = now - 240;
                    } else if (220 >= now && now >= 100) {
                        remainder = now - 100;
                    } else if (80 >= now && now >= 0) {
                        remainder = now;
                    }
                } else if ((left == 7 || left == 17) && (right == 2 || right == 6 || right == 10 || right == 15 || right == 19)) {
                    if (1020 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 780) {
                        remainder = now - 780;
                    } else if (760 >= now && now >= 640) {
                        remainder = now - 640;
                    } else if (620 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 440) {
                        remainder = now - 440;
                    } else if (420 >= now && now >= 300) {
                        remainder = now - 240;
                    } else if (280 >= now && now >= 140) {
                        remainder = now - 100;
                    } else if (120 >= now && now >= 60) {
                        remainder = now - 60;
                    } else {
                        remainder = now + 100;
                    }
                } else {
                    if (1020 >= now && now >= 980) {
                        remainder = now - 980;
                    } else if (960 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 680) {
                        remainder = now - 740;
                    } else if (660 >= now && now >= 480) {
                        remainder = now - 480;
                    } else if (460 >= now && now >= 340) {
                        remainder = now - 340;
                    } else if (320 >= now && now >= 200) {
                        remainder = now - 200;
                    } else if (180 >= now && now >= 100) {
                        remainder = now - 100;
                    } else if (80 >= now && now >= 0) {
                        remainder = now + 40;
                    }
                }
            } else {
                if (1020 >= now && now >= 980) {
                    remainder = now - 980;
                } else if (960 >= now && now >= 840) {
                    remainder = now - 840;
                } else if (820 >= now && now >= 680) {
                    remainder = now - 740;
                } else if (660 >= now && now >= 480) {
                    remainder = now - 480;
                } else if (460 >= now && now >= 340) {
                    remainder = now - 340;
                } else if (320 >= now && now >= 200) {
                    remainder = now - 200;
                } else if (180 >= now && now >= 100) {
                    remainder = now - 100;
                } else if (80 >= now && now >= 0) {
                    remainder = now + 40;
                }
            }
        }//強スイカ
        if (role == "チェリー") {
            if (now == 1020) {
                remainder = 0;
            } else if (1000 >= now && now >= 920) {
                remainder = now - 920;
            } else if (900 >= now && now >= 780) {
                remainder = now - 780;
            } else if (760 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 580) {
                remainder = now - 580;
            } else if (560 >= now && now >= 540) {
                remainder = now - 540;
            } else if (520 >= now && now >= 400) {
                remainder = now - 400;
            } else if (380 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 140) {
                remainder = now - 140;
            } else if (80 >= now && now >= 0) {
                remainder = now;
            }
            if (left == 4 && right == 2) {
                if (900 >= now && now >= 840) {
                    remainder = now - 840;
                } else if (820 >= now && now >= 780) {
                    remainder = now - 740;
                }
            } else if (left == 15) {
                if (now == 1020) {
                    remainder = now - 920;
                } else if (520 >= now && now >= 480) {
                    remainder = now - 480;
                } else if (460 >= now && now >= 400) {
                    remainder = now - 340;
                }
            }
        }//チェリー
        if (role == "強チェリー") {

            if (now == 1020) {
                remainder = 0;
            } else if (1000 >= now && now >= 920) {
                remainder = now - 920;
            } else if (900 >= now && now >= 780) {
                remainder = now - 780;
            } else if (760 >= now && now >= 740) {
                remainder = now - 740;
            } else if (720 >= now && now >= 580) {
                remainder = now - 580;
            } else if (560 >= now && now >= 540) {
                remainder = now - 540;
            } else if (520 >= now && now >= 400) {
                remainder = now - 400;
            } else if (380 >= now && now >= 300) {
                remainder = now - 300;
            } else if (280 >= now && now >= 140) {
                remainder = now - 140;
            } else if (80 >= now && now >= 0) {
                remainder = now;
            }
            if (left == 4 || left == 2) {
                if (right == 3) {
                    if (900 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 740) {
                        remainder = now - 740;
                    }
                }
            } else if (left == 8 || left == 18) {
                if (right == 3) {
                    if (900 >= now && now >= 880) {
                        remainder = now - 880;
                    } else if (860 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (560 >= now && now >= 540) {
                        remainder = now - 480;
                    }
                } else if (right == 5 || right == 18) {
                    if (900 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (560 >= now && now >= 540) {
                        remainder = now - 440;
                    }
                }
            } else if (left == 10 || right == 13) {
                if (right == 5 || right == 18) {
                    if (1000 >= now && now >= 920) {
                        remainder = now - 880;
                    } else if (760 >= now && now >= 740) {
                        remainder = now - 680;
                    } else if (560 >= now && now >= 540) {
                        remainder = now - 480;
                    } else if (280 >= now && now >= 240) {
                        remainder = now - 240;
                    } else if (220 >= now && now >= 200) {
                        remainder = now - 200;
                    } else if (180 >= now && now >= 140) {
                        remainder = now - 100;
                    }
                }
            } else if (left == 15) {
                if (right == 8 || right == 13 || right == 21) {
                    if (now == 1020) {
                        remainder = now - 920;
                    } else if (520 >= now && now >= 480) {
                        remainder = now - 480;
                    } else if (460 >= now && now >= 400) {
                        remainder = now - 340;
                    }
                }
            }
        }//強チェリー
        if (role == "中チェ") {
            if (left == 9) {
                if (620 >= now && now >= 540) {
                    remainder = now - 540;
                }
            } else if (left == 19) {
                if (860 >= now && now >= 780) {
                    remainder = now - 780;
                }
            } else {
                if (now == 1020) {
                    remainder = 0;
                } else if (1000 >= now && now >= 920) {
                    remainder = now - 920;
                } else if (900 >= now && now >= 780) {
                    remainder = now - 780;
                } else if (760 >= now && now >= 740) {
                    remainder = now - 740;
                } else if (720 >= now && now >= 580) {
                    remainder = now - 580;
                } else if (560 >= now && now >= 540) {
                    remainder = now - 540;
                } else if (520 >= now && now >= 400) {
                    remainder = now - 400;
                } else if (380 >= now && now >= 300) {
                    remainder = now - 300;
                } else if (280 >= now && now >= 140) {
                    remainder = now - 140;
                } else if (80 >= now && now >= 0) {
                    remainder = now;
                }
                if (left == 4) {
                    if (900 >= now && now >= 780) {
                        remainder = now - 740;
                    }
                } else if (left == 5) {
                    if (right == 8 || right == 13 || right == 21) {
                        if (now == 1020) {
                            remainder = now - 980;
                        } else if ((80 >= now && now >= 0)) {
                            remainder = now + 40;
                        } else if (520 >= now && now >= 440) {
                            remainder = now - 440;
                        } else if (560 >= now && now >= 540) {
                            remainder = now - 480;
                        }
                    }
                } else if (left == 10 || left == 13) {
                    if (right == 5 || right == 18) {
                        if (1000 >= now && now >= 920) {
                            remainder = now - 880;
                        } else if (760 >= now && now >= 740) {
                            remainder = now - 680;
                        } else if (420 >= now && now >= 340) {
                            remainder = now - 340;
                        } else if (380 >= now && now >= 300) {
                            remainder = now - 240;
                        } else if (280 >= now && now >= 140) {
                            remainder = now - 100;
                        }
                    }
                } else if (left == 14) {
                    if (right == 15) {
                        if (1000 >= now && now >= 920) {
                            remainder = now - 880;
                        } else if (380 >= now && now >= 300) {
                            remainder = now - 240;
                        }
                    }
                } else if (left == 2) {
                    if (right == 3) {
                        if (900 >= now && now >= 740) {
                            remainder = now - 740;
                        }
                    }
                }
            }
        }//中チェ
    }

    private void rightReel() {
        int left = setPlace.getLeft();
        if (role == "ハズレ") {
            if (1020 <= now && now >= 980) {
                remainder = now - 980;
            } else if (960 <= now && now >= 920) {
                remainder = now - 920;
            } else if (900 <= now && now >= 880) {
                remainder = now - 880;
            } else if (860 <= now && now >= 840) {
                remainder = now - 840;
            } else if (820 <= now && now >= 780) {
                remainder = now - 780;
            } else if (760 <= now && now >= 740) {
                remainder = now - 740;
            } else if (720 <= now && now >= 680) {
                remainder = now - 680;
            } else if (660 <= now && now >= 640) {
                remainder = now - 640;
            } else if (620 <= now && now >= 580) {
                remainder = now - 580;
            } else if (560 <= now && now >= 540) {
                remainder = now - 540;
            } else if (530 <= now && now >= 480) {
                remainder = now - 480;
            } else if (460 <= now && now >= 440) {
                remainder = now - 440;
            } else if (420 <= now && now >= 400) {
                remainder = now - 400;
            } else if (380 <= now && now >= 340) {
                remainder = now - 340;
            } else if (320 <= now && now >= 300) {
                remainder = now - 300;
            } else if (280 <= now && now >= 240) {
                remainder = now - 240;
            } else if (220 <= now && now >= 200) {
                remainder = now - 200;
            } else if (180 <= now && now >= 140) {
                remainder = now - 140;
            } else if (120 <= now && now >= 100) {
                remainder = now - 100;
            } else if (80 <= now && now >= 60) {
                remainder = now - 60;
            } else if (40 <= now && now >= 0) {
                remainder = now;
            }
        }//ハズレ
        if (role == "リプレイ") {
            byte R;
            if (left == 4 || left == 14) {
                R = 1;
            } else if (left == 1 || left == 6 || left == 11 || left == 16) {
                R = 2;
            } else {
                R = 3;
            }
            switch (R) {
                case 1:
                    if (now == 1020) {
                        remainder = 0;
                    } else if (1000 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 640) {
                        remainder = now - 640;
                    } else if (620 >= now && now >= 400) {
                        remainder = now - 400;
                    } else if (380 >= now && now >= 200) {
                        remainder = now - 200;
                    } else {
                        remainder = now;
                    }
                    break;
                case 2:
                    if (1020 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (720 >= now && now >= 480) {
                        remainder = now - 480;
                    } else if (460 >= now && now >= 300) {
                        remainder = now - 300;
                    } else if (280 >= now && now >= 100) {
                        remainder = now - 100;
                    } else {
                        remainder = now + 100;
                    }
                    break;
                case 3:
                    if (1020 >= now && now >= 880) {
                        remainder = now - 880;
                    } else if (860 >= now && now >= 680) {
                        remainder = now - 680;
                    } else if (660 >= now && now >= 440) {
                        remainder = now - 440;
                    } else if (420 >= now && now >= 240) {
                        remainder = now - 240;
                    } else if (220 >= now && now >= 60) {
                        remainder = now - 60;
                    } else {
                        remainder = now + 160;
                    }
                    break;
                default:
                    break;
            }
        }//リプレイ
        if (role == "ベル" || role == "強ベル" || role == "チャンス目A") {
            byte B;
            if (left == 7 || left == 11 || left == 14 || left == 17) {
                B = 2;
            } else {
                B = 1;
            }
            switch (B) {
                case 1:
                    if (now == 1020) {
                        remainder = 0;
                    } else if (1000 >= now && now >= 920) {
                        remainder = now - 920;
                    } else if (900 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 740) {
                        remainder = now - 740;
                    } else if (720 >= now && now >= 640) {
                        remainder = now - 640;
                    } else if (620 >= now && now >= 540) {
                        remainder = now - 540;
                    } else if (520 >= now && now >= 400) {
                        remainder = now - 400;
                    } else if (380 >= now && now >= 300) {
                        remainder = now - 300;
                    } else if (280 >= now && now >= 200) {
                        remainder = now - 200;
                    } else if (180 >= now && now >= 100) {
                        remainder = now - 100;
                    } else {
                        remainder = now;
                    }
                    break;
                case 2:
                    if (1020 >= now && now >= 980) {
                        remainder = now - 980;
                    } else if (960 >= now && now >= 780) {
                        remainder = now - 780;
                    } else if (760 >= now && now >= 580) {
                        remainder = now - 580;
                    } else if (560 >= now && now >= 340) {
                        remainder = now - 340;
                    } else if (320 >= now && now >= 140) {
                        remainder = now - 140;
                    } else {
                        remainder = now + 40;
                    }
                    break;
                default:
                    break;
            }
        }//ベル  強ベル  チャンス目A
        if (role == "スイカ") {
            if (1020 >= now && now >= 980) {
                remainder = now - 980;
            } else if (960 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 780) {
                remainder = now - 780;
            } else if (760 >= now && now >= 680) {
                remainder = now - 680;
            } else if (660 >= now && now >= 580) {
                remainder = now - 580;
            } else if (560 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 340) {
                remainder = now - 340;
            } else if (320 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 140) {
                remainder = now - 140;
            } else if (100 >= now && now >= 60) {
                remainder = now - 60;
            } else {
                remainder = now + 40;
            }
        }//スイカ
        if (role == "強スイカ" || role == "チャンス目B") {

            byte W;
            if (left == 5 || left == 7 || left == 12 || left == 17) {
                W = 2;
            } else {
                W = 1;
            }

            switch (W) {
                case 1:
                    if (1020 == now) {
                        remainder = 0;
                    } else if (1000 >= now && now >= 840) {
                        remainder = now - 840;
                    } else if (820 >= now && now >= 640) {
                        remainder = now - 640;
                    } else if (620 >= now && now >= 400) {
                        remainder = now - 400;
                    } else if (380 >= now && now >= 200) {
                        remainder = now - 200;
                    } else {
                        remainder = now;
                    }
                    break;
                case 2:
                    if (1020 >= now && now >= 980) {
                        remainder = now - 980;
                    } else if (960 >= now && now >= 880) {
                        remainder = now - 880;
                    } else if (860 >= now && now >= 780) {
                        remainder = now - 780;
                    } else if (760 >= now && now >= 680) {
                        remainder = now - 680;
                    } else if (660 >= now && now >= 580) {
                        remainder = now - 580;
                    } else if (560 >= now && now >= 440) {
                        remainder = now - 440;
                    } else if (420 >= now && now >= 340) {
                        remainder = now - 340;
                    } else if (320 >= now && now >= 240) {
                        remainder = now - 240;
                    } else if (220 >= now && now >= 140) {
                        remainder = now - 140;
                    } else if (100 >= now && now >= 60) {
                        remainder = now - 60;
                    } else {
                        remainder = now + 40;
                    }
                    break;
                default:
                    break;
            }
        }//強スイカ  チャンス目B
        if (role == "チェリー") {
            if (1020 <= now && now >= 980) {
                remainder = now - 980;
            } else if (960 <= now && now >= 880) {
                remainder = now - 880;
            } else if (860 <= now && now >= 780) {
                remainder = now - 780;
            } else if (760 <= now && now >= 680) {
                remainder = now - 680;
            } else if (660 <= now && now >= 580) {
                remainder = now - 580;
            } else if (560 <= now && now >= 480) {
                remainder = now - 480;
            } else if (460 <= now && now >= 440) {
                remainder = now - 440;
            } else if (420 <= now && now >= 340) {
                remainder = now - 340;
            } else if (320 <= now && now >= 240) {
                remainder = now - 240;
            } else if (220 <= now && now >= 140) {
                remainder = now - 140;
            } else if (120 <= now && now >= 60) {
                remainder = now - 60;
            } else {
                remainder = now + 40;
            }
        }//チェリー
        if (role == "強チェリー") {
            if (1020 >= now && now >= 920) {
                remainder = now - 920;
            } else if (900 >= now && now >= 840) {
                remainder = now - 840;
            } else if (820 >= now && now >= 680) {
                remainder = now - 680;
            } else if (660 >= now && now >= 540) {
                remainder = now - 540;
            } else if (520 >= now && now >= 440) {
                remainder = now - 440;
            } else if (420 >= now && now >= 340) {
                remainder = now - 340;
            } else if (320 >= now && now >= 200) {
                remainder = now - 200;
            } else if (180 >= now && now >= 60) {
                remainder = now - 60;
            } else {
                remainder = now + 100;
            }
        }//強チェリー
        if (role == "中チェ") {
            byte C = 0;
            if (left == 9) {
                C = 1;
            } else if (left == 19) {
                C = 2;
            }

            if (C == 1) {
                if (1020 >= now && now >= 880) {
                    remainder = now - 880;
                } else if (380 >= now && now >= 240) {
                    remainder = now - 240;
                }
            } else if (C == 2) {
                if (1020 >= now && now >= 980) {
                    remainder = now - 980;
                } else if (80 >= now && now >= 0) {
                    remainder = now + 40;
                }
            } else {
                if (1020 >= now && now >= 920) {
                    remainder = now - 920;
                } else if (900 >= now && now >= 840) {
                    remainder = now - 840;
                } else if (820 >= now && now >= 680) {
                    remainder = now - 680;
                } else if (660 >= now && now >= 540) {
                    remainder = now - 540;
                } else if (520 >= now && now >= 440) {
                    remainder = now - 440;
                } else if (420 >= now && now >= 340) {
                    remainder = now - 340;
                } else if (320 >= now && now >= 200) {
                    remainder = now - 200;
                } else if (160 >= now && now >= 60) {
                    remainder = now - 60;
                } else {
                    remainder = now + 100;
                }
            }
        }//中チェ
    }

    public void setNow(int now) {
        this.now = now;
    }
}
