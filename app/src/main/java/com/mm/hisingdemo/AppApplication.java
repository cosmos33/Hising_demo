package com.mm.hisingdemo;

import android.app.Application;

import com.mm.hising.client.IHisingClient;
import com.mm.hising.client.bean.RoomUser;
import com.mm.hising.client.impl.HisingClient;
import com.mm.hisingdemo.clienthandler.HisingRtcHandler;
import com.mm.hisingdemo.clienthandler.HisingRtmHandler;
import com.mm.hisingdemo.clienthandler.HisingSongHandler;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AppApplication extends Application {
    private static final String APPID = "11111";
    @Override
    public void onCreate() {
        super.onCreate();
        HisingClient.INSTANCE.init(this, APPID, new IHisingClient.RoomCallback() {
            @Override
            public void onCloseGameClick() {

            }

            @NotNull
            @Override
            public List<RoomUser> grade(@NotNull List<RoomUser> users) {
                return users;
            }

            @Override
            public void currentRoundFinish(@NotNull RoomUser[] users) {

            }
        })
        .setSongHandler(new HisingSongHandler())
        .setRtcHandler(new HisingRtcHandler())
        .setRtmHandler(new HisingRtmHandler());
    }
}