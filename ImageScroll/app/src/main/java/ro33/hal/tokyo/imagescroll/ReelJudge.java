package ro33.hal.tokyo.imagescroll;

/**
 * Created by shelleden on 2018/01/22.
 */

public class ReelJudge {

    int now;



    public int Judge(){

        if((1398/21)*14 >=now && (1398/21)*10 <= now )
        {
            now = (1398/21)*10;
        }

        return now;
    }

    public void setNow(int now){

        this.now = now;
    }

}
