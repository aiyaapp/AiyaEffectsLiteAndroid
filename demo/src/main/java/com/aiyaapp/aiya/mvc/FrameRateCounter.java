package com.aiyaapp.aiya.mvc;

import android.util.Log;

import com.aiyaapp.camera.sdk.widget.AnimEffectTextureView;

import java.text.DecimalFormat;

/**
 * Created by aiya on 2017/7/4.
 */

public class FrameRateCounter implements AnimEffectTextureView.AnimListener {

    private String currentEffect;
    private int count = 0;
    private long startTime;
    private DecimalFormat format;
    private boolean isStart = false;
    private long lastTime;

    private int unsmoothNum = 0;
    private int unsmoothTime = 0;

    public FrameRateCounter() {
        format = new DecimalFormat("0.00");
    }

    @Override
    public void onAnimEnd(String effect) {
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        Log.e("wuwang", effect + " rate:" + format.format(count * 1000f / time));
        if (unsmoothNum > 0) {
            Log.e("wuwang", "卡顿次数及时间：" + unsmoothNum + "/" + unsmoothTime);
        }
        unsmoothNum = 0;
        unsmoothTime = 0;
        count = 0;
        isStart = false;
        lastTime = 0;
    }

    @Override
    public void onAnimStart(String effect) {
        isStart = true;
        this.currentEffect = effect;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onAnimError() {

    }

    @Override
    public void onFrame() {
        if (isStart) {
            count++;
            if (lastTime != 0) {
                long l = System.currentTimeMillis() - lastTime;
                if (l > 100) {
                    unsmoothNum++;
                    unsmoothTime += l;
                }
            }
            lastTime = System.currentTimeMillis();

        }
    }

}
