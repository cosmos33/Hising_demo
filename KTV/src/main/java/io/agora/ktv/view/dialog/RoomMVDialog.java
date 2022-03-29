package io.agora.ktv.view.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.agora.data.model.AgoraRoom;
import com.agora.data.provider.AgoraObject;
import com.agora.data.sync.AgoraException;
import com.agora.data.sync.SyncManager;

import java.util.ArrayList;
import java.util.List;

import com.mm.hising.baseutil.base.DataBindBaseDialog;
import com.mm.hising.baseutil.base.OnItemClickListener;
import com.mm.hising.baseutil.util.ToastUtile;
import io.agora.ktv.R;
import io.agora.ktv.adapter.MVAdapter;
import io.agora.ktv.databinding.KtvDialogMvBinding;
import io.agora.ktv.manager.RoomManager;
import io.agora.ktv.widget.SpaceItemDecoration;

/**
 * 房间MV菜单
 *
 * @author chenhengfei@agora.io
 */
public class RoomMVDialog extends DataBindBaseDialog<KtvDialogMvBinding> implements OnItemClickListener<MVAdapter.MVModel> {
    private static final String TAG = RoomMVDialog.class.getSimpleName();

    private static final String TAG_MV_INDEX = "mvIndex";

    private int index = 0;
    private MVAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Window win = getDialog().getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.Dialog_Bottom);
    }

    @Override
    public void iniBundle(@NonNull Bundle bundle) {
        index = bundle.getInt(TAG_MV_INDEX);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ktv_dialog_mv;
    }

    @Override
    public void iniView() {
        List<MVAdapter.MVModel> list = new ArrayList<>();
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background1));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background2));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background3));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background4));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background5));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background6));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background7));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background8));
        list.add(new MVAdapter.MVModel(R.mipmap.ktv_music_background9));

        mAdapter = new MVAdapter(list, this);
        mDataBinding.rvList.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        mDataBinding.rvList.setAdapter(mAdapter);
        mDataBinding.rvList.addItemDecoration(new SpaceItemDecoration(requireContext()));
    }

    @Override
    public void iniListener() {
    }

    @Override
    public void iniData() {
        mAdapter.setSelectIndex(index);
    }

    public void show(@NonNull FragmentManager manager, int index) {
        Bundle mBundle = new Bundle();
        mBundle.putInt(TAG_MV_INDEX, index);
        setArguments(mBundle);
        super.show(manager, TAG);
    }

    @Override
    public void onItemClick(@NonNull MVAdapter.MVModel data, View view, int position, long id) {
        AgoraRoom mRoom = RoomManager.Instance(requireContext()).getRoom();
        if (mRoom == null) {
            dismiss();
            return;
        }

        mAdapter.setSelectIndex(position);
        SyncManager.Instance()
                .getRoom(mRoom.getId())
                .update(AgoraRoom.COLUMN_MV, String.valueOf(position + 1), new SyncManager.DataItemCallback() {
                    @Override
                    public void onSuccess(AgoraObject result) {

                    }

                    @Override
                    public void onFail(AgoraException exception) {
                        ToastUtile.toastShort(requireContext(), exception.getMessage());
                    }
                });
    }

    @Override
    public void onItemClick(View view, int position, long id) {

    }
}
