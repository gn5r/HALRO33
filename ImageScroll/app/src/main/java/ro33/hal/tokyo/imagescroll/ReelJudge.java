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
                remainder = now + 60;
            }
        }//リプレイ
        if (role == "ベル" || role == "強ベル" || role == "チャンス目A") {
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
            } else if (720 >= now && now >= 640) {
                remainder = now - 540;
            } else if (620 >= now && now >= 580) {
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
            } else if (420 >= now && now >= 340) {
                remainder = now - 340;
            } else if (320 >= now && now >= 200) {
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
        }//リプレイ
        if (role == "ベル" || role == "強ベル" || role == "チャンス目A") {
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
        }//ベル  強ベル  チャンス目A
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
        if (role == "チャンス目B") {
            if ((left == 5 || left == 12) && (right == 4 || right == 8 || right == 13 || right == 17 || right == 21)) {
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
            } else if ((left == 7 || left == 17) && (right == 2 || right == 6 || right == 10 || right == 15 || right == 19)) {
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
            }else if (1020 >= now && now >= 880) {
                remainder = now - 880;
            } else if (860 >= now && now >= 680) {
                remainder = now - 680;
            } else if (660 >= now && now >= 480) {
                remainder = now - 480;
            } else if (460 >= now && now >= 240) {
                remainder = now - 240;
            } else if (220 >= now && now >= 100) {
                remainder = now - 200;
            } else if (80 >= now && now >= 0) {
                remainder = now + 160;
            }
        }

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
            switch (left) {
                case 21:
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
                    } else if (620 >= now && now >= 480) {
                        remainder = now - 480;
                    } else if (460 >= now && now >= 400) {
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
                default:
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
            }
        }//リプレイ
        if (role == "ベル" || role == "強ベル" || role == "チャンス目A") {
            byte B;
            if (left == 7 || left == 11 || left == 14) {
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
            } else if (40 <= now && now >= 0) {
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
