package com.bobby.materialdemo.data;

/**
 * Created by ting on 15/4/23.
 */
public interface DataSource {

    int STATUS_NONE = 0;
    int STATUS_LOADING = 1;
    int STATUS_SUCCESS = 2;
    int STATUS_FAILED = 3;

    int DATATYPE_STATUS = 1;
    int DATATYPE_LIST_READY = 10;

    void addDataChangeListener(DataSourceListener listener);
    void removeDataChangeListener(DataSourceListener listener);
    void publishDataChange(int dataType);
    int status();

    interface DataSourceListener {
        void onDataChange(int dataType);
    }

}
