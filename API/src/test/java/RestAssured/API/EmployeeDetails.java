package RestAssured.API;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.PropertiFileReader;
import utilities.ResponseUtils;
   
public class EmployeeDetails {
	
	public String  ResID = "41038";
	
	@Before
	public void setup(){
		
		PropertiFileReader prop = new PropertiFileReader();
		try{
		prop.setup();
		//System.out.println(PropertiFileReader.getValue("city"));
		} catch (Exception E){
			System.out.println(E.getMessage());
		}
		System.out.println("Properties file is loaded");
	}
	@Test
	public void getEmployeeDetails(){
		Response res;
		res = ResponseUtils.getresponse(PropertiFileReader.getValue("city"), PropertiFileReader.getValue("baseuri"));
		String responseBody = res.getBody().asString();
		JsonPath json = new JsonPath(responseBody);
		ArrayList<String> Id = json.get("id");
		System.out.println(Id);
		ResID = Id.get(50);
		System.out.println(Id.get(50));
		System.out.println("Main method is executed");
	}
	@After
  public void getEmployeebyId(){
      Response res;
	  res = ResponseUtils.getresponsebyID(ResID, PropertiFileReader.getValue("baseuri"));
      String responseBody = res.getBody().asString();
      JsonPath json = new JsonPath(responseBody);
      System.out.println(json.get("id")+"--"+json.get("employee_name")+""+json.get("employee_salary")+"--"+json.get("employee_age"));
      System.out.println(responseBody);
      System.out.println("After method is executed");
  }
	
  public void updateEmployeeDetails(){
	  Response res;
	  res = ResponseUtils.getresponsebyID(ResID, PropertiFileReader.getValue("baseuri"));
	  String responseBody = res.getBody().asString();
      JsonPath json = new JsonPath(responseBody);
      System.out.println(json.get("id")+"--"+json.get("employee_name")+""+json.get("employee_salary")+"--"+json.get("employee_age"));
	  //res = ResponseUtils.updatemethod(PropertiFileReader.getValue("baseuri"),"14526", json.getString("name"), salary, age)
  }	
}