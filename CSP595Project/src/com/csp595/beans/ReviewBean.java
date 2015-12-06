package com.csp595.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csp595.model.Utils;

public class ReviewBean implements java.io.Serializable {

    String itemID = "";
    Integer rating;
    String reviewText = "";
    String reviewDate = "";
    String retailerName = "";
    String retailerZip = "";
    String retailerCity = "";
    String retailerState = "";
    String userid ="";
    Integer age = null;
    String gender = "";
    String occupation = "";
    
    public ReviewBean() {

    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getRetailerZip() {
        return retailerZip;
    }

    public void setRetailerZip(String retailerZip) {
        this.retailerZip = retailerZip;
    }

    public String getRetailerCity() {
        return retailerCity;
    }

    public void setRetailerCity(String retailerCity) {
        this.retailerCity = retailerCity;
    }

    public String getRetailerState() {
        return retailerState;
    }

    public void setRetailerState(String retailerState) {
        this.retailerState = retailerState;
    }
    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void submitReview(UserBean u, Boolean delete){
        Utils.persistReview(itemID, rating, reviewText, retailerName, 
                retailerZip, retailerCity, retailerState, u.getUserObj(), delete);
    }
    
    public String getTodayDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        return formatter.format(new Date());
    }
    
    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    public void setFields(String itemID, String retailerName,
            String retailerCity, String retailerZip, String retailerState,
            String reviewText, String userid, String gender,
            String age, String occupation, Integer rating,
            Date reviewDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        
        this.setItemID(itemID);
        this.setRating(rating);
        this.setReviewText(reviewText);
        if(reviewDate!=null){
            this.setReviewDate(formatter.format(reviewDate));
        }
        this.setRetailerName(retailerName);
        this.setRetailerZip(retailerZip);
        this.setRetailerCity(retailerCity);
        this.setRetailerState(retailerState);
        this.setUserid(userid);
        this.setAge(Integer.parseInt(age));
        this.setGender(gender);
        this.setOccupation(occupation);
    }
    
}
