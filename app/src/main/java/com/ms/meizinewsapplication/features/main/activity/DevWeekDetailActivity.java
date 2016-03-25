package com.ms.meizinewsapplication.features.main.activity;

import android.os.Bundle;

import com.ms.meizinewsapplication.features.base.activity.BaseActivityPresenterImpl;
import com.ms.meizinewsapplication.features.main.iview.DevWeekDetailIVew;
import com.ms.meizinewsapplication.features.main.model.DevWeekDetailModel;

import org.loader.model.OnModelListener;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekDetailActivity extends BaseActivityPresenterImpl<DevWeekDetailIVew> {
    private DevWeekDetailModel devWeekDetailModel;
    private String path;
    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(DevWeekDetailActivity.this);
        initDevWeekDetailModel();
    }
    //TODO Model====================================================

    private void initDevWeekDetailModel()
    {
        path = getIntent().getStringExtra("path");
        devWeekDetailModel = new DevWeekDetailModel();
        devWeekModelLoad();
    }

    private void devWeekModelLoad() {
        devWeekDetailModel.loadWeb(DevWeekDetailActivity.this, listenerDevWeek, path);
    }

    //TODO Listener====================
    OnModelListener<String> listenerDevWeek = new OnModelListener<String>() {
        @Override
        public void onSuccess(String s) {
            mView.showDetail(s);
        }

        @Override
        public void onError(String err) {

        }

        @Override
        public void onCompleted() {

        }
    };
}
