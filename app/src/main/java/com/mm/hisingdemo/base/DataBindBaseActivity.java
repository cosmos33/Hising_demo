package com.mm.hisingdemo.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class DataBindBaseActivity<V extends ViewDataBinding> extends BaseActivity {

    protected V mDataBinding;

    @Override
    protected void setCusContentView() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }
}