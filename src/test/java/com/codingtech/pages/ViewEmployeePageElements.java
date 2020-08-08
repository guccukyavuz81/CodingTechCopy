package com.codingtech.pages;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codingtech.testbase.BaseClass;



public class ViewEmployeePageElements {

	@FindBy(id = "empsearch_employee_name_empName")
	public WebElement empName;

	@FindBy(id = "empsearch_id")
	public WebElement empID;

	@FindBy(id = "searchBtn")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//table[@id = 'resultTable']")
	public WebElement employeesTable;
	
	@FindBy(id = "empsearch_job_title")
	public WebElement jobTitlesDD;
	
	@FindBy(xpath = "//*[@id='resultTable']/tbody/tr[1]/td[3]/a")
	public WebElement employee;
	
	@FindBy(xpath = "//*[@id='btnSave']") 
	public WebElement employeeEdit;
	
	@FindBy(xpath = "//*[@id='btnSave']") 
	public WebElement JobEdit;  //*[@id='job_location']
	
	@FindBy(xpath = "//*[@id='job_location']") 
	public WebElement LocationDD;
	
	@FindBy(xpath = "//*[@id='personal_cmbNation']")
	public WebElement nationalityDD;

	@FindBy(xpath = "//table[@id = 'resultTable']//tbody/tr/td[3]")
	public List<WebElement> tableFirstName;
	
	@FindBy(xpath="(//a[text()='Job'])[2]")
	public WebElement Job;
	
	/**
	 * this method will check if employees table is diplayed
	 * @return
	 */
	public boolean isTableDisplayed() {
		return employeesTable.isDisplayed();
	}
	
	public List<Map<String, String>> getFirstNameFromTable() {
		List<Map<String, String>> uiName = new ArrayList<>();
		for (WebElement row : tableFirstName) {
			Map<String, String> storeUiNames = new LinkedHashMap<>();
			String tableName = row.getText();
			storeUiNames.put("emp_firstname", tableName);
			uiName.add(storeUiNames);
		}
		return uiName;
	}

	

	public List<Map<String, String>> getAllJobTitleDropDown(List<WebElement>JobTitlenames) {
		List<Map<String, String>> jobTilelist = new ArrayList<>();
		
		for (int i=0; i<JobTitlenames.size(); i++) {
			
			Map<String, String> storeJobTitles = new LinkedHashMap<>();
			String tableName=JobTitlenames.get(i).getText();
			storeJobTitles.put("job_title", tableName);
			jobTilelist.add(storeJobTitles);
		}
		return jobTilelist;
	}


	@FindBy(id="menu_admin_viewAdminModule")
	public WebElement adminBTN;
	
	@FindBy(id="menu_admin_viewJobTitleList")
	public WebElement Joblist;
	
	@FindBy(id="menu_admin_Job")
	public WebElement Jobmenue;
	
	@FindBy(xpath="//a[contains(text(),'Adams')] ")
	public WebElement search;
	
	@FindBy(xpath="//a[@href='/humanresources/symfony/web/index.php/pim/viewJobDetails/empNumber/14457']")
	public WebElement job;
	
	@FindBy(id="btnSave")
	public WebElement edit;
	
//	@FindBy(name="empsearch[job_title]")
//	public List<WebElement> jobTitle;
	
	
	@FindBy(xpath="//*[@id=\"resultTable\"]")
	public List<WebElement> jobTitle;
	

	@FindBy (xpath="//*[@id='resultTable']//tbody/tr/td/a")
			public List <WebElement> JobTitlenames;
	
	
	
	public ViewEmployeePageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
