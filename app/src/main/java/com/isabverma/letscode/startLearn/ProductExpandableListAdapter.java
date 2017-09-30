package com.isabverma.letscode.startLearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.isabverma.letscode.R;
import com.isabverma.letscode.modal.Category;
import com.isabverma.letscode.modal.Product;

import java.util.ArrayList;

/**
 * Created by isabverma on 3/6/2017.
 */

public class ProductExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Category> categoryList;
    private ArrayList<Category> originalList;

    public ProductExpandableListAdapter(Context context, ArrayList<Category> categoryList){
        this.context = context;
        this.categoryList = new ArrayList<Category>();
        this.categoryList.addAll(categoryList);
        this.originalList = new ArrayList<Category>();
        this.originalList.addAll(categoryList);
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Product> productList = categoryList.get(groupPosition).getProductList();
        return productList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Product> productList = categoryList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Category category = (Category) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.category_row, null);
        }
        TextView categoryName = (TextView) convertView.findViewById(R.id.category_row_text_view);
        categoryName.setText(category.getCategoryName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Product product = (Product) getChild(groupPosition, childPosition);
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.product_row, null);
        }
        TextView productName = (TextView) convertView.findViewById(R.id.product_row_text_view);
        productName.setText(product.getProduct_name().trim());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){
        query = query.toLowerCase();
        categoryList.clear();
        if(query.isEmpty()){
            categoryList.addAll(originalList);
        }else{
            for(Category category : originalList){
                ArrayList<Product> productList = category.getProductList();
                ArrayList<Product> newList = new ArrayList<Product>();
                for(Product product : productList){
                    if(product.getProduct_name().toLowerCase().contains(query)){
                        newList.add(product);
                    }
                }
                if(newList.size() > 0){
                    Category nCategory = new Category(category.getCategoryName(), newList);
                    categoryList.add(nCategory);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateExpendableList(ArrayList<Category> categoryList) {
        this.originalList = categoryList;
        notifyDataSetChanged();
        filterData("");
    }
}
