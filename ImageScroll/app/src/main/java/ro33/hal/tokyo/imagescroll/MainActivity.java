package ro33.hal.tokyo.imagescroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private ScrollSurfaceView scrollSurfaceView;
    private SmallRole smallRole;
    String role;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.reel);
        scrollSurfaceView = new ScrollSurfaceView(this);
        linearLayout.addView(scrollSurfaceView);
        smallRole =new SmallRole();

        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
        schedule.scheduleWithFixedDelay(smallRole,0,1, TimeUnit.MILLISECONDS);

    }


    public void onClick(View view) {
        scrollSurfaceView.stopLeft(role);
    }

    public void leverOn(View view){
        scrollSurfaceView.leverOn();
        role = smallRole.getSmallRole();
    }
}
