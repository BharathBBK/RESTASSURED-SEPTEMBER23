package RestAssured.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonConversion {

	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("age")
	@Expose
	private String age;
	

	@SerializedName("salary")
	@Expose
	private String salary;
	
	
	public String getname(){
		return name;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getage(){
		return age;
	}
	
	public void setage(String age){
		this.age = age;
	}
	public String getsalary(){
		return salary;
	}
	
	public void setsalary(String salary){
		this.salary = salary;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     
		
		
	}

}
