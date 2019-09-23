package RestAssured.API;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.restassured.response.Response;
import utilities.JsonUtils;
import utilities.PropertiFileReader;
import utilities.ResponseUtils;

public class UpdateEmployeeDetails {

	public String ResID = "42978";
	public String baseuri = "";
	
	@Before
	public void setup(){
		PropertiFileReader prop = new PropertiFileReader();
		try{
		prop.setup();
		baseuri = PropertiFileReader.getValue("baseuri");
		//System.out.println(PropertiFileReader.getValue("city"));
		} catch (Exception E){
			System.out.println(E.getMessage());
		}
		System.out.println("Properties file is loaded");
	}
	
		@Test
	  public void updateEmployeeDetails(){
		  Response res;
		  res = ResponseUtils.updateEmployeeDetails(baseuri,ResID,JsonUtils.updateEmployeeDetails("BBKNEW01","25000","23").toJSONString());
	      System.out.println("employee details are updated successfully");
		  System.out.println(res.getBody().asString());
		  }	
		  @After
		  public void getEmployeebyId(){
		      Response res;
			  res = ResponseUtils.getresponsebyID(ResID, PropertiFileReader.getValue("baseuri"));
	          System.out.println(res.getBody().asString());
		  }
	
	
}
