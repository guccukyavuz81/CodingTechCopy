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

public class UserStory38 extends CommonMethods{
//For Employe List Search Page. The Job Title drop down should have all the
//	values in the Database Job Title Table. Without Filter.
	
	public static List<Map<String,String>> DBJOBLIST;
	public static List<Map<String,String>> jobTitles;

	@Given("I log in to HRMS")
	public void i_log_in_to_HRMS() {
		login.username.sendKeys(ConfigsReader.getProperty("username"));
	    sendText(login.password, ConfigsReader.getProperty("password"));
	    click(login.loginBtn);
	}

	@Given("go to Employee Search page")
	public void go_to_Employee_Search_page() throws InterruptedException {
		click(dashboard.PIM);
	    click(dashboard.empListPage);
	    Thread.sleep(3000); 
	}

	@When("I get the Job Titles as a list")
	public void i_get_the_Job_Titles_as_a_list() {

		List<Map<String,String>> jobTitles = new ArrayList<>();
		Select select = new Select(viewEmp.jobTitlesDD);
		for(int i=1;i<select.getOptions().size();i++) {
		String jobTitle = select.getOptions().get(i).getText();
		Map<String,String> jobMap = new LinkedHashMap<String, String>();
		jobMap.put("job_title", jobTitle);
		jobTitles.add(jobMap);
		}
		System.out.println(jobTitles);
		System.out.println(jobTitles.size());
	
	}

	@When("I get the Job titles from the database")
	public void get_the_Job_titles_from_the_database() {
		DBUtils.getConnection();
		List<Map<String,String>> DBJOBLIST=DBUtils.jobTitlesFromDB("select job_title from ohrm_job_title where job_title =is_deleted order by job_title");
		System.out.println(DBJOBLIST);
		System.out.println(DBJOBLIST.size());
	}

	@Then("I validate the list from UI against DB")
	public void i_validate_the_list_from_UI_against_DB() {
		Assert.assertEquals(jobTitles, DBJOBLIST);
		}



}
