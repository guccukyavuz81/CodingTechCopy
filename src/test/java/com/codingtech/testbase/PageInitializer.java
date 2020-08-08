package com.codingtech.testbase;

import com.codingtech.pages.DashboardPageElements;
import com.codingtech.pages.LoginPageElements;
import com.codingtech.pages.PersonalDetailsPageElements;
import com.codingtech.pages.ViewEmployeePageElements;

//initializes all pages class and stores references in static varibales
//that will called/used in test classes
public class PageInitializer extends BaseClass{

	public static LoginPageElements login;
	public static DashboardPageElements dashboard;
	public static ViewEmployeePageElements viewEmp;
	public static PersonalDetailsPageElements pdetails;
	
	public static void initialize() {
		
		login=new LoginPageElements();
		dashboard=new DashboardPageElements();
		viewEmp=new ViewEmployeePageElements();
		pdetails = new PersonalDetailsPageElements();
	}
}
