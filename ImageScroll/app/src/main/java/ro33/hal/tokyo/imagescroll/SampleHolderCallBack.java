package ro33.hal.tokyo.imagescroll;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * Created by shelleden on 2017/12/18.
 */

public class SampleHolderCallBack implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;
    private Resources res;

    public SampleHolderCallBack(Resources res) {
        this.res = res;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        this.holder = holder;
        thread = new Thread(this);
        thread.start(); //スレッドを開始
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO 自動生成されたメソッド・スタブ
        isAttached = false;
        thread = null; //スレッドを終了
    }

    @Override
    public void run() {
        // TODO 自動生成されたメソッド・スタブ
        // メインループ（無限ループ）
        // 画像 h = 1398   Log.w("テスト", ""+ h);
        int hh = 1398;
        while (isAttached) {

            Canvas canvas = holder.lockCanvas();

            // ResourceからBitmapを生成
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.all);

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            if (hh <= 0) {
                hh = 1398;
            }
            // 描画元の矩形イメージ
            Rect src = new Rect(0, hh - h * 3 / 21, w, hh);
            // 描画先の矩形イメージ
            Rect dst = new Rect(0, 0, w, h * 3 / 21);

            Rect centerSrc = new Rect(0, hh - h * 6 / 21, w, hh - h * 3 / 21);
            Rect centerDst = new Rect(256, 0, w + 256, h * 3 / 21);
            hh -= 32;

            // 描画処理
            canvas.drawBitmap(bitmap, centerSrc, centerDst, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}