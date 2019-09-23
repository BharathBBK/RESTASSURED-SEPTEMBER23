package RestAssured.API;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.JsonUtils;
import utilities.PropertiFileReader;
import utilities.ResponseUtils;

public class CreateNewEmployee {

	public String baseuri = "";
	public String newID = "";
	Response res;
	@Before
	public void setup(){
		
		PropertiFileReader prop = new PropertiFileReader();
		try {
			
			prop.setup();
			System.out.println("Properties file is loaded");
			baseuri = PropertiFileReader.getValue("baseuri");
		}catch(FileNotFoundException E){
			
		System.out.println(E.getMessage());	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
			
	}
	
	@Test
	public void NewEmployee(){
		System.out.println(baseuri);
		res = ResponseUtils.createNewEmpRecord(baseuri, JsonUtils.createEmployee("BBKNEW01","22", "25000").toJSONString());
		JsonPath json = new JsonPath(res.getBody().asString());
		newID = json.get("id");
		System.out.println(newID);
		
	}
	
	@After
	public void verifyNewlyCreatedAccount(){
		
		res = ResponseUtils.getresponsebyID(newID, baseuri);
		System.out.println(res.getBody().asString());
	}
	

}
