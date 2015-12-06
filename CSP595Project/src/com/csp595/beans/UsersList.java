package com.csp595.beans;

import java.util.ArrayList;

import com.csp595.model.Utils;

public class UsersList implements java.io.Serializable {

    ArrayList<UserBean> accounts = new ArrayList<UserBean>();
    
    public UsersList() {
        
    }

    public ArrayList<UserBean> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<UserBean> accounts) {
        this.accounts = accounts;
    }

    public void filter(){
        accounts = Utils.getAccounts();
    }

}
