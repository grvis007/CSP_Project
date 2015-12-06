package com.csp595.beans;

import com.csp595.model.ShoppingCart;
import com.csp595.model.User;
import com.csp595.model.UserType;
import com.csp595.model.Utils;

public class UserBean implements java.io.Serializable {

    String userid ="";

    String password ="";
    
    String name ="";
    
    Integer age = null;
    
    String gender = "";
    
    String occupation = "";

    UserType type = UserType.CUSTOMER;

    String passwordConfirmation = "";
    
    private ShoppingCart cart;
    
    Boolean loggedin = false;
    
    public UserBean() {

    }

    public void login(){
        if(userid != null && !userid.isEmpty()) {
            userid = userid.trim();
        }
        if(password != null && !password.isEmpty()) {
            password = password.trim();
        }
        if(userid != null && password != null) {
            com.csp595.model.User user = Utils.retrieveUser(userid);
            String realpassword = user.getPassword();
            if(realpassword != null && realpassword.equals(password)) {
                type = user.getType();
                name = user.getName();
                age = user.getAge();
                gender = user.getGender();
                occupation = user.getOccupation();
                loggedin = true;
                cart = user.getCart();
            }
        }
    }

    public void logout(){
        loggedin = false;
    }
    
    /**
     * Creates/Updates user account. Return true if operation was successful
     */
    public boolean editAccount(){
        if(type == null){
            type = UserType.CUSTOMER;
        }
        if(!userid.isEmpty()) {
            userid = userid.trim();
        }
        if(!password.isEmpty()) {
            password = password.trim();
        }
        if(!userid.isEmpty() && !password.isEmpty() && passwordConfirmation.equals(password)) {
            Utils.editUser(userid, password, type, name, age, gender, occupation);
            return true;
        }
        return false;
    }
    
    //Returns the confirmationNumber
    public Integer submitOrder(){
        Integer confirmationNumber = Utils.submitOrder(userid, cart);
        if(confirmationNumber != null){
            this.setCart(new ShoppingCart());
        }
        return confirmationNumber;
    }
    
    public void persistCart(){
        Utils.persistCart(userid, cart);
    }
    
    public Boolean isLoggedIn(){
        return loggedin;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
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

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public com.csp595.model.User getUserObj() {
        com.csp595.model.User u = new com.csp595.model.User(userid, password, type, name,
                    gender, age, occupation, cart);
        return u;
    }
    
    public Boolean isManager(){
        return UserType.MANAGER.equals(this.getType());
    }
    
    public Boolean isSalesman(){
        return UserType.SALESMAN.equals(this.getType());
    }
    
    public void loadFields(){
        if(this.getUserid()!=null && !this.getUserid().isEmpty()){
            User user = Utils.retrieveUser(userid);
            password = user.getPassword();
            passwordConfirmation = user.getPassword();
            type = user.getType();
            name = user.getName();
            age = user.getAge();
            gender = user.getGender();
            occupation = user.getOccupation();
            loggedin = true;
            cart = user.getCart();
        }
    }
}
