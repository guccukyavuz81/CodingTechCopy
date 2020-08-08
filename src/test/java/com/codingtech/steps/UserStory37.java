package com.codingtech.steps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;
import com.syntax.utils.DBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStory37 extends CommonMethods{
	public static List<Map<String,String>> locations;
	public static List<Map<String,String>> DBlocationList;
	
//	@Given("User login to HRMS application")
//	public void user_login_to_HRMS_application() {
//		login.username.sendKeys(ConfigsReader.getProperty("username"));
//	    sendText(login.password, ConfigsReader.getProperty("password"));
//	    click(login.loginBtn);
//	}
//
//	@Given("Navigate to Edit Users")
//	public void navigate_to_Edit_Users() throws InterruptedException {
//		click(dashboard.PIM);
//	    click(dashboard.empListPage);
//	    Thread.sleep(2000);
//	    click(viewEmp.employee);
//	  
//	}
	
	@Given("Navigate to Job and click on Edit")
	public void navigate_to_Job_and_click_on_Edit() {
		 click(viewEmp.Job);
		 click(viewEmp.JobEdit);
	}

	@When("Open Office Location Drop down")
	public void open_Office_Location_Drop_down() {
	    
		locations = new ArrayList<>();
		Select select = new Select(viewEmp.LocationDD);
		for(int i=1;i<select.getOptions().size();i++) {
		String location = select.getOptions().get(i).getText();
		Map<String,String> locationMap = new LinkedHashMap<String, String>();
		locationMap.put("name", location);
		locations.add(locationMap);
		}
		System.out.println(locations);
		System.out.println(locations.size());
		
		
	}
	
	@Then("All the values in the Database should be available in Drop down")
	public void all_the_values_in_the_Database_should_be_available_in_Drop_down() {
		DBUtils.getConnection();
		DBlocationList=DBUtils.locationsFromDB("select distinct name  from ohrm_location order by name");
		System.out.println(DBlocationList);
		System.out.println(DBlocationList.size());
		Assert.assertEquals(locations, DBlocationList);
	}

}
