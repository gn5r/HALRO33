package ro33.hal.tokyo.imagescroll;

/**
 * Created by shelleden on 2018/02/12.
 */

public class CreditCalculation {

    int credit=0;

    public void maxBet(){
        credit -=3;
    }

    public void centerButton(int pay){
        credit += pay;
    }

    public int getCredit(){
        return credit;
    }
}
