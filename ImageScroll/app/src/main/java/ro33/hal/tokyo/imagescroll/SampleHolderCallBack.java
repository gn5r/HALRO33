package ro33.hal.tokyo.imagescroll;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;


public class SampleHolderCallBack implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = false;
    private ReelJudge reelJudge = new ReelJudge();
    Resources res;
    int nowbottom;
    int th;
    int cnt;

    Bitmap bitmap;
    int w;
    int h;

    public SampleHolderCallBack(Resources res) {
        this.res = res;
        bitmap = BitmapFactory.decodeResource(res, R.drawable.versus_left);
        w = bitmap.getWidth();
//        h = bitmap.getHeight();
        h = 1020;
        nowbottom = h;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        thread = new Thread(this);
        thread.start(); //スレッドを開始
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isAttached = false;
        thread = null; //スレッドを終了
    }

    @Override
    public void run() {
        // メインループ（無限ループ）
        // 画像 h = h
        while (true) {
            while (isAttached) {
                nowbottom -= 20;
                drawReel();
            }
        }
    }

    public void setIsAttached(boolean isAttached, String role) {
        this.isAttached = isAttached;
        reelJudge.setNow(nowbottom);
        cnt = reelJudge.Judge(role);
        Log.d("tes1", String.valueOf(nowbottom));
        Log.d("tes2", String.valueOf(cnt));
        if (cnt > 0) {
            for (; cnt > 0; cnt--) {
                if (nowbottom==0){
                    nowbottom=1020;
                }
                nowbottom -= 20;
                drawReel();
            }
        }
        Log.d("tes3", String.valueOf(nowbottom));
        alignment();
        drawReel();
    }

    public void leverOn(boolean isAttached) {
        nowbottom = nowbottom - nowbottom % 20;
        this.isAttached = isAttached;
    }

    public void drawReel() {
        Canvas canvas = holder.lockCanvas();
        // ResourceからBitmapを生成

        if (nowbottom <= 0) {
            nowbottom = 1020;
        }

        if (nowbottom <= (h / 21) * 3) {
            th = (h / 21) * 3 - nowbottom;
            Rect src = new Rect(0, 4, w, nowbottom);
            Rect dst = new Rect(0, th * 4, w * 4, (h / 21) * 3 * 4);
            canvas.drawBitmap(bitmap, src, dst, null);

            Rect sr = new Rect(0, 1020 - th, w, 1020);
            Rect ds = new Rect(0, 0, w * 4, ((h / 21) * 3 - nowbottom) * 4);
            canvas.drawBitmap(bitmap, sr, ds, null);
            holder.unlockCanvasAndPost(canvas);
        } else {

            // 描画元の矩形イメージ
            Rect src = new Rect(0, nowbottom - h * 3 / 21, w, nowbottom);
            // 描画先の矩形イメージ
            Rect dst = new Rect(0, 0, w * 4, ((h / 21) * 3) * 4);
            // 描画処理
            canvas.drawBitmap(bitmap, src, dst, null);
            holder.unlockCanvasAndPost(canvas);
        }

    }

    public void alignment() {
        if (nowbottom == 980) {
            nowbottom = 975;
        } else if (nowbottom == 920) {
            nowbottom = 927;
        } else if (nowbottom == 880) {
            nowbottom = 879;
        } else if (nowbottom == 840) {
            nowbottom = 830;
        } else if (nowbottom == 780) {
            nowbottom = 782;
        } else if (nowbottom == 740) {
            nowbottom = 732;
        } else if (nowbottom == 680) {
            nowbottom = 682;
        } else if (nowbottom == 640) {
            nowbottom = 635;
        } else if (nowbottom == 580) {
            nowbottom = 586;
        } else if (nowbottom == 540) {
            nowbottom = 537;
        } else if (nowbottom == 480) {
            nowbottom = 489;
        } else if (nowbottom == 440) {
            nowbottom = 440;
        } else if (nowbottom == 400) {
            nowbottom = 392;
        } else if (nowbottom == 340) {
            nowbottom = 343;
        } else if (nowbottom == 300) {
            nowbottom = 295;
        } else if (nowbottom == 240) {
            nowbottom = 246;
        } else if (nowbottom == 200) {
            nowbottom = 198;
        } else if (nowbottom == 140) {
            nowbottom = 149;
        } else if (nowbottom == 100) {
            nowbottom = 101;
        } else if (nowbottom == 60) {
            nowbottom = 52;
        } else {
            nowbottom = 1020;
        }
    }


}




