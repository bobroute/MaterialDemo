package com.bobby.materialdemo.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.bobby.materialdemo.R;

/**
 * Created by biyuan.mo on 15/4/16.
 */
public class NetworkImageView extends com.android.volley.toolbox.NetworkImageView {
    private static int errorPlaceholderId = R.drawable.placeholder_error;
    private static int defaultPlaceholderId = R.drawable.placeholder_default;

    public NetworkImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDefaultImageResId(defaultPlaceholderId);
        setErrorImageResId(errorPlaceholderId);
    }
}
