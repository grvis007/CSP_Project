package com.csp595.beans;

import java.util.HashMap;

import com.csp595.model.Catalog;
import com.csp595.model.CatalogItem;
import com.csp595.model.Utils;

public class ProductBean implements java.io.Serializable {

    private String itemID = "";
    private String itemName = "";
    private String longDescription = "";
    private Double cost;
    private Boolean onsale = false;
    private String category = "";
    private String condition = "";
    private String image = "";
    
    private HashMap<String, String> onsaleMap = new HashMap<String, String>();
    private HashMap<String, String> conditionMap = new HashMap<String, String>();
    private HashMap<String, String> categoryMap = new HashMap<String, String>();
    
    public ProductBean() {
        
    }
    
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
	
	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
	
	public Boolean getOnsale() {
        return onsale;
    }
	
	public void setOnsale(Boolean onsale) {
        this.onsale = onsale;
    }
    
    public void recoverAllFields(){
		if(itemID!=null && !itemID.isEmpty()){
        CatalogItem item = Catalog.getItem(itemID);
        this.setItemID(item.getItemID());
        this.setItemName(item.getShortDescription());
        this.setLongDescription(item.getLongDescription());
        this.setCost(item.getCost());;
        this.setOnsale(item.getOnsale());
        this.setCategory(item.getCategory());
        this.setCondition(item.getCondition());
        this.setImage(item.getImage());
        
        categoryMap.put((String) this.getCategory(), "selected");
        onsaleMap.put(this.getOnsale().toString().toUpperCase(), "checked");
        conditionMap.put(this.getCondition(), "checked");;
		}
    }
    
    public String getChecked(String field, String value) {
        String result = "";
        if(field.equals("category")){
            if(categoryMap.get(value) != null)
                return categoryMap.get(value);
        }
        else if(field.equals("onsale")){
            if(onsaleMap.get(value) != null)
                return onsaleMap.get(value);
        }
        else if(field.equals("condition")){
            if(conditionMap.get(value) != null)
                return conditionMap.get(value);
        }
            
        return result;
    }
    
    public void editProduct(Boolean delete){
        Utils.editProduct(this, delete);
    }
}
