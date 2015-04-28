package com.bobby.materialdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bobby.materialdemo.R;
import com.bobby.materialdemo.adapter.ShopAdapter;
import com.bobby.materialdemo.data.DataSource;
import com.bobby.materialdemo.data.ShopDatasource;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ting on 15/4/23.
 */
public class ShoplistFragment extends Fragment implements ShopDatasource.DataSourceListener, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @InjectView(R.id.list)
    RecyclerView shopListView;

    ShopDatasource shopDatasource;

    ShopAdapter shopAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopDatasource = new ShopDatasource();
        shopDatasource.addDataChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shoplist, container, false);
        ButterKnife.inject(this, rootView);
        refreshLayout.setOnRefreshListener(this);
        setupAdapter();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        shopListView.setLayoutManager(linearLayoutManager);
        shopAdapter = new ShopAdapter(getActivity());
        shopListView.setAdapter(shopAdapter);
        shopListView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastPos = ((LinearLayoutManager)shopListView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    shopDatasource.setLastPos(lastPos);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//		mRefreshLayout.setRefreshing(false);
//		mRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
//        mRefreshLayout.setRefreshing(true);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                shopDatasource.reload(true);
            }
        });
    }


    @Override
    public void onDataChange(int dataType) {
        switch (dataType) {
            case DataSource.DATATYPE_STATUS:
                switch (shopDatasource.status()) {
                    case DataSource.STATUS_FAILED:
                        Toast.makeText(getActivity(), shopDatasource.getErrorMessage(), Toast.LENGTH_LONG).show();
                        refreshLayout.setRefreshing(false);
                        break;
                }
                break;
            case DataSource.DATATYPE_LIST_READY:
                shopAdapter.updateItems(shopDatasource);
                break;
        }
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        shopDatasource.reload(true);
    }

}
