package com.csp595.beans;

import java.util.ArrayList;

import com.csp595.model.CatalogItem;
import com.csp595.model.Utils;

public class OrdersList implements java.io.Serializable {

    ArrayList<OrderBean> orders = new ArrayList();
    
    public OrdersList() {
        
    }

    public ArrayList<OrderBean> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderBean> orders) {
        this.orders = orders;
    }

    public void filter(String userID){
        orders = Utils.getOrders(userID);
    }

}
