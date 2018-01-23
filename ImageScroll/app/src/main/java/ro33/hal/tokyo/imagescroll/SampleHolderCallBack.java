package ro33.hal.tokyo.imagescroll;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.concurrent.TimeUnit;

/**
 * Created by shelleden on 2017/12/18.
 */

public class SampleHolderCallBack implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;
    private ReelJudge reelJudge = new ReelJudge();
    Resources res;
    int nowbottom=1398;

    public SampleHolderCallBack(Resources res) {
        this.res = res;
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
        // 画像 h = 1398   Log.w("テスト", ""+ h);

        int th;
        while (true) {
            while (isAttached) {

                Canvas canvas = holder.lockCanvas();
                // ResourceからBitmapを生成
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.all);

                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                if (nowbottom <= 0) {
                    nowbottom = 1398;
                }

                if (nowbottom <= 199) {
                    th = 199 - nowbottom;
                    Rect src = new Rect(0, 0, w, nowbottom);
                    Rect dst = new Rect(0, th * 2, w * 2, 199 * 2);
                    canvas.drawBitmap(bitmap, src, dst, null);

                    Rect sr = new Rect(0, 1398 - th, w, 1398);
                    Rect ds = new Rect(0, 0, w * 2, (199 - nowbottom) * 2);
                    canvas.drawBitmap(bitmap, sr, ds, null);
                    holder.unlockCanvasAndPost(canvas);
                } else {

                    // 描画元の矩形イメージ
                    Rect src = new Rect(0, nowbottom - h * 3 / 21, w, nowbottom);
                    // 描画先の矩形イメージ
                    Rect dst = new Rect(0, 0, w * 2, 199 * 2);
                    // 描画処理
                    canvas.drawBitmap(bitmap, src, dst, null);
                    holder.unlockCanvasAndPost(canvas);
                }
                nowbottom -= 42;
            }
        }
    }

    public void setIsAttached(boolean isAttached) {
        this.isAttached = isAttached;
        reelJudge.setNow(nowbottom);
        nowbottom = reelJudge.Judge();
    }

}




