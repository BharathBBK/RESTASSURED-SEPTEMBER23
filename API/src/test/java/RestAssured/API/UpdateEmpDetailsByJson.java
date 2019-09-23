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

public class UpdateEmpDetailsByJson {

public static GsonConversion gson;
	Gson gsons;
	Response res;
	public String inputID = "47361";
    public static Logger logger = Logger.getLogger(UpdateEmpDetailsByJson.class);
	    public static Object empDetails(){
	    	//{"name":"Bharath5","age":"25","salary":"25000","id":"47361"}
	    	gson = new GsonConversion();
	    	gson.setname("Bharath5");
	    	gson.setsalary("25000");
	    	gson.setage("26");
	    	
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
	    public void updateDetails(){
	    	gsons = new GsonBuilder().setPrettyPrinting().create();
	    	System.out.println(gsons.toJson(UpdateEmpDetailsByJson.empDetails()));
	    	logger.debug(gsons.toJson(UpdateEmpDetailsByJson.empDetails()));
	    	
	    	res = ResponseUtils.updateEmployeeDetails(PropertiFileReader.getValue("baseuri"),inputID, gsons.toJson(UpdateEmpDetailsByJson.empDetails()));
	    	System.out.println(res.getBody().asString());
	    	logger.debug(res.getBody().asString());
	    }
	
	
	
	
}
