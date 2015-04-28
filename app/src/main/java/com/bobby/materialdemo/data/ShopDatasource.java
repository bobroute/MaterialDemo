package com.bobby.materialdemo.data;

import android.os.Handler;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bobby.materialdemo.data.bean.Shop;
import com.bobby.materialdemo.data.bean.ShopResponse;
import com.bobby.materialdemo.network.GsonRequest;
import com.bobby.materialdemo.network.RequestManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ting on 15/4/23.
 */
public class ShopDatasource extends AbstractDataSource{
    public static final String URL = "http://192.168.5.149:4053/search/shop?query=term(cityid,1)&sort=desc(dpscore)";

    GsonRequest<ShopResponse> shopRequest;

    List<Shop> shopList = new ArrayList<>();
    int startPos = 0;
    int nextStartPos = 0;

    int lastPos = -1;

    VolleyError error;


    public void reload(boolean clear) {
        if (clear) {
            lastPos = -1;
            shopList.clear();
        }
//        requestShops(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mockShops();
                publishDataChange(DATATYPE_LIST_READY);
            }
        }, 1000);
    }



    public void requestShops(int startPos) {
        if (shopRequest != null) {
            RequestManager.getRequestQueue().cancelAll(shopRequest);
            shopRequest = null;
        }
        String url = URL + "&limit=" + startPos + ",25&fl=*";
        shopRequest = new GsonRequest<>(url, ShopResponse.class, shopResponseListener(), errorListener());
        RequestManager.addRequest(shopRequest, shopRequest);
    }

    Response.Listener<ShopResponse> shopResponseListener() {
        return new Response.Listener<ShopResponse>() {
            @Override
            public void onResponse(ShopResponse shopResponse) {
                shopList.addAll(shopResponse.getRecords());
                startPos = nextStartPos;
                nextStartPos = startPos + shopResponse.getRecords().size();
                publishDataChange(DATATYPE_LIST_READY);
            }
        };
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShopDatasource.this.error = error;
                changeStatus(STATUS_FAILED);
            }
        };
    }

    public List<Shop> getShopList() {
        return shopList;
    }


    public String getErrorMessage() {
         return error == null ? "" : error.getMessage();
    }

    public int getLastPos() {
        return lastPos;
    }

    public void setLastPos(int lastPos) {
        this.lastPos = lastPos;
    }

    private void mockShops() {
        for (int i = 0; i< 25; i++) {
            shopList.add(mockShop());
            startPos =0;
            nextStartPos = 25;
        }

    }

    private Shop mockShop() {
        Shop shop = new Shop();
        shop.setAddress("安化路492号");
        shop.setShopname("大众点评网");
        shop.setBranchname("上海总部");
        shop.setShoppower(45);
        shop.setDefaultpic("http://si1.s1.dpfile.com/s/c/app/main/index-header/i/sprite-ie6.19e5286e1d5e662d5de1c2116d34d5e4.png");
        return shop;
    }
}
