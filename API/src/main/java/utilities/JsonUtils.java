package utilities;

import org.json.simple.JSONObject;
import io.restassured.response.Response;


public class JsonUtils {
	
	
	@SuppressWarnings("unchecked")
	public static JSONObject createEmployee(String name,String age, String salary){
		
		JSONObject json = new JSONObject();
		
		json.put("name", name);
		json.put("salary", salary);
		json.put("age", age);
	    System.out.println(json.toJSONString());
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	public static JSONObject updateEmployeeDetails(String name,String salary,String age){
		
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("salary",salary);
		json.put("age",age);
		//System.out.println(json.toJSONString());
		return json;
	}
	
	
		public static void main (String[] args) {
    	
    	Response res;
    	String baseuri = "http://dummy.restapiexample.com/api/v1";
    	//res = ResponseUtils.getPost("http://dummy.restapiexample.com/api/v1", JsonUtils.createEmployee("001BBK", "25", "25000").toJSONString());
        res= ResponseUtils.updateEmployeeDetails(baseuri, "42825", JsonUtils.updateEmployeeDetails("001BBK","25000", "29").toJSONString());
        System.out.println(">>>"+res.getHeaders());
        res = ResponseUtils.getresponsebyID("42825", baseuri);
        System.out.println(res.getBody().asString());
	}
	
	

}
