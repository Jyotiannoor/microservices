package sandy.springBootProject.service;


import  static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import sandy.springBootProject.model.CustomerDetails;
@Service
public class UpdateServiceMongo {
 @Autowired
 MongoTemplate mongoTemplate;
	public void updateAddress(String name, String address) {
		Query query=new Query().addCriteria(where("name").is(name));
		Update update = new Update();
		update.set("address", address);
		mongoTemplate.findAndModify(query, update, CustomerDetails.class);
		
	}


}
