package com.mm.hisingdemo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.fanyiran.utils.ToastUtils;
import com.immomo.svgaplayer.SVGAAnimListenerAdapter;
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
            public void showRobButton(boolean show, IHisingClient.OnRobAnimFinishCallback callback) {
                mDataBinding.btnRobCountDown.setVisibility(show? View.VISIBLE:View.GONE);
                if (show) {
                    robCountdownAnim(callback);
                }
            }

            @Override
            public void showScoreButton(boolean show) {
                mDataBinding.btnLight.setVisibility(show? View.VISIBLE:View.GONE);
                mDataBinding.btnUnLight.setVisibility(show? View.VISIBLE:View.GONE);
            }
        })).commit();
    }

    private void robCountdownAnim(IHisingClient.OnRobAnimFinishCallback callback) {
        mDataBinding.btnRobCountDown.startSVGAAnimWithListener("rob_count_1.svga",1,new SVGAAnimListenerAdapter(){
            @Override
            public void onFinished() {
                mDataBinding.btnRob.setVisibility(View.VISIBLE);
                robStartAnim(callback);
            }
        });
    }

    private void robStartAnim(IHisingClient.OnRobAnimFinishCallback callback) {
        mDataBinding.btnRob.startSVGAAnimWithListener("rob_count_2.svga",1,new SVGAAnimListenerAdapter(){
            @Override
            public void onFinished() {
                mDataBinding.btnRob.setVisibility(View.GONE);
                mDataBinding.btnRobCountDown.setVisibility(View.GONE);
                if (callback != null) {
                    callback.onRobAnimFinish();
                }
            }
        });
    }


    @Override
    protected void iniListener() {
        mDataBinding.flRob.setOnClickListener(this);
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
            case R.id.flRob:
                robSing();
                break;
        }
    }

    private void robSing() {
        if (mDataBinding.btnRob.getVisibility() == View.VISIBLE) {
            HisingClient.INSTANCE.robSing(success -> runOnUiThread(() -> {
                ToastUtils.showText(success?"抢唱成功":"抢唱失败");
            }));
        }
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
