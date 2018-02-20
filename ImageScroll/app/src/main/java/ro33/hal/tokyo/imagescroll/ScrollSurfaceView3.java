package ro33.hal.tokyo.imagescroll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class ScrollSurfaceView3 extends SurfaceView {
    private SampleHolderCallBack cb;

    boolean isAttached = false;

    public ScrollSurfaceView3(Context context,SetPlace setPlace) {
        super(context);
        Resources res = this.getContext().getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.versus_right);
        byte place = 3;
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

