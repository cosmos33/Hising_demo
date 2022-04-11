package com.mm.hisingdemo.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fanyiran.utils.ToastUtils;
import com.fanyiran.utils.base.BaseActivity;
import com.mm.hising.client.IHisingClient;
import com.mm.hising.client.bean.Role;
import com.mm.hising.client.bean.RoomUser;
import com.mm.hising.client.impl.HisingClient;
import com.mm.hising.client.bean.RoomInfo;

import org.jetbrains.annotations.NotNull;

import io.agora.hisingdemo.R;
import io.agora.hisingdemo.databinding.ActivityCreateRoomBinding;

public class CreateRoomActivity extends BaseActivity implements View.OnClickListener {
    private ActivityCreateRoomBinding mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setAsyncCreateView(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_room;
    }

    @Override
    protected void onSetContentViewEnd() {
        mDataBinding.btCreate.setOnClickListener(this);
        mDataBinding.btJoinRoom.setOnClickListener(this);
    }

    @Override
    protected View inflateView() {
        mDataBinding = ActivityCreateRoomBinding.inflate(getLayoutInflater());
        return mDataBinding.root;
    }

    @Override
    public void onClick(View v) {
        if (v == mDataBinding.btCreate) {
            createRoom();
        } else if (v == mDataBinding.btJoinRoom) {
            joinRoom();
        }
    }

    private void joinRoom() {
        String roomid = mDataBinding.etRoomId.getText().toString();
        RoomUser roomUser = new RoomUser("22","我是观众","https://alifei05.cfp.cn/creative/vcg/800/new/VCG41N1224883701.jpg", Role.ROLE_LISTENER);
        RoomInfo room = new RoomInfo();
        room.setId(roomid);
        startActivity(RoomActivity.newIntent(CreateRoomActivity.this, room,roomUser));
    }

    private void createRoom() {
        RoomUser roomUser = new RoomUser("11","我是房主","https://alifei05.cfp.cn/creative/vcg/800/new/VCG41N1224883700.jpg", Role.ROLE_OWNER);
        HisingClient.INSTANCE.createRoom(roomUser, new IHisingClient.RoomCreateCallBack() {
            @Override
            public void onRoomCreated(@NotNull String roomId, boolean success) {
                if (success) {
                    RoomInfo room = new RoomInfo();
                    room.setId(roomId);
                    startActivity(RoomActivity.newIntent(CreateRoomActivity.this, room, roomUser));
                    ToastUtils.showText("roomid:"+roomId);
                } else {
                    ToastUtils.showText("创建room失败");
                }
            }
        });
    }
}
