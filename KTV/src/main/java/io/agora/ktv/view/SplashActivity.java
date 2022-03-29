package io.agora.ktv.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mm.hising.baseutil.base.DataBindBaseActivity;

import io.agora.ktv.R;
import io.agora.ktv.databinding.KtvActivitySplashBinding;


/**
 * 闪屏界面
 *
 * @author chenhengfei@agora.io
 */
public class SplashActivity extends DataBindBaseActivity<KtvActivitySplashBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0)
            finish();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void iniBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.ktv_activity_splash;
    }

    @Override
    protected void iniView() {

    }

    @Override
    protected void iniListener() {

    }

    @Override
    protected void iniData() {
        startActivity(new Intent(this, RoomListActivity.class));
        finish();
    }
}
