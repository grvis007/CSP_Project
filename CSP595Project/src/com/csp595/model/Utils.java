
package com.csp595.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.bson.types.ObjectId;

import com.csp595.beans.OrderBean;
import com.csp595.beans.OrderItemBean;
import com.csp595.beans.ProductBean;
import com.csp595.beans.ReviewBean;
import com.csp595.beans.UserBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Utils {

    /**
     * If there is a user logged in, it verifies if this session has User and Cart objects created.
     */
    public static User retrieveUser(String userid){

        // Connect to Mongo DB
        MongoClient mongo = new MongoClient("localhost", 27017);

        // If database doesn't exists, MongoDB will create it for you
        DB db = mongo.getDB("ChicagoArtShop");

        // If the collection does not exists, MongoDB will create it for you
        DBCollection users = db.getCollection("users");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", userid);

        DBCursor cursor = users.find(searchQuery);
        if(cursor.size()==1){
            String password = (String) cursor.next().get("password");
            String type = (String) cursor.curr().get("type");
            String name = (String) cursor.curr().get("name");
            String gender = (String) cursor.curr().get("gender");
            String occupation = (String) cursor.curr().get("occupation");
            Integer age = Integer.valueOf((String) cursor.curr().get("age"));
            ShoppingCart cart = getShoppingCart((DBObject) cursor.curr().get("cart"));

            return new User(userid, password, UserType.valueOf(type), name, gender, age, occupation, cart);
        } else {
            System.out.print("Error retrieving user "+userid);
            return null;
        }
    }

    /**
     * Retrieves cart information from DB and returns a Cart object
     */
    private static ShoppingCart getShoppingCart(DBObject cartObj) {
        ShoppingCart cart = new ShoppingCart();
        if(cartObj!=null){
            BasicDBList items = (BasicDBList) cartObj;
            for (Object obj : items) {
                DBObject item = (DBObject) obj;
                String productId = (String) item.get("id");
                Integer quantity = (Integer) item.get("quantity");
                cart.setNumOrdered(productId, quantity);
            }
        }
        return cart;
    }

    public static void persistCart(User u) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection users = db.getCollection("users");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", u.getId());

        ArrayList<BasicDBObject> cartItems = new ArrayList<>();
        for(Object obj : u.getCart().getItemsOrdered()){
            ItemOrder item = (ItemOrder) obj;
            BasicDBObject itemObj = new BasicDBObject();
            itemObj.put("id", item.getItemID());
            itemObj.put("quantity", item.getNumItems());
            cartItems.add(itemObj);
        }

        BasicDBObject doc = new BasicDBObject().append("cart", cartItems);
        BasicDBObject set = new BasicDBObject("$set", doc);

        users.update(searchQuery, set);
    }


    public static void persistCart(String userId, ShoppingCart cart) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection users = db.getCollection("users");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", userId);

        ArrayList<BasicDBObject> cartItems = new ArrayList<>();
        for(Object obj : cart.getItemsOrdered()){
            ItemOrder item = (ItemOrder) obj;
            BasicDBObject itemObj = new BasicDBObject();
            itemObj.put("id", item.getItemID());
            itemObj.put("quantity", item.getNumItems());
            cartItems.add(itemObj);
        }

        BasicDBObject doc = new BasicDBObject().append("cart", cartItems);
        BasicDBObject set = new BasicDBObject("$set", doc);

        users.update(searchQuery, set);
    }


    /**
     * Returns true if today date is before the maximum date to cancel the order
     */
    public static Boolean isBeforeMaxCancelationDate(Date orderDate) {
        if(orderDate == null)
            return false;

        GregorianCalendar date = new GregorianCalendar();
        date.setTime(orderDate);
        date.add(GregorianCalendar.DAY_OF_YEAR, 14);
        int i = 0;
        while(i < 5){
            date.add(GregorianCalendar.DAY_OF_YEAR, -1);
            if(date.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY 
                    && date.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY){
                i++;
            }
        }
        GregorianCalendar today = new GregorianCalendar();
        today.setTime(new Date());
        return date.after(today);
    }

    public static void editUser(String userid, String password, UserType type, String name,
            Integer age, String gender, String occupation) {
        // Connect to Mongo DB
        MongoClient mongo = new MongoClient("localhost", 27017);

        // If database doesn't exists, MongoDB will create it for you
        DB db = mongo.getDB("ChicagoArtShop");

        // If the collection does not exists, MongoDB will create it for you
        DBCollection users = db.getCollection("users");
        System.out.println("Collection users selected successfully");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", userid);

        BasicDBObject doc = new BasicDBObject().
                append("id", userid).
                append("password", password).
                append("type", type.toString()).
                append("gender", gender).
                append("name", name).
                append("age", age.toString()).
                append("occupation", occupation);

        users.findAndModify(searchQuery, null, null, false, doc, false, true);
        mongo.close();
    }

    public static ArrayList<CatalogItem> getCatalogItems(String categoryFilter,
            String onsaleFilter, String itemIdFilter) {
        ArrayList<CatalogItem> items = new ArrayList<CatalogItem>();

        DBCursor cursor;
        BasicDBObject searchQuery = new BasicDBObject();

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection products = db.getCollection("products");

        if(categoryFilter != null && !categoryFilter.isEmpty()){
            searchQuery.put("category", categoryFilter);                    
        }
        if(onsaleFilter != null && !onsaleFilter.isEmpty()){
            searchQuery.put("onsale", onsaleFilter);                    
        }
        if(itemIdFilter != null && !itemIdFilter.isEmpty()){
            searchQuery.put("_id", new ObjectId(itemIdFilter));                    
        }

        cursor = products.find(searchQuery);
        while(cursor.hasNext()){
            DBObject productObj = cursor.next();
            String itemID = ((ObjectId) productObj.get("_id")).toString();
            String modelname = (String) productObj.get("modelname");
            String category = (String) productObj.get("category");
            String price = (String) productObj.get("price");
            String onsale = (String) productObj.get("onsale");
            String manufacturer = (String) productObj.get("manufacturer");
            String image = (String) productObj.get("image");
            String description = (String) productObj.get("description");
            String condition = (String) productObj.get("condition");

            CatalogItem cItem = new CatalogItem(itemID, modelname, category, Double.valueOf(price), Boolean.valueOf(onsale), 
                    image, description, condition);
            items.add(cItem);
        }

        mongo.close();
        return items;
    }

    public static Integer submitOrder(String userid, ShoppingCart cart) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection orders = db.getCollection("orders");
		
        BasicDBObject orderObj = new BasicDBObject();
        Random random = new Random();
        Integer confirmation = random.nextInt(1000000);
        orderObj.put("userid", userid);
        orderObj.put("date", new Date());
        orderObj.put("confirmation", confirmation);

        BasicDBList itemsList = new BasicDBList();
        for(Object obj: cart.getItemsOrdered()){
            ItemOrder item = (ItemOrder) obj;
            BasicDBObject itemObj = new BasicDBObject();
            itemObj.put("id", item.getItemID());
            itemObj.put("quantity", item.getNumItems());
            itemObj.put("unitCost", item.getUnitCost());
            itemObj.put("description", item.getShortDescription());
            itemsList.add(itemObj);
        }

        orderObj.put("items",itemsList);
        WriteResult insertResult = orders.insert(orderObj);

        if(insertResult.wasAcknowledged()){
			//If order was submited, then we clean the cart
			DBCollection users = db.getCollection("users");
            BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", userid);
			ArrayList<BasicDBObject> cartItems = new ArrayList<>();			
			BasicDBObject doc = new BasicDBObject().append("cart", cartItems);
			BasicDBObject set = new BasicDBObject("$set", doc);
			users.update(searchQuery, set);
			mongo.close();
			return confirmation;
	    }
        else {
			mongo.close();
			return null;
		}
    }

    public static void persistReview(String itemID, Integer rating,
            String reviewText, String retailerName, String retailerZip,
            String retailerCity, String retailerState, User u, Boolean delete) {

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");

        DBCollection reviews = db.getCollection("reviews");
        System.out.println("Collection reviews selected successfully");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("itemid", itemID);
        searchQuery.put("userid", u.getId());
        if(!delete){

            DBCollection products = db.getCollection("products");
            System.out.println("Collection products selected successfully");
            BasicDBObject searchProductQuery = new BasicDBObject();
            searchProductQuery.put("_id", new ObjectId(itemID));
            BasicDBObject productObj = (BasicDBObject) products.findOne(searchProductQuery);
            String productModelName = productObj.getString("modelname");
            String productCategory = productObj.getString("category");
            Double price = Double.valueOf(productObj.getString("price"));
            String onsale = productObj.getString("onsale");
            String manufacturer = productObj.getString("manufacturer");

            BasicDBObject doc = new BasicDBObject().
                    append("itemid", itemID).
                    append("productModelName", productModelName).
                    append("productCategory", productCategory).
                    append("price", price).
                    append("onsale", onsale).
                    append("manufacturer", manufacturer).
                    append("userid", u.getId()).
                    append("userAge", u.getAge()).
                    append("userGender", u.getGender()).
                    append("userOccupation", u.getOccupation()).
                    append("retailerName", retailerName).
                    append("retailerZip", retailerZip).
                    append("retailerCity", retailerCity).
                    append("retailerState", retailerState).
                    append("date", new Date()).
                    append("reviewText", reviewText).
                    append("rating", rating);

            reviews.findAndModify(searchQuery, null, null, false, doc, false, true);                 
        } else if(delete) { 
            reviews.remove(searchQuery);
        }
        mongo.close();
    }

    public static ArrayList<ReviewBean> getReviews(String itemID) {
        ArrayList<ReviewBean> reviewsList = new ArrayList<ReviewBean>();

        String retailerName = "";
        String retailerCity = "";
        String retailerZip = "";
        String retailerState = "";
        String reviewText = "";
        String reviewUserID = "";
        Integer reviewRating = 0;
        Date reviewDate = new Date();

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection reviews = db.getCollection("reviews");        
        DBCollection products = db.getCollection("products");

        //Looking for reviews for this product      
        BasicDBObject productQuery = new BasicDBObject();
        productQuery.put("_id", new ObjectId(itemID));
        BasicDBObject product = (BasicDBObject) products.findOne(productQuery);

        if(product!= null){
            BasicDBObject reviewsQuery = new BasicDBObject();
            reviewsQuery.put("itemid", itemID);
            DBCursor reviewsCursor = reviews.find(reviewsQuery);

            if(reviewsCursor.size() == 0){
                return reviewsList;
            }

            while(reviewsCursor.hasNext()){
                DBObject review = (DBObject) reviewsCursor.next();
                reviewRating = (Integer) review.get("rating");
                reviewDate = (Date) review.get("date");
                reviewText = (String) review.get("reviewText");
                retailerName = (String) review.get("retailerName");
                retailerCity = (String) review.get("retailerCity");
                retailerZip = (String) review.get("retailerZip");
                retailerState = (String) review.get("retailerState");
                reviewUserID = (String)  review.get("userid");

                BasicDBObject userQuery = new BasicDBObject();
                userQuery.put("id", reviewUserID);
                DBCollection users = db.getCollection("users");
                BasicDBObject user = (BasicDBObject) users.findOne(userQuery);
                if(user!= null){
                    String age = (String) user.get("age");
                    String occupation = (String)  user.get("occupation");
                    String gender = (String)  user.get("gender");

                    ReviewBean reviewBean = new ReviewBean();
                    reviewBean.setFields(itemID, retailerName,
                            retailerCity, retailerZip, retailerState, reviewText,
                            reviewUserID, gender, age, occupation, reviewRating, reviewDate);
                    reviewsList.add(reviewBean);                                          
                }
            }
        }
        return reviewsList;
    }

    public static ArrayList<OrderBean> getOrders(String userID) {
        ArrayList<OrderBean> ordersList = new ArrayList<OrderBean>();

        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection orders = db.getCollection("orders");    
        BasicDBObject query = new BasicDBObject();
        
        if(userID!=null && !userID.isEmpty()){
            query.put("userid", userID);
        }
        DBCursor cursor = orders.find(query);
        
        while(cursor.hasNext()){
            BasicDBObject obj = (BasicDBObject) cursor.next();
            String orderID = ((ObjectId) obj.get("_id")).toString();
            String userid = obj.getString("userid");
            Date date = obj.getDate("date");
            Integer confirmation = obj.getInt("confirmation");
            ArrayList<OrderItemBean> items = parseOrderItems((BasicDBList) obj.get("items"));            
            OrderBean order = new OrderBean();
            order.setFields(orderID, userid, date, confirmation, items);
            ordersList.add(order);
        }

        return ordersList;
    }

    private static ArrayList<OrderItemBean> parseOrderItems(BasicDBList objList) {
        ArrayList<OrderItemBean> items = new ArrayList<OrderItemBean>();

        for(Object obj : objList){
            BasicDBObject item = (BasicDBObject) obj;
            String description = item.getString("description");
            Integer quantity = item.getInt("quantity");
            Double unitCost = item.getDouble("unitCost");
            OrderItemBean itemBean = new OrderItemBean();
            itemBean.setFields(description, quantity, unitCost);
            items.add(itemBean);
        }

        return items;
    }

    public static OrderBean getOrder(String orderID) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection orders = db.getCollection("orders");    
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(orderID));
        BasicDBObject obj = (BasicDBObject) orders.findOne(query);
        String userid = obj.getString("userid");
        Date date = obj.getDate("date");
        Integer confirmation = obj.getInt("confirmation");
        ArrayList<OrderItemBean> items = parseOrderItems((BasicDBList) obj.get("items"));            
        OrderBean order = new OrderBean();
        order.setFields(orderID, userid, date, confirmation, items);
        mongo.close();
        return order;
    }

    public static void editProduct(ProductBean productBean, Boolean delete) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");
        DBCollection products = db.getCollection("products");

        String modelname = productBean.getItemName();
        String description = productBean.getLongDescription();
        String category = productBean.getCategory();
        String price = productBean.getCost()!=null? String.valueOf(productBean.getCost()) : "0.00";
        String onsale = productBean.getOnsale().toString().toUpperCase();
        String condition = productBean.getCondition();
        String image = productBean.getImage();

        Boolean fields_are_valid = modelname!=null && !modelname.isEmpty();
        if(fields_are_valid) {  
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("modelname", modelname);

            if(!delete){
                BasicDBObject doc = new BasicDBObject().
                        append("modelname", modelname).
                        append("description", description).
                        append("category", category).
                        append("price", price).
                        append("onsale", onsale).
                        append("condition", condition).
                        append("image", image);

                products.findAndModify(searchQuery, null, null, false, doc, false, true); 

            } else{
                products.remove(searchQuery);
            }
        }
    }
    
    public static void cancelOrder(String orderID){
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");

        DBCollection orders = db.getCollection("orders");
        System.out.println("Collection orders selected successfully");

        BasicDBObject orderObj = new BasicDBObject();
        orderObj.put("_id", new ObjectId(orderID));

        orders.remove(orderObj);
        mongo.close();
    }

    public static ArrayList<UserBean> getAccounts() {
        ArrayList<UserBean> accounts = new ArrayList<UserBean>();
        
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("ChicagoArtShop");

        DBCollection users = db.getCollection("users");
        DBCursor cursor = users.find();
        while(cursor.hasNext()){
            BasicDBObject obj = (BasicDBObject) cursor.next();
            
            UserBean user = new UserBean();
            user.setName(obj.getString("name"));
            user.setUserid(obj.getString("id"));
            user.setType(UserType.valueOf(obj.getString("type")));
            
            accounts.add(user);
        }
        return accounts;
    }

}
