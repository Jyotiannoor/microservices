package sandy.springBootProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sandy.springBootProject.Common.CommonConstant;
import sandy.springBootProject.model.ClientCustomerDetails;
import sandy.springBootProject.model.CustomerDetails;
import sandy.springBootProject.model.ResponseMessage;
import sandy.springBootProject.repository.CustomerRepository;
import sandy.springBootProject.service.CustomerServices;
import sandy.springBootProject.service.UpdateServiceMongo;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerServices customerServices;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	UpdateServiceMongo updateServiceMongo;
	@Autowired
	RestTemplate restTemplate;
//------------------------------------------------------	
  @PostMapping(path ="/registration", produces= {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ResponseMessage> resisterCustomer(@RequestBody CustomerDetails objCustomerDetails) {
	 if(objCustomerDetails.getName()!=null && objCustomerDetails.getName()!="" && 
		objCustomerDetails.getEmail()!=null && objCustomerDetails.getEmail()!="" && 
		objCustomerDetails.getAddress()!=null && objCustomerDetails.getAddress()!="")   // for not blank
	 { 
      //	 List<CustomerDetails> detail=customerRepository.findByEmail(objCustomerDetails.getEmail());   //without service method		
		 List<CustomerDetails> detail=customerServices.findDetailByEmailId(objCustomerDetails); // for finding duplicate
		 if(detail.isEmpty())
		 {
			 customerServices.customerReg(objCustomerDetails); //for saving the data in the DB
		 }
		 
		 else { 
		       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Customer email is already exist", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));	  
	          }  
		 return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(objCustomerDetails.getName()+" >> Record saved successfully",HttpStatus.OK.value(),HttpStatus.OK));
     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Customer name or email or address can not be blank", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
  }
//---------------------------------------------------------------------------
  @PostMapping(path ="/update", produces= {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ResponseMessage> updateCustomer(@RequestBody CustomerDetails objCustomerDetails) {
	 if(objCustomerDetails.getName()!=null && objCustomerDetails.getName()!="" && 
		objCustomerDetails.getEmail()!=null && objCustomerDetails.getEmail()!="" && 
		objCustomerDetails.getAddress()!=null && objCustomerDetails.getAddress()!="")   // for not blank
	 { 	
		 List<CustomerDetails> detail=customerServices.findDetailByName(objCustomerDetails); // for finding duplicate 
			 if(detail.isEmpty())
			 {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Customer name is not found", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
			 }
			 else {
				 updateServiceMongo.updateAddress(objCustomerDetails.getName(), objCustomerDetails.getAddress());
				 return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(objCustomerDetails.getName()+" >> Record updated successfully",HttpStatus.OK.value(),HttpStatus.OK)); 
		 }
		
     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Customer name or email or address can not be blank", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
  }
//---------------------------------------------------------------------  
//  @GetMapping(path ="/getDetails", produces= {MediaType.APPLICATION_JSON_VALUE})
////  public List<CustomerDetails> getDetails(@RequestParam ("name")String name,@RequestParam ("email")String email,@RequestParam ("rollno")int rollno) {
////		 List<CustomerDetails> detail=customerServices.findDetailByEmailNameAndRollno(name,email,rollno);
////			return detail;
//			
//  public List<CustomerDetails> getDetails(@RequestParam ("name")String name) {
//		 List<CustomerDetails> detail=customerRepository.findDetailByEmailNameAndRollno(name,0);
//			return detail;			     
//  } //incomplete
//------------------------------------------------------------------
  @GetMapping(value ="/getClientDetails", produces= {MediaType.APPLICATION_JSON_VALUE})			
public String getClientDetails() {
	    String url ="http://localhost:8081/client/getdata ";   //(01)
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> e = new HttpEntity<>(headers);      //(02)passing header
		String response = restTemplate.exchange(url, HttpMethod.GET, e, String.class).getBody();
		System.out.println(response);
		return response; 
} 
//--------------------------------------------------------------------  
  @GetMapping(value ="/saveDataToClient", produces= {MediaType.APPLICATION_JSON_VALUE})			
  public String saveDataToClient(@RequestParam("name")String name,@RequestParam("email")String email,@RequestParam("rollno")int rollno) {
    
//  	    String rn = Integer.toString(rollno);
//		JSONObject json = new JSONObject(); // not importing   // option-2-JSONObject
//		Map<String, String> m = new HashMap<String, String>(); // option-3 Map
//		m.put("name", name);
//		m.put("email", email);
//		m.put("rollno", rn); 
		ClientCustomerDetails c= new ClientCustomerDetails();
		c.setName(name);
		c.setEmail(email);
		c.setRollno(rollno);
    
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> e = new HttpEntity<String>(c.toString(), headers); // (02)passing header
//		String url ="http://localhost:8081/client/saveComingDataFromService?name="+name+"&email="+email+"&rollno="+rollno;   //(01)
		String url =CommonConstant.client_url;
		String response = restTemplate.exchange(url, HttpMethod.POST, e, String.class).getBody();

//		String response = restTemplate.postForEntity(url, e ,String.class).getBody();
//		ResponseEntity<String> response = restTemplate.postForEntity(url, e ,String.class).getBody();
//		ResponseEntity<> r = restTemplate.postForEntity(url, e ,String.class).getBody();
//		ResponseEntity<String> response = restTemplate.postForEntity(url, e ,String.class);

		//		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(response, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
//     	System.out.println(response);
		return response;	
		
  } 
//------------------------------------------------------------------
}
//-----------------------------------------------------------
//http://localhost:8080/customer/registration >> save data
//http://localhost:8080/customer/update       >> update data
//http://localhost:8080/customer/getDetails   >> Service provider??
//http://localhost:8080/customer/getClientDetails  >> display Client Data via our Application
//http://localhost:8081/client/savedata         >> Client side Customer
//http://localhost:8081/client/getdata        >> Client side Customer
//-------------------------------------------------------

//@RequestBody when pass the whole object
//@RequestParam when pass a particular parameter
//@PathVariable when pass a particular parameter, it extracts the uri path
//restTemplate.exchange()>> help us to communicate with other application/client application 
   
//return new ResponseEntity<> ("Record Save Successfully",HttpStatus.CREATED);  //or- OK  -- (01)

//System.out.println(objCustomerDetails.getName()+" "+objCustomerDetails.getEmail()+" "+ objCustomerDetails.getAddress());






