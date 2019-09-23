package RestAssured.API;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import utilities.PropertiFileReader;
import utilities.ResponseUtils;


public class CreateEmployeewithGSON {
	
	
	public static Logger logger = Logger.getLogger(CreateEmployeewithGSON.class);
	public static GsonConversion gson; 
	Gson gsons;
	public static Object setEmployeeDetails(){
		gson = new GsonConversion();
		gson.setname("Bharath52");
		gson.setsalary("25000");
		gson.setage("25");
		
		return gson;
	}
	@Before
	public void propertiSetup(){
		
		PropertiFileReader prop = new PropertiFileReader();
		try {
			prop.setup();
		System.out.println("Properties file loaded");
		logger.debug("--------------------------------------");
		logger.debug("Properties file is loaded");
		} catch(FileNotFoundException fn){
			System.out.println(fn.getMessage());
		} catch(Exception E){
			  System.out.println(E.getMessage());
		  }
		
	}
	@Test
	public void CreateEmployee(){
		Response res;
		gsons = new GsonBuilder().setPrettyPrinting().create();
		logger.debug("Json Input is given below");
		logger.debug(gsons.toJson(CreateEmployeewithGSON.setEmployeeDetails()));
		
		res = ResponseUtils.createNewEmpRecord(PropertiFileReader.getValue("baseuri"),gsons.toJson(CreateEmployeewithGSON.setEmployeeDetails()));
		String responseBody = res.getBody().asString();
		System.out.println(responseBody);
		logger.debug("New Employee details inserted in DB successfully----"+responseBody);
			
	}
	
	

}
