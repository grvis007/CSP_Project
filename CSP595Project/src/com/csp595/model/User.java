package com.csp595.model;

public class User {
    
    private String id;
    
    private String password;
    
    private UserType type;

    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String occupation;
    
    private ShoppingCart cart;
    
    public User(String userid, String password, UserType type, String name, String gender, Integer age, String occupation, ShoppingCart cart) {
        this.name = name;
        this.id = userid;
        this.password = password;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.cart = cart;
    }
    
    public UserType getType() {
        return type;
    }
    public void setType(UserType type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
