package tests;

import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ClearRoundForClass {
	
	public static String url = "http://dummy.restapiexample.com/api/v1";
	
	
	//This method is used to get all employee records.
	public static void getEmployees(){
		
		//Below line will load the base url of the web service.
		RestAssured.baseURI = url;
		//Below line will initiate Request Specifications  
		RequestSpecification httpRequest = RestAssured.given();
		//Hear we are adding remaining parameters to the request at the same time getting response
		Response response = httpRequest.request(Method.GET, "/employees");
		 //We are going to store total response in a string.
		String totalResponse = response.getBody().asString();
		//let's print the response
		System.out.println(totalResponse);
		
	}
	//This method is used to get employee record on the basis of employee ID
	public static void getEmployeeByID(String ID){
		
		//Below line will load the base url of the web service.
		RestAssured.baseURI = url;
		//Below line will initiate Request Specifications  
		RequestSpecification httpRequest = RestAssured.given();
		//Hear we are adding remaining parameters to the request at the same time getting response
		Response response = httpRequest.request(Method.GET, "/employee/"+ID);
		//We are going to store total response in a string.
		String totalResponse = response.getBody().asString();
		//let's print the response
		System.out.println(totalResponse);
				
	}
	
	//This method will create new employee record
	@SuppressWarnings("unchecked")
	public static String CreateNewEmpRecord(){
		//Below line will load the base url of the web service.
		RestAssured.baseURI = url;
		//Below line will initiate the request specification
		RequestSpecification httprequest = RestAssured.given();
		//For post method we are going to provide input data by using JSONObject
		JSONObject jobj = new JSONObject();
		jobj.put("name","Full round3");
		jobj.put("age", "25");
		jobj.put("salary","250000");
		//it's also a part of request specifications, we have to provide content type 
		httprequest.header("Content-type","application/json");
		//Hear we need to pass above input data in request body and format should be json.
		httprequest.body(jobj.toJSONString());
		//Now we are going to hit the request and will store the response.
		Response response = httprequest.request(Method.POST,"/create");
		//we have to store all the response into a string then we can access values node by node.
		String responsebody = response.getBody().asString();
		//Hear we have to use JSONPath to get specific node values from the response.
		JsonPath jso = new JsonPath(responsebody);
		return jso.get("id");
	}
	   //This method is used to update the details of an employee
	@SuppressWarnings("unchecked")
	public static void updateEmpDetails(String empid){
		//Below line will load the base url of the web service.
		RestAssured.baseURI = url;
		//Below line will initiate request specification
		RequestSpecification httprequest = RestAssured.given();
		//For put method we are going to provide input data by using JSONObject
		JSONObject jobj = new JSONObject();
		jobj.put("name", "Full round3");
		jobj.put("age", "25");
		jobj.put("salary","250001");
		//it's also part of request specifications, we have to provide content type
		httprequest.header("Content-type","application/json");
		//Hear we need to pass above input data in request body and format should be json
		httprequest.body(jobj.toJSONString());
		//We are going to hit request and will store the response.
		Response response = httprequest.request(Method.PUT, "/update/"+empid);
		
		System.out.println(response.getBody().asString());
	}
	
	 
	public static void deleteRecord(String empid) {
		
		//Below line will load the base url of the web service.
		RestAssured.baseURI = url;
		//Below line will initiate Request Specifications  
		RequestSpecification httpRequest = RestAssured.given();
		//Hear we are adding remaining parameters to the request at the same time getting response
		Response response = httpRequest.request(Method.DELETE, "/delete/"+empid);
		//We are going to store total response in a string.
		String totalResponse = response.getBody().asString();
		//let's print the response
		System.out.println(totalResponse);	
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //Get all employee details
		ClearRoundForClass.getEmployees();
		//Get employee details by ID
		ClearRoundForClass.getEmployeeByID("59691");
		//Create New employee record
		String newemp = ClearRoundForClass.CreateNewEmpRecord();
        System.out.println(newemp);
        //Update the newly created employee details
		ClearRoundForClass.updateEmpDetails(newemp);
		//Get updated employee details
        ClearRoundForClass.getEmployeeByID(newemp);
        //ClearRoundForClass.deleteRecord("60426");
        //ClearRoundForClass.getEmployeeByID("60426");
	}

}
