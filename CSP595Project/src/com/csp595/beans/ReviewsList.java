package com.csp595.beans;

import java.util.ArrayList;

import com.csp595.model.Utils;

public class ReviewsList implements java.io.Serializable {

    ArrayList<ReviewBean> reviews = new ArrayList();

    public ReviewsList() {

    }

    public ArrayList<ReviewBean> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewBean> reviews) {
        this.reviews = reviews;
    }

    public void filter(String itemID){
        reviews = Utils.getReviews(itemID);
    }
}
