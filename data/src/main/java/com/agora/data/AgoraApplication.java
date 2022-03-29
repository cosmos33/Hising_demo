package com.agora.data;

import androidx.multidex.MultiDexApplication;

import com.agora.data.sync.SyncManager;

public class AgoraApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        SyncManager.Instance().init(this);
    }
}
