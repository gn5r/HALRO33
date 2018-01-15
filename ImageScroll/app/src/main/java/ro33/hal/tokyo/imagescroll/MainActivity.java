package ro33.hal.tokyo.imagescroll;

import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private ScrollSurfaceView scrollSurfaceView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.reel) ;
        ScrollSurfaceView scrollSurfaceView = new ScrollSurfaceView(this);
        linearLayout.addView(scrollSurfaceView);
    }

    public void onClick(View view){
       // scrollSurfaceView.setIsAttached(false);
        SampleHolderCallBack sampleHolderCallBack = new SampleHolderCallBack(getResources());
        sampleHolderCallBack.setIsAttached(false);
        Toast.makeText(MainActivity.this,"STOP",Toast.LENGTH_SHORT).show();
    }
}
