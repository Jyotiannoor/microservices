package my.RestClient.Repo;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import my.RestClient.Model.ClientCustomer;

@EnableMongoRepositories
public interface ClientRepo extends MongoRepository<ClientCustomer, String> {


}
