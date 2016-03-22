package com.ms.meizinewsapplication.features.main.iview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.RecyclerIView;
import com.ms.meizinewsapplication.features.main.adapter.AndroidDevWeekAdapter;
import com.ms.meizinewsapplication.features.main.pojo.AndroidDevWeek;

import java.util.ArrayList;

/**
 * Created by 啟成 on 2016/3/21.
 */
public class AndroidDevWeekIVew extends RecyclerIView {

    private AndroidDevWeekAdapter androidDevWeekAdapter;

    //TODO init========================================

    public void init(Context context) {
        initRecycler_list(context);
    }

    //TODO View========================================

    private void initRecycler_list(final Context context) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        androidDevWeekAdapter = new AndroidDevWeekAdapter(context, R.layout.fragment_dev_week_item);

        recycler_list.setAdapter(androidDevWeekAdapter);
    }

    //TODO Model======================================================

    public void addDatas2QuickAdapter(ArrayList<AndroidDevWeek> androidDevWeeks) {

        androidDevWeekAdapter.addDatas(androidDevWeeks);
    }

}
