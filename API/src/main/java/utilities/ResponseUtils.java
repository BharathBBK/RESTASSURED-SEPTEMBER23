package utilities;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseUtils {
	
	    
	    public static Response getresponse(String uri,String baseuri){
		 
	        RestAssured.baseURI = baseuri;
		    RequestSpecification requestSpecification = RestAssured.given() ;
		    Response response = requestSpecification.request(Method.GET, "/"+uri);
		    return response;
	   } 
	    public static Response getresponsebyID(String ID,String baseuri){
			
	    	RestAssured.baseURI = baseuri;
			RequestSpecification requestSpecification = RestAssured.given() ;
			Response response = requestSpecification.request(Method.GET, "/employee/"+ID);
			return response;
		 }   
		  
	    public static Response createNewEmpRecord(String baseuri,String stringJsn){
	    	
	    	RestAssured.baseURI = baseuri;
	        RequestSpecification requestSpecification = RestAssured.given();
	        requestSpecification.header("Content-type","application/json");
	        requestSpecification.body(stringJsn);
	        Response response = requestSpecification.request(Method.POST, "/create");
	        return response;	
	    
	    }
	    
	    public static Response createNewEmpRecord(String baseuri,JsonObject jso){
	    	Response response;
	    	RestAssured.baseURI = baseuri;
	    	RequestSpecification httpRequest = RestAssured.given();
	    	httpRequest.header("Content-type", "application/json");
	    	httpRequest.body(jso);
	    	response = httpRequest.request(Method.POST,"/create" );
	    	
	    	return response;
	    }
	    
	    @SuppressWarnings("unchecked")
		public static Response updateEmployeeDetails(String baseuri,String ID,Object name,Object salary,String age){
	    	Response response = null;
	    	RestAssured.baseURI = baseuri;
	    	RequestSpecification requestSpecification = RestAssured.given();
	    	try {
	    	 JSONObject jobj = new JSONObject();
	    	 jobj.put("name", name);
	    	 jobj.put("salary", salary);
	    	 jobj.put("age", age);
		     requestSpecification.header("Content-type", "application/json");
	    	 requestSpecification.body(jobj.toJSONString());
	    	 response = requestSpecification.request(Method.PUT, "/update/"+ID);
	    	
	    	 } catch (Exception E){System.out.println(E.getMessage());}
	    	 
	    	 return response; 
	     }
	    
		
		public static Response updateEmployeeDetails(String baseuri,String ID,String stringjson){
	    	 Response response = null;
	    	 RestAssured.baseURI = baseuri;
	    	 RequestSpecification requestSpecification = RestAssured.given();
	  	     requestSpecification.header("Content-type", "application/json");
	    	 requestSpecification.body(stringjson);
	    	 response = requestSpecification.request(Method.PUT, "/update/"+ID);
	    	
	    	return response; 
	     }
	    
	    public static Response DeleteMethod(String baseuri,String uri){
	       
	   	    RestAssured.baseURI = baseuri;
            RequestSpecification requestSpecification = RestAssured.given() ;
			Response response = requestSpecification.request(Method.DELETE, "/"+uri);
			return response;
	    }
	    
	    
	   public static void VerifyNewUserID(){
	    	 
	        Response res = ResponseUtils.createNewEmpRecord("", "");
	        String responseBody = res.getBody().asString();
	        System.out.println(responseBody);
	        JsonPath json = new JsonPath(responseBody);
	        System.out.println(json.get("id")); 
	        res = getresponse(PropertiFileReader.getValue("city"),PropertiFileReader.getValue("baseuri"));
	        JsonPath js= new JsonPath(res.getBody().asString());
	        ArrayList<String> ids = js.get("id");
	        for(String i : ids){
	    	if (i.contains(String.valueOf(json.get("id"))))
	    	{System.out.println("newly created id is inserted in DB>>"+i);}
	       	}
	    }
	    
    	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
    	   Response res;
		   PropertiFileReader prop = new PropertiFileReader();
		   prop.setup();
		   res = ResponseUtils.createNewEmpRecord(PropertiFileReader.getValue("baseuri"),JsonUtils.createEmployee("Bharath012", "52", "35000").toJSONString());
		   System.out.println(res.getBody().asString());
     }

}
