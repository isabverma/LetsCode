package com.isabverma.letscode.modal;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by isabverma on 3/6/2017.
 */
@IgnoreExtraProperties
public class Category {
    private String categoryName = "";
    private ArrayList<Product> productList = new ArrayList<Product>();

    public Category() {
    }

    public Category(String categoryName, ArrayList<Product> productList) {
        this.categoryName = categoryName;
        this.productList = productList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
}
