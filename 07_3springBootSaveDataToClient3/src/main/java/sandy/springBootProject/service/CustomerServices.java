package sandy.springBootProject.service; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sandy.springBootProject.model.CustomerDetails;
import sandy.springBootProject.repository.CustomerRepository;
@Service
public class CustomerServices {
	@Autowired
	CustomerRepository customerRepository;
//--------------------------------------------	
	public void customerReg(CustomerDetails customerDetails) {
		customerRepository.save(customerDetails);	
	}
//---------------------------------------------
	public List<CustomerDetails> findDetailByEmailId(CustomerDetails customerDetails) {
//		List<CustomerDetails> detail=customerRepository.findByEmail(customerDetails.getEmail());
		return customerRepository.findByEmail(customerDetails.getEmail()); 
	}
//------------------------------------------------------
	public List<CustomerDetails> findDetailByName(CustomerDetails customerDetails) {
//		List<CustomerDetails> detail=customerRepository.findByEmail(customerDetails.getEmail());
		return customerRepository.findByName(customerDetails.getName()); 
	}
//---------------------------------------------------------------------	
//	public List<CustomerDetails> findDetailByEmailNameAndRollno(String email,String name , int rollno) {
//		
//		return customerRepository.findDetailByEmailNameAndRollno(email,name ,rollno);
//	}
	
//public List<CustomerDetails> findDetailByEmailNameAndRollno(String name) {
//		
//		return customerRepository.findDetailByEmailNameAndRollno(name, name, 0);
//	}
}
