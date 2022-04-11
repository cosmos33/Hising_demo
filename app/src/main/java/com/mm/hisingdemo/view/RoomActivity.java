package com.mm.hisingdemo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.fanyiran.utils.ToastUtils;
import com.mm.hising.client.IHisingClient;
import com.mm.hising.client.bean.RoomInfo;
import com.mm.hising.client.bean.RoomUser;
import com.mm.hising.client.impl.HisingClient;
import com.mm.hisingdemo.base.DataBindBaseActivity;

import io.agora.hisingdemo.R;
import io.agora.hisingdemo.databinding.ActivityRoomBinding;

public class RoomActivity extends DataBindBaseActivity<ActivityRoomBinding> implements View.OnClickListener {
    private static final String TAG_ROOM = "room";
    private static final String TAG_USER = "user";

    public static Intent newIntent(Context context, RoomInfo mRoom, RoomUser roomUser) {
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra(TAG_ROOM, mRoom);
        intent.putExtra(TAG_USER, roomUser);
        return intent;
    }

    @Override
    protected void iniBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room;
    }

    @Override
    protected void iniView() {
        RoomInfo mRoom = (RoomInfo) getIntent().getExtras().getSerializable(TAG_ROOM);
        RoomUser roomUser = (RoomUser) getIntent().getExtras().getSerializable(TAG_USER);
        HisingClient.INSTANCE.roomInfo(mRoom,roomUser,6);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, HisingClient.INSTANCE.getHisingFragment(new IHisingClient.ShowBtnCallBack() {
            @Override
            public void showRobButton(boolean show) {
                mDataBinding.btnRob.setVisibility(show? View.VISIBLE:View.GONE);
            }

            @Override
            public void showScoreButton(boolean show) {
                mDataBinding.btnLight.setVisibility(show? View.VISIBLE:View.GONE);
                mDataBinding.btnUnLight.setVisibility(show? View.VISIBLE:View.GONE);
            }
        })).commit();
    }

    @Override
    protected void iniListener() {
        mDataBinding.btnRob.setOnClickListener(this);
        mDataBinding.btnLight.setOnClickListener(this);
        mDataBinding.btnUnLight.setOnClickListener(this);
    }

    @Override
    protected void iniData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUnLight:
                unLight();
                break;
            case R.id.btnLight:
                light();
                break;
            case R.id.btnRob:
                robSing();
                break;
        }
    }

    private void robSing() {
        HisingClient.INSTANCE.robSing(success -> runOnUiThread(() -> {
            ToastUtils.showText(success?"抢唱成功":"抢唱失败");
        }));
    }

    private void light() {
        HisingClient.INSTANCE.light(success -> runOnUiThread(() -> {
            ToastUtils.showText(success?"爆灯成功":"爆灯失败");
        }));
    }

    private void unLight() {
        HisingClient.INSTANCE.unLight(success -> runOnUiThread(() -> {
            ToastUtils.showText(success?"灭灯成功":"灭灯失败");
        }));
    }
}
