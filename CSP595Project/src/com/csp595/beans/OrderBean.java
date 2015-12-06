package com.csp595.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csp595.model.Utils;

public class OrderBean implements java.io.Serializable {

    String date = "";
    String orderID = "";
    String userid = "";
    Integer confirmation;
    Date dateObj;
    ArrayList<OrderItemBean> items = new ArrayList<OrderItemBean>();    
    
    public OrderBean() {
        
    }
    
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Integer confirmation) {
        this.confirmation = confirmation;
    }

    public ArrayList<OrderItemBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItemBean> items) {
        this.items = items;
    }
    
    public Date getDateObj() {
        return dateObj;
    }

    public void setDateObj(Date dateObj) {
        this.dateObj = dateObj;
    }

    public void setFields(String orderID, String userid, Date dateObj2, Integer confirmation,
            ArrayList<OrderItemBean> items) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        this.setOrderID(orderID);
        this.setUserid(userid);
        this.setDateObj(dateObj2);
        if(dateObj2!=null)
            this.setDate(formatter.format(dateObj2));
        this.setConfirmation(confirmation);
        this.setItems(items);
        
    }
    
    public void loadFields(){
        OrderBean ob = Utils.getOrder(this.getOrderID());
        this.setFields(ob.getOrderID(), ob.getUserid(), ob.getDateObj(), ob.getConfirmation(), ob.getItems());
    }
    
    public Boolean isBeforeMaxCancelationDate(){
        return Utils.isBeforeMaxCancelationDate(this.getDateObj());
    }
    
    public void cancelOrder(){
        Utils.cancelOrder(this.getOrderID());
    }
}
