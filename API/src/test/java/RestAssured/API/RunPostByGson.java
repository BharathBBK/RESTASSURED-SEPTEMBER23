package RestAssured.API;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import utilities.ResponseUtils;

public class RunPostByGson {
	public static GsonConversion gson;
	public static Logger logger = Logger.getLogger(RunPostByGson.class);
	
	@Test
    public void getGsonData(){
		logger.info("-------------------------------");
		Response res ;
		gson = new GsonConversion();
    	System.out.println(gson.toString());
    	Gson gsons = new GsonBuilder().setPrettyPrinting().create();
    	System.out.println(gsons.toJson(RunPostByGson.createemployee()));
    	res = ResponseUtils.createNewEmpRecord("http://dummy.restapiexample.com/api/v", gsons.toJson(RunPostByGson.createemployee()));
    	System.out.println(res.getBody().asString());
    	logger.info("-------------------------------");
    	logger.debug(gsons.toJson(RunPostByGson.createemployee()));
    	logger.debug(res.getBody().asString());
    	logger.debug("New User is successfully created");
    }
	
	public static Object createemployee(){
		logger.info("-------------------------------");
    	gson.setname("bharathbbk003");
    	gson.setsalary("25000");
    	gson.setage("25");
    	logger.info("-------------------------------");
		return gson;
	}

    
}
