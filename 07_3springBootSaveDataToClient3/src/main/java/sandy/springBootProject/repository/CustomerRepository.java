package sandy.springBootProject.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import sandy.springBootProject.model.CustomerDetails;

public interface CustomerRepository extends MongoRepository<CustomerDetails, String> {

	List<CustomerDetails> findByEmail(String email);

	List<CustomerDetails> findByName(String name);

	@Query("$and:[{name:?0},{rollno:{$ne:0}}]")
	List<CustomerDetails> findDetailByEmailNameAndRollno(String name,int rollno);



	
}
