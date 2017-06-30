package com.aiyaapp.aiya.mvc;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.aiyaapp.aiya.EffectSelectActivity;
import com.aiyaapp.aiya.R;
import com.aiyaapp.aiya.util.PermissionUtils;
import com.aiyaapp.camera.sdk.AiyaEffects;
import com.aiyaapp.camera.sdk.widget.AnimEffectTextureView;

/**
 * Created by aiya on 2017/6/21.
 */

public class EffectOnlyActivity extends EffectSelectActivity {

    private AnimEffectTextureView mEffectView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtils.askPermission(this,new String[]{Manifest.permission.CAMERA,Manifest
            .permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE},10,mRunnable);

    }

    private Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            setContentView(R.layout.activity_effect_only);
            initData();
            modelInit();
            mEffectView=(AnimEffectTextureView) findViewById(R.id.mEffectView);
        }
    };

    private void modelInit(){

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.mShutter:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mEffectView!=null){
            mEffectView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mEffectView!=null){
            mEffectView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AiyaEffects.getInstance().setEffect(null);
    }

}
