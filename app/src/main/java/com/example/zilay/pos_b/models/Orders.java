package com.example.zilay.pos_b.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zilay on 5/6/18.
 */

public class Orders {

    @SerializedName("productId")
    private String pid;

    @SerializedName("qt")
    private int qty;

    @SerializedName("total")
    private int total;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
