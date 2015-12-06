package com.csp595.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrderConfirmationBean {
    Integer confirmationNumber;
    
    public String getDeliveryDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        
        GregorianCalendar deliveryDate = new GregorianCalendar();
        deliveryDate.setTime(new Date());
        deliveryDate.add(GregorianCalendar.DAY_OF_YEAR, 14);
        return formatter.format(deliveryDate.getTime());
    }

    public Integer getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(Integer confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}

