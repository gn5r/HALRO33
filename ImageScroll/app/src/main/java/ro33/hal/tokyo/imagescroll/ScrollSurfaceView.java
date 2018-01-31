package ro33.hal.tokyo.imagescroll;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by shelleden on 2017/12/18.
 */

public class ScrollSurfaceView extends SurfaceView {
    private SampleHolderCallBack cb;

    boolean isAttached = false;

    public ScrollSurfaceView(Context context) {
        super(context);
        Resources res = this.getContext().getResources();
        SurfaceHolder holder = getHolder();
        cb = new SampleHolderCallBack(res);
        holder.addCallback(cb);
    }

    public void stopLeft(String role) {
        if (isAttached == true) {
            cb.setIsAttached(false,role);
            isAttached = false;
        }
    }

    public void leverOn(){
        if (isAttached == false) {
            cb.leverOn(true);
            isAttached = true;
        }
    }

}

