package ro33.hal.tokyo.imagescroll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class ScrollSurfaceView extends SurfaceView {
    private SampleHolderCallBack cb;

    boolean isAttached = false;

    public ScrollSurfaceView(Context context,SetPlace setPlace) {
        super(context);
        Resources res = this.getContext().getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.versus_left);
        byte place = 1;
        SurfaceHolder holder = getHolder();
        cb = new SampleHolderCallBack(res,bitmap,place,setPlace);
        holder.addCallback(cb);
    }

    public void stop(String role) {
        if (isAttached == true) {
            cb.setisAttached(false,role);
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

