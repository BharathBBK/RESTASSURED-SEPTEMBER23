package tests;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import RestAssured.API.GsonConversion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.PropertiFileReader;
import utilities.ResponseUtils;

public class EndToEnd {
	
	public static Logger logger = Logger.getLogger(EndToEnd.class);
    public static GsonConversion gson;
	public Gson gsons;
	@Before
	public void setup(){
		logger.info("--------------------------------------");
		PropertiFileReader prop = new PropertiFileReader();
		try {
			prop.setup();
			logger.debug("Properties file is loaded");
		}catch(Exception E) {
		System.out.println("File is not loaded"+E.getMessage());	
		}		
	}
	
	public static Object createEmp(){
		
		gson = new GsonConversion();
		gson.setname("NewEmp003");
		gson.setsalary("50000");
		gson.setage("56");
		System.out.println(gson.toString());
		return gson;
	} 
	
	@Test
	public void ValidateFlow(){
		
		Response res;
		String baseuri = PropertiFileReader.getValue("baseuri");
		gsons = new GsonBuilder().setPrettyPrinting().create();
		
		//NEW EMployee records inserted
		logger.debug("------------------------001");
		res = ResponseUtils.createNewEmpRecord(baseuri,gsons.toJson(EndToEnd.createEmp()));
		logger.debug("New Account is created"+res.getBody().asString());
		System.out.println("New Account is created"+res.getBody().asString());
		JsonPath jso = new JsonPath(res.getBody().asString());
		String newID = jso.get("id");
		
		//Update details of newly created account
		logger.debug("---------------------002");
		res = ResponseUtils.updateEmployeeDetails(baseuri, newID, gsons.toJson(EndToEnd.createEmp()));
		logger.debug("Newly created Account details are updated"+res.getBody().asString());
		System.out.println("Newly created Account details are updated"+res.getBody().asString());
		
		//Verify employee details with ID
		logger.debug("---------------------003");
		res = ResponseUtils.getresponsebyID(newID, baseuri);
		logger.debug("Newly created Account details are updated"+res.getBody().asString());
		System.out.println("Newly created Account details are varified"+res.getBody().asString());
		logger.info("End to End cycle executed successfully");
	}
	
	

}
