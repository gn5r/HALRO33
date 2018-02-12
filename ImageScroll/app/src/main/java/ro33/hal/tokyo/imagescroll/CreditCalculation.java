package ro33.hal.tokyo.imagescroll;

/**
 * Created by shelleden on 2018/02/12.
 */

public class CreditCalculation {

    int credit=0;

    public void maxBet(){
        credit -=3;
    }

    public int getCredit(){
        return credit;
    }
}
