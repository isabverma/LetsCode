package com.isabverma.letscode.modal;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by isabverma on 3/6/2017.
 */
@IgnoreExtraProperties
public class Product {
    private String product_name = "";

    public Product() {
    }

    public Product(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
