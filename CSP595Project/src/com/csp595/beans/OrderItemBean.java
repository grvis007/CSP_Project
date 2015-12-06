package com.csp595.beans;

public class OrderItemBean implements java.io.Serializable {

    String id = "";
    Integer quantity;
    Double unitCost;
    String description = "";
    
    public OrderItemBean(){
        
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setFields(String description, Integer quantity,
            Double unitCost) {
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setUnitCost(unitCost);
    }
}
