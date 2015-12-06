package com.csp595.model;

import java.io.Serializable;

public class CatalogItem implements Serializable {
    
  private String itemID;
  private String shortDescription;
  private String longDescription;
  private String image;
  private double cost;
  private Boolean onsale = false;
  private String category = "";
  private String condition = "";

  public CatalogItem(String itemID, String modelname, String category,
        Double price, Boolean onsale, String image, String description, String condition ) {
        
      setItemID(itemID);
      setShortDescription(modelname);
      setLongDescription(description);
      setCost(price);
      setImage(image);
      setOnsale(onsale);
      setCategory(category);
      setCondition(condition);
      setCondition(condition);
  }

public String getItemID() {
    return(itemID);
  }

  protected void setItemID(String itemID) {
    this.itemID = itemID;
  }

  public String getShortDescription() {
    return(shortDescription);
  }

  protected void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return(longDescription);
  }

  protected void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public double getCost() {
    return(cost);
  }

  protected void setCost(double cost) {
    this.cost = cost;
  }

public String getImage() {
    return image;
}

public void setImage(String image) {
    this.image = image;
}

public Boolean getOnsale() {
    return onsale;
}

public void setOnsale(Boolean onsale) {
    this.onsale = onsale;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}

public String getCondition() {
    return condition;
}

public void setCondition(String condition) {
    this.condition = condition;
}
}
