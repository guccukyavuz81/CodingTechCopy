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

public class UserStory39 extends CommonMethods{

	public static List<Map<String,String>> nationalities;
	public static List<Map<String,String>> DBNationalityList;
	
	@Given("User login to HRMS application")
	public void user_login_to_HRMS_application() {
		login.username.sendKeys(ConfigsReader.getProperty("username"));
	    sendText(login.password, ConfigsReader.getProperty("password"));
	    click(login.loginBtn);
	}

	@Given("Navigate to Edit Users")
	public void navigate_to_Edit_Users() throws InterruptedException {
		click(dashboard.PIM);
	    click(dashboard.empListPage);
	    Thread.sleep(2000);
	    click(viewEmp.employee);
	    click(viewEmp.employeeEdit);

	}

	@When("Open Nationality Drop down")
	public void open_Nationality_Drop_down() {
		nationalities = new ArrayList<>();
		Select select = new Select(viewEmp.nationalityDD);
		for(int i=1;i<select.getOptions().size();i++) {
		String nationality = select.getOptions().get(i).getText();
		Map<String,String> nationalityMap = new LinkedHashMap<String, String>();
		nationalityMap.put("name", nationality);
		nationalities.add(nationalityMap);
		}
		System.out.println(nationalities);
		System.out.println(nationalities.size());
	}

	@Then("All the values in the Database should be available in Drop down.")
	public void all_the_values_in_the_Database_should_be_available_in_Drop_down() {
		DBUtils.getConnection();
		DBNationalityList=DBUtils.nationalitiesFromDB("select distinct name from ohrm_nationality order by name asc" + 
				"");
		System.out.println(DBNationalityList);
		System.out.println(DBNationalityList.size());
		Assert.assertEquals(nationalities, DBNationalityList);
	}



}
