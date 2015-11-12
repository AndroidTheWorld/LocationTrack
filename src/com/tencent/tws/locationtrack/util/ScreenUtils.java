package com.tencent.tws.locationtrack.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

/**
 * Created by microzhang on 2015/11/12 at 19:09.
 */
public class ScreenUtils {
    private static final String TAG = "ScreenUtils";

    public static Bitmap takeScreenShot(Activity act) {
        if (act == null || act.isFinishing()) {
            Log.d(TAG, "act����Ϊ��.");
            return null;
        }

        // ��ȡ��ǰ��ͼ��view
        View scrView = act.getWindow().getDecorView();
        scrView.setDrawingCacheEnabled(true);
        scrView.buildDrawingCache(true);

        // ��ȡ״̬���߶�
        Rect statuBarRect = new Rect();
        scrView.getWindowVisibleDisplayFrame(statuBarRect);
        int statusBarHeight = statuBarRect.top;
        int width = act.getWindowManager().getDefaultDisplay().getWidth();
        int height = act.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap scrBmp = null;
        try {
            // ȥ���������Ľ�ͼ
            scrBmp = Bitmap.createBitmap(scrView.getDrawingCache(), 0, statusBarHeight,
                    width, height - statusBarHeight);
        } catch (IllegalArgumentException e) {
            Log.d("", "#### ��ת��Ļ����ȥ��״̬��ʧ��");
        }
        scrView.setDrawingCacheEnabled(false);
        scrView.destroyDrawingCache();
        return scrBmp;
    }
}
