package ro33.hal.tokyo.imagescroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SurfaceViewTest s = new SurfaceViewTest(this);

        //SurfaceViewをセットする
        LinearLayout l = new LinearLayout(this);
        l.addView(s);
        setContentView(l);
    }
}