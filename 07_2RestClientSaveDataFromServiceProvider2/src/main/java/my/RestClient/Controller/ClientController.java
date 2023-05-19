package my.RestClient.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.RestClient.ClientService.ClientServices;
import my.RestClient.Model.CustomerClient;
import my.RestClient.Repo.ClientRepo;


@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	ClientRepo objClientRepo;
	@Autowired
	ClientServices clientServices; 
//------------------------------------	
	@PostMapping(value="/savedata",produces= {MediaType.APPLICATION_JSON_VALUE})
	public String registrationCustmorClient(@RequestBody CustomerClient objCustomerClient) {
		if(objCustomerClient.getName()!=null & objCustomerClient.getName()!="" && objCustomerClient.getName()!="" && objCustomerClient.getRollno()!=0)
		{
			List<CustomerClient> clientDetail=clientServices.findClientDetailByEmailid(objCustomerClient);
			{
				if(clientDetail.isEmpty())
				{
					objClientRepo.save(objCustomerClient);
					return "record saved successfully";
				}
			}	
		}
		else return "Customer name or email or address can not be blank";
		return "Customer email is already exist";	
	}
//-----------------------------------	
	@GetMapping(value="/getdata",produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<CustomerClient> findClientData() {
		List<CustomerClient> list=objClientRepo.findAll();
		return list;	
	}
//-----------------------------------	
	@GetMapping(value="/saveComingDataFromService",produces= {MediaType.APPLICATION_JSON_VALUE})
	public String saveDtaToClientDb(@RequestParam("name")String name,@RequestParam("email")String email,@RequestParam("rollno")int rollno) {
		CustomerClient c=new CustomerClient();
		c.setName(name);
		c.setEmail(email);
		c.setRollno(rollno);
		objClientRepo.save(c);
		return "Record save successfuly";	
	}
}
//--------------------------------------
//http://localhost:8081/client/savedata     >> Client side Customer
//http://localhost:8081/client/getdata
//http://localhost:8081/client/saveComingDataFromService
//{
//    "name":"aman kumar",
//    "email":"aman12kumar@gmail.com",
//    "address":"delhi",
//    "rollno":"127"
//
//}

