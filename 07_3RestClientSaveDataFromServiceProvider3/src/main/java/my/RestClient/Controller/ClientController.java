package my.RestClient.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.RestClient.Model.ClientCustomer;
import my.RestClient.Repo.ClientRepo;


@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	ClientRepo objClientRepo;
//	@Autowired
//	ClientServices clientServices; 
//------------------------------------	
	@PostMapping(value="/savedata",produces= {MediaType.APPLICATION_JSON_VALUE})
	public String registrationCustmorClient(@RequestBody ClientCustomer objClientCustomer) {
			objClientRepo.save(objClientCustomer);
			return "record saved successfully";
	}
//-----------------------------------	
	@GetMapping(value="/getdata",produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<ClientCustomer> findClientData() {
		List<ClientCustomer> list=objClientRepo.findAll();
		return list;	
	}
//-----------------------------------	
	@PostMapping(value="/saveComingDataFromService",produces= {MediaType.APPLICATION_JSON_VALUE})
	public String saveDtaToClientDb(@RequestBody ClientCustomer objClientCustomer) {
		objClientRepo.save(objClientCustomer);
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

