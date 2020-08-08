package com.codingtech.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codingtech.testbase.BaseClass;
import com.syntax.utils.CommonMethods;


public class DashboardPageElements extends CommonMethods{

	@FindBy(id = "welcome")
	public WebElement welcome;
	
	@FindBy(xpath = "//div[@id='branding']/a[1]/img")
	public WebElement logo;

	@FindBy(xpath = "//a[@id='menu_pim_addEmployee']")
	public WebElement addEmp;
	
	@FindBy(xpath = "//a[@id='menu_pim_viewPimModule']")
	public WebElement PIM;
	
	@FindBy(id="menu_pim_viewEmployeeList")
	public WebElement empListPage;
	
	@FindBy(xpath="//div[@class='menu']/ul/li")
	public List<WebElement> dashMenu;
	
	@FindBy(id = "empsearch_id")
	public WebElement idEmp;

	@FindBy (id="searchBtn")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//table[@id = 'resultTable']")
	public WebElement employeesTable;
	
	@FindBy(xpath = "//table[@id = 'resultTable']//tbody/tr/td[3]")
	public List<WebElement> tableFirstName;
	
	@FindBy(xpath="//ul[@id='sidenav']/li[5]")
	public WebElement job;
	
	public DashboardPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	public void navigateToAddEmployee() {
		jsClick(PIM);
		jsClick(empListPage);
	}
	
}
