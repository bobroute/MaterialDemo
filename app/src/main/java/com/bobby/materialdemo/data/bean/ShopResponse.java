package com.bobby.materialdemo.data.bean;

import java.util.List;

/**
 * Created by ting on 15/4/23.
 */
public class ShopResponse {
    private List<Shop> records;
    private int totaldocs;
    private int totalhits;

    public List<Shop> getRecords() {
        return records;
    }

    public void setRecords(List<Shop> records) {
        this.records = records;
    }

    public int getTotaldocs() {
        return totaldocs;
    }

    public void setTotaldocs(int totaldocs) {
        this.totaldocs = totaldocs;
    }

    public int getTotalhits() {
        return totalhits;
    }

    public void setTotalhits(int totalhits) {
        this.totalhits = totalhits;
    }
}
