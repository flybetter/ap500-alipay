package org.alipay.dao.cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MongodbDao {
	
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
	
	private DBCollection collection;
	
	public MongodbDao(MongoTemplate mongoTemplate) {
		collection= mongoTemplate.getCollection("order");
	}
	
	public String insertOrder(Map<String, Object> paramMap) {
		 paramMap.put("orderNo","autophone"+sdf1.format(new Date())+(int)(Math.random()*100));
        BasicDBObject document=new BasicDBObject(paramMap); 
        document=insert_id(document);
        collection.save(document);
        return  document.get("orderNo").toString();
	}
	
	
	public boolean updateOrder(Integer _id,Map<String, Object> paramMap) {
		BasicDBObject query=new BasicDBObject().append("_id", _id);
		BasicDBObject update= new BasicDBObject().append("$set", new BasicDBObject(paramMap));
		WriteResult WriteResult=collection.update(query, update);
		return WriteResult.wasAcknowledged();
	}
	
	
	public boolean updateOrder(String orderNo,Map<String, Object> paramMap) {
		BasicDBObject query=new BasicDBObject().append("orderNo", orderNo);
		BasicDBObject update= new BasicDBObject().append("$set", new BasicDBObject(paramMap));
		WriteResult WriteResult=collection.update(query, update);
		return WriteResult.wasAcknowledged();
	}
	
	
	public DBObject queryById(Integer id) {
		BasicDBObject query=new BasicDBObject();
		query.put("_id", id);
		DBCursor cursor=collection.find(query).limit(1);
		DBObject obj =null;
		 while(cursor.hasNext()){  
			 obj=cursor.next();
        }  
		return obj;
	}
	
	
	public DBObject queryByOrderNo(String orderNo) {
		BasicDBObject query=new BasicDBObject();
		query.put("orderNo", orderNo);
		DBCursor cursor=collection.find(query).limit(1);
		DBObject obj =null;
		 while(cursor.hasNext()){  
			 obj=cursor.next();
        }  
		return obj;
	}
	
	public BasicDBObject insert_id(BasicDBObject object){
		DBCursor cursor=collection.find().sort(new BasicDBObject("_id",-1)).limit(1);
		if(cursor.hasNext()){  
			object.put("_id", Integer.parseInt(cursor.next().get("_id").toString())+1);  
		  }else{  
		    object.put("_id", 1);  
		  }  
		return object;  
	}
	
	
	

}
