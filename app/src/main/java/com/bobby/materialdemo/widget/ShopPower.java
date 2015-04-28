package com.bobby.materialdemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bobby.materialdemo.R;


public class ShopPower extends ImageView {
    private static Drawable PR0, PR10, PR20, PR30, PR35, PR40, PR45, PR50;
    private int power;

    public ShopPower(Context context) {
        super(context);
    }

    public ShopPower(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShopPower(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int p) {
        power = p;
        switch (p) {
            case 0: {
                if (PR0 == null)
                    PR0 = getResources().getDrawable(R.drawable.star0);
                setImageDrawable(PR0);
                break;
            }
            case 10: {
                if (PR10 == null)
                    PR10 = getResources().getDrawable(R.drawable.star10);
                setImageDrawable(PR10);
                break;
            }
            case 20: {
                if (PR20 == null)
                    PR20 = getResources().getDrawable(R.drawable.star20);
                setImageDrawable(PR20);
                break;
            }
            case 30: {
                if (PR30 == null)
                    PR30 = getResources().getDrawable(R.drawable.star30);
                setImageDrawable(PR30);
                break;
            }
            case 35: {
                if (PR35 == null)
                    PR35 = getResources().getDrawable(R.drawable.star35);
                setImageDrawable(PR35);
                break;
            }
            case 40: {
                if (PR40 == null)
                    PR40 = getResources().getDrawable(R.drawable.star40);
                setImageDrawable(PR40);
                break;
            }
            case 45: {
                if (PR45 == null)
                    PR45 = getResources().getDrawable(R.drawable.star45);
                setImageDrawable(PR45);
                break;
            }
            case 50: {
                if (PR50 == null)
                    PR50 = getResources().getDrawable(R.drawable.star50);
                setImageDrawable(PR50);
                break;
            }
            default: {
                setImageDrawable(null);
                break;
            }
        }
    }
}
