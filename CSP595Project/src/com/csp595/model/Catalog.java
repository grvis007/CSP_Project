package com.csp595.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Catalog {
    public static CatalogItem getItem(String itemID) {
    CatalogItem item;
    if (itemID == null) {
      return(null);
    }
    // Connect to Mongo DB
    MongoClient mongo = new MongoClient("localhost", 27017);
                
    // If database doesn't exists, MongoDB will create it for you
    DB db = mongo.getDB("ChicagoArtShop");
    
    // If the collection does not exists, MongoDB will create it for you
    DBCollection products = db.getCollection("products");
    
    BasicDBObject searchQuery = new BasicDBObject();
    searchQuery.put("_id", new ObjectId(itemID));
    
    DBObject productObj = products.findOne(searchQuery);
    if(productObj != null) {
        String modelname = (String) productObj.get("modelname");
        String category = (String) productObj.get("category");
        String price = (String) productObj.get("price");
        String onsale = (String) productObj.get("onsale");
        String image = (String) productObj.get("image");
        String description = (String) productObj.get("description");
        String condition = (String) productObj.get("ocndition");
        
        CatalogItem cItem = new CatalogItem(itemID, modelname, category, Double.valueOf(price), Boolean.valueOf(onsale), image, description, condition);
        mongo.close();
        return cItem;
    } else {
        System.out.print("Error retrieving item "+itemID);
        mongo.close();
        return null;
    } 
  }
}
               
