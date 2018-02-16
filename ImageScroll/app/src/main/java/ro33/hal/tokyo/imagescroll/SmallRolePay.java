package ro33.hal.tokyo.imagescroll;

/**
 * Created by shelleden on 2018/02/12.
 */

public class SmallRolePay {

    private int pay;
    private String role;

    public int pay(String role){

        switch (role){
            case "ハズレ":
                pay=0;
                break;
            case "リプレイ":
                pay=0;
                break;
            case "ベル":
                pay=7;
                break;
            case "強ベル":
                pay=10;
                break;
            case "スイカ":
                pay=10;
                break;
            case "強スイカ":
                pay=10;
                break;
            case "チェリー":
                pay=4;
                break;
            case "強チェリー":
                pay=4;
                break;
            case "チャンス目A":
                pay=10;
                break;
            case "チャンス目B":
                pay=10;
                break;
            case "中チェ":
                pay=3;
                break;
            default:
                break;
        }
        return pay;
    }
}
