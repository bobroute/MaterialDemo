package com.bobby.materialdemo.data;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ting on 15/4/23.
 */
public class AbstractDataSource implements DataSource {
    List<DataSourceListener> dataSourceListenerList = new ArrayList<>();
    int status = 0;
    @Override
    public void addDataChangeListener(DataSourceListener dataSourceListener) {
        this.dataSourceListenerList.add(dataSourceListener);

    }


    @Override
    public void removeDataChangeListener(DataSourceListener listener) {
        this.dataSourceListenerList.remove(listener);
    }

    @Override
    public void publishDataChange(final int dataType) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                for (DataSourceListener dataSourceListener : dataSourceListenerList) {
                    dataSourceListener.onDataChange(dataType);
                }
            }
        });

    }

    @Override
    public int status() {
        return status;
    }

    protected void changeStatus(int status) {
        if (status == this.status) {
            return;
        }
        this.status = status;

        publishDataChange(DATATYPE_STATUS);
    }
}
