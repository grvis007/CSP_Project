package com.csp595.beans;

import java.util.ArrayList;

import com.csp595.model.CatalogItem;
import com.csp595.model.Utils;

public class ProductList implements java.io.Serializable {

    ArrayList<CatalogItem> items = new ArrayList();

    public ProductList() {

    }

    public ArrayList<CatalogItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CatalogItem> items) {
        this.items = items;
    }
    
    public void filter(String category, String onsale, String itemID){
        items = Utils.getCatalogItems(category, onsale, itemID);
    }

    public void filter(){
        items = Utils.getCatalogItems(null,null,null);
    }
}
