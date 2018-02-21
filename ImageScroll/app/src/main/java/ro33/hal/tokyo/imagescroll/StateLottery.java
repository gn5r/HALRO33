package ro33.hal.tokyo.imagescroll;

/**
 * Created by shelleden on 2018/02/12.
 */

import java.util.Random;

public class StateLottery implements Runnable {
    byte state = 1;
    byte newState;
    byte nextState=1;
    Random random = new Random();
    int iRand;
    int stock;

    public void run() {
        iRand = random.nextInt(65535);
    }

    private void stateSet(String role) {
        switch (state) {
            case 1:
                switch (role) {
                    case "強ベル":
                        if (0 <= iRand && iRand <= 17694) {
                            newState = 6;
                        } else if (17894 <= iRand && iRand <= 22118) {
                            newState = 7;
                        } else if (22119 <= iRand && iRand <= 29491) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強スイカ":
                        if (256 <= iRand && iRand <= 3008) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4688) {
                            newState = 7;
                        } else if (10098 <= iRand && iRand <= 11245) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強チェリー":
                        if (300 <= iRand && iRand <= 2265) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4491) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 60820) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "中段チェリー":
                        state = 7;
                        break;
                    case "チャンス目A":
                        if (1000 <= iRand && iRand <= 4932) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4983) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 61638) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "スイカ":
                        if (1000 <= iRand && iRand <= 16859) {
                            newState = 2;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チェリー":
                        if (5000 <= iRand && iRand <= 17189) {
                            newState = 2;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チャンス目B":
                        if (0 <= iRand && iRand <= 32768) {
                            newState = 2;
                        } else {
                            newState = state;
                        }
                        break;
                    default:
                        newState=state;
                }
                break;//case 1
            case 2:
                switch (role) {
                    case "強ベル":
                        if (0 <= iRand && iRand <= 17694) {
                            newState = 6;
                        } else if (17894 <= iRand && iRand <= 22118) {
                            newState = 7;
                        } else if (22119 <= iRand && iRand <= 29491) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強スイカ":
                        if (200 <= iRand && iRand <= 3739) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4885) {
                            newState = 7;
                        } else if (10000 <= iRand && iRand <= 11474) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強チェリー":
                        if (300 <= iRand && iRand <= 3052) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4688) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 61147) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "中段チェリー":
                        state = 7;
                        break;
                    case "チャンス目A":
                        if (1000 <= iRand && iRand <= 6111) {
                            newState = 6;
                        } else if (7000 <= iRand && iRand <= 8278) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 62130) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "スイカ":
                        if (1000 <= iRand && iRand <= 15614) {
                            newState = 3;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チェリー":
                        if (5000 <= iRand && iRand <= 15092) {
                            newState = 3;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チャンス目B":
                        if (0 <= iRand && iRand <= 26214) {
                            newState = 3;
                        } else {
                            newState = state;
                        }
                        break;
                    case "ハズレ":
                        if (0 <= iRand && iRand <= 6422){
                            newState=1;
                        }else {
                            newState=state;
                        }
                        break;
                    case "リプレイ":
                        if (0 <= iRand && iRand <= 6422){
                            newState=1;
                        }else {
                            newState=state;
                        }
                        break;
                    default:
                        newState=state;
                }
                break;//case 2
            case 3:
                switch (role) {
                    case "強ベル":
                        if (0 <= iRand && iRand <= 17694) {
                            newState = 6;
                        } else if (17894 <= iRand && iRand <= 22118) {
                            newState = 7;
                        } else if (22119 <= iRand && iRand <= 29491) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強スイカ":
                        if (200 <= iRand && iRand <= 4525) {
                            newState = 6;
                        } else if (5000 <= iRand && iRand <= 6081) {
                            newState = 7;
                        } else if (10000 <= iRand && iRand <= 11802) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強チェリー":
                        if (300 <= iRand && iRand <= 3839) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4885) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 61474) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "中段チェリー":
                        state = 7;
                        break;
                    case "チャンス目A":
                        if (1000 <= iRand && iRand <= 7291) {
                            newState = 6;
                        } else if (8000 <= iRand && iRand <= 9572) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 62622) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "スイカ":
                        if (1000 <= iRand && iRand <= 13189) {
                            newState = 4;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チェリー":
                        if (5000 <= iRand && iRand <= 11881) {
                            newState = 4;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チャンス目B":
                        if (0 <= iRand && iRand <= 19660) {
                            newState = 4;
                        } else {
                            newState = state;
                        }
                        break;
                    case "ハズレ":
                        if (0 <= iRand && iRand <= 6422){
                            newState=2;
                        }else {
                            newState=state;
                        }
                        break;
                    case "リプレイ":
                        if (0 <= iRand && iRand <= 6422){
                            newState=2;
                        }else {
                            newState=state;
                        }
                        break;
                    default:
                        newState=state;
                }
                break;//case 3
            case 4:
                switch (role) {
                    case "強ベル":
                        if (0 <= iRand && iRand <= 17694) {
                            newState = 6;
                        } else if (17894 <= iRand && iRand <= 22118) {
                            newState = 7;
                        } else if (22119 <= iRand && iRand <= 29491) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強スイカ":
                        if (200 <= iRand && iRand <= 10030) {
                            newState = 6;
                        } else if (30000 <= iRand && iRand <= 32457) {
                            newState = 7;
                        } else if (10000 <= iRand && iRand <= 14097) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "強チェリー":
                        if (300 <= iRand && iRand <= 6198) {
                            newState = 6;
                        } else if (8000 <= iRand && iRand <= 9474) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 62458) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "中段チェリー":
                        state = 7;
                        break;
                    case "チャンス目A":
                        if (1000 <= iRand && iRand <= 12796) {
                            newState = 6;
                        } else if (15000 <= iRand && iRand <= 17949) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 64923) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "スイカ":
                        if (300 <= iRand && iRand <= 2265) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4491) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 60820) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チェリー":
                        if (256 <= iRand && iRand <= 3008) {
                            newState = 6;
                        } else if (4000 <= iRand && iRand <= 4688) {
                            newState = 7;
                        } else if (10098 <= iRand && iRand <= 11245) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "チャンス目B":
                        if (1000 <= iRand && iRand <= 12796) {
                            newState = 6;
                        } else if (15000 <= iRand && iRand <= 17949) {
                            newState = 7;
                        } else if (60000 <= iRand && iRand <= 64923) {
                            newState = 8;
                        } else {
                            newState = state;
                        }
                        break;
                    case "ハズレ":
                        if (0 <= iRand && iRand <= 6422){
                            newState=3;
                        }else {
                            newState=state;
                        }
                        break;
                    case "リプレイ":
                        if (0 <= iRand && iRand <= 6422){
                            newState=3;
                        }else {
                            newState=state;
                        }
                        break;
                    default:
                        newState=state;
                }
                break;//case 4
        }
    }

    public byte getState(String role) {
        stateSet(role);
        state=newState;
        return newState;
    }

    public int stockLottery(String role){
        switch (role) {
            case "強ベル":
                if (0 <= iRand && iRand <= 17694) {
                    stock = 1;
                } else if (17894 <= iRand && iRand <= 22118) {
                    stock = 3;
                } else if (22119 <= iRand && iRand <= 29491) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "強スイカ":
                if (200 <= iRand && iRand <= 10030) {
                    stock = 1;
                } else if (30000 <= iRand && iRand <= 32457) {
                    stock = 3;
                } else if (10000 <= iRand && iRand <= 14097) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "強チェリー":
                if (300 <= iRand && iRand <= 6198) {
                    stock = 1;
                } else if (8000 <= iRand && iRand <= 9474) {
                    stock = 3;
                } else if (60000 <= iRand && iRand <= 62458) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "中段チェリー":
                stock = 5;
                break;
            case "チャンス目A":
                if (1000 <= iRand && iRand <= 12796) {
                    stock = 1;
                } else if (15000 <= iRand && iRand <= 17949) {
                    stock = 3;
                } else if (60000 <= iRand && iRand <= 64923) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "スイカ":
                if (300 <= iRand && iRand <= 2265) {
                    stock = 1;
                } else if (4000 <= iRand && iRand <= 4491) {
                    stock = 3;
                } else if (60000 <= iRand && iRand <= 60820) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "チェリー":
                if (256 <= iRand && iRand <= 3008) {
                    stock = 1;
                } else if (4000 <= iRand && iRand <= 4688) {
                    stock = 3;
                } else if (10098 <= iRand && iRand <= 11245) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "チャンス目B":
                if (1000 <= iRand && iRand <= 12796) {
                    stock = 1;
                } else if (15000 <= iRand && iRand <= 17949) {
                    stock = 3;
                } else if (60000 <= iRand && iRand <= 64923) {
                    stock = 2;
                } else {
                    stock = 0;
                }
                break;
            case "リプレイ":
                stock = 0;
                break;
            default:
                stock = 0;
        }
        return stock;
    }


    public byte getStockStateLotterry(){
        if (0 <= iRand && iRand <= 39321) {
            newState = 6;
        } else if (39322 <= iRand && iRand <=49151) {
            newState = 7;
        } else{
            newState = 8;
        }
        state=newState;
        return newState;
    }

    public byte getNextState() {
        return nextState;
    }

    public void endBOUNUS(String role) {
        switch (role) {
            case "強ベル":
                if (0 <= iRand && iRand <= 35389) {
                    nextState = 6;
                } else if (35390 <= iRand && iRand <= 44236){
                    nextState=7;
                }else if (44237 <= iRand && iRand <= 58982){
                    nextState=8;
                } else {
                    nextState =1;
                }
                break;
            case "強スイカ":
                if (0 <= iRand && iRand <= 27525) {
                    newState = 6;
                } else if (27526 <= iRand && iRand <= 34406) {
                    newState = 7;
                } else if (34407 <= iRand && iRand <= 45875) {
                    newState = 8;
                } else {
                    nextState=1;
                }
                break;
            case "強チェリー":
                if (0 <= iRand && iRand <= 23593) {
                    newState = 6;
                } else if (23594 <= iRand && iRand <= 29492) {
                    newState = 7;
                } else if (29493 <= iRand && iRand <= 39322) {
                    newState = 8;
                } else {
                    nextState=1;
                }
                break;
            case "中段チェリー":
                state = 7;
                break;
            case "チャンス目A":
                if (0 <= iRand && iRand <= 31457) {
                    newState = 6;
                } else if (31458 <= iRand && iRand <= 39322) {
                    newState = 7;
                } else if (39323 <= iRand && iRand <= 52428) {
                    newState = 8;
                } else {
                    nextState=1;
                }
                break;
            case "スイカ":
                if (0 <= iRand && iRand <= 11796) {
                    newState = 6;
                } else if (11797 <= iRand && iRand <= 14745) {
                    newState = 7;
                } else if (14746 <= iRand && iRand <= 19668) {
                    newState = 8;
                } else {
                    nextState=1;
                }
                break;
            case "チェリー":
                if (0 <= iRand && iRand <= 19660) {
                    newState = 6;
                } else if (19661 <= iRand && iRand <= 23592) {
                    newState = 7;
                } else if (23593 <= iRand && iRand <= 32768) {
                    newState = 8;
                } else {
                    nextState=1;
                }
                break;
            case "チャンス目B":
                if (0 <= iRand && iRand <= 31457) {
                    newState = 6;
                } else if (31458 <= iRand && iRand <= 39322) {
                    newState = 7;
                } else if (39323 <= iRand && iRand <= 52428) {
                    newState = 8;
                } else {
                    nextState=1;
                }
                break;
            default:
                nextState=1;
                break;
        }
        if (nextState==1){
            switch (state){
                case 6:
                    if (0 <= iRand && iRand <= 13762) {
                        nextState = 6;
                    } else if (13763 <= iRand && iRand <= 17203){
                        nextState=7;
                    }else if (17204 <= iRand && iRand <= 22937){
                        nextState=8;
                    } else {
                        nextState =1;
                    }
                    break;
                case 7:
                    if (0 <= iRand && iRand <= 19660) {
                        newState = 6;
                    } else if (19661 <= iRand && iRand <= 23592) {
                        newState = 7;
                    } else if (23593 <= iRand && iRand <= 32768) {
                        newState = 8;
                    } else {
                        nextState=1;
                    }
                    break;
                case 8:
                    if (0 <= iRand && iRand <= 7864) {
                        newState = 6;
                    } else if (7865 <= iRand && iRand <= 9830) {
                        newState = 7;
                    } else if (9831 <= iRand && iRand <= 13107) {
                        newState = 8;
                    } else {
                        nextState=1;
                    }
                    break;
                default:
                    break;
            }
        }
    }


}
