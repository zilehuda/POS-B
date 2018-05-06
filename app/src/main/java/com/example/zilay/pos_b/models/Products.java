package com.example.zilay.pos_b.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zilay on 5/1/18.
 */

public class Products implements Serializable {


    @SerializedName("name")
    private String productname;

    @SerializedName("qty")
    private String productqty;

    @SerializedName("price")
    private String price;

    @SerializedName("vendid")
    private String vendorId;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductqty() {
        return productqty;
    }

    public void setProductqty(String productqty) {
        this.productqty = productqty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
