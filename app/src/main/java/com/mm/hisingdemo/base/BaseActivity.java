package com.mm.hisingdemo.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            iniBundle(bundle);
        }

        setCusContentView();

        iniView();
        iniListener();
        iniData();
    }

    protected void setCusContentView() {
        setContentView(getLayoutId());
    }

    protected abstract void iniBundle(@NonNull Bundle bundle);

    protected abstract int getLayoutId();

    protected abstract void iniView();

    protected abstract void iniListener();

    protected abstract void iniData();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (this instanceof EasyPermissions.PermissionCallbacks) {
            EasyPermissions
                    .onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }
    }
}
