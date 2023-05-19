package sandy.springBootProject.model;

import org.springframework.http.HttpStatus;


//common for all-generate constructor
public class ResponseMessage { //body(new ResponseMessage(below 3)
	private String message;  //customer name or any variable
	private int statusCode; // HttpStatus.OK.value()
	private HttpStatus status; // HttpStatus.OK
//--------------------------------------	
	
public ResponseMessage(String message, int statusCode, HttpStatus status) {
	super();
	this.message = message;
	this.statusCode = statusCode;
	this.status = status;
}
//------------------------------------------
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
//----------------------------------
	
	
	

}
