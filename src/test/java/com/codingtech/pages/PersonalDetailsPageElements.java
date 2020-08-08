package com.codingtech.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codingtech.testbase.BaseClass;

public class PersonalDetailsPageElements {
	
	@FindBy(id = "personal_txtEmpFirstName")
	public WebElement firstName;
	
	@FindBy(id = "personal_txtEmpMiddleName")
	public WebElement middleName;
	
	@FindBy(id = "personal_txtEmpLastName")
	public WebElement lastName;
	
	@FindBy(id = "personal_txtEmployeeId")
	public WebElement employeeId;
	
	@FindBy(id = "personal_DOB")
	public WebElement dateOfBird;
	
	@FindBy(name = "personal[optGender]")
	public List<WebElement> genderRadioGroup;
	
	@FindBy(xpath="//ul[@id='sidenav']/li[6]/a")
	public WebElement jobPages;
	
	@FindBy(id = "job_job_title")
	public WebElement jobTitle;
	
	@FindBy(id = "job_emp_status")
	public WebElement empStatus;
	
	@FindBy(id = "btnSave")
	public WebElement editBtn;
	
	@FindBy(xpath="//form[@id='frmEmpPersonalDetails']/fieldset/p/input")
	public WebElement editBtnPersonel;
	

	
	
	
	
	
	
	
	@FindBy(id = "personal_cmbNation")
	public WebElement nationalityDD;

	@FindBy(xpath = "//div[@id='pdMainContainer']/div[1]/h1")
	public WebElement lblPersonalDetails;

	
	@FindBy(xpath="//div[@id='profile-pic']//h1")
	public WebElement profilePic;
	
	@FindBy(id = "personal_txtLicenNo")
	public WebElement textDriverLicence;
	
	@FindBy(id = "personal_txtLicExpDate")
	public WebElement licExpDate;
	
	@FindBy(id = "personal_txtNICNo")
	public WebElement textSSN;
	
	@FindBy(id = "personal_txtSINNo")
	public WebElement textSINNo;
	
	@FindBy(id = "personal_txtEmpNickName")
	public WebElement textNickName;
	
	
	
	@FindBy(id = "personal_txtMilitarySer")
	public WebElement textMilitarySer;
	
	@FindBy(id = "personal_cmbMarital")
	public WebElement maritalStatusDD;
	
	
	public PersonalDetailsPageElements () {
		PageFactory.initElements(BaseClass.driver, this);
	}

}
