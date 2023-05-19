package my.RestClient.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.RestClient.Model.CustomerClient;
import my.RestClient.Repo.ClientRepo;

@Service
public class ClientServices {
	@Autowired
	ClientRepo clientRepo;

	public List<CustomerClient> findClientDetailByEmailid(CustomerClient objCustomerClient) {
		return clientRepo.getClientDataByEmailId(objCustomerClient.getEmail());

	}
	

}
