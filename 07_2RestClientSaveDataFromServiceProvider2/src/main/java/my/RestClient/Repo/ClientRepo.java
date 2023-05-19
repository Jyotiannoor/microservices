package my.RestClient.Repo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import my.RestClient.Model.CustomerClient;

@EnableMongoRepositories
public interface ClientRepo extends MongoRepository<CustomerClient, String> {

	List<CustomerClient> getClientDataByEmailId(String email);


}
