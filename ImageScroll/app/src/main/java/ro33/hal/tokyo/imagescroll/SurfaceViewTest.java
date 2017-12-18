package ro33.hal.tokyo.imagescroll;

//tokyo.hal.ro33

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by shelleden on 2017/12/18.
 */

public class SurfaceViewTest extends SurfaceView {
    private SampleHolderCallBack cb;
    public SurfaceViewTest(Context context) {
        super(context);
        Resources res = this.getContext().getResources();
        SurfaceHolder holder = getHolder();
        cb = new SampleHolderCallBack(res);
        holder.addCallback(cb);
    }
}
