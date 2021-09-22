package Pages;

import Utils.Utils;


public class LoginPage extends Utils{

	
	
	public String verifyTitle() {
		click("account");
		click("myaccount");
		
		return getText("title");
	}
	
	public HomePage doLogin(String mail , String pwd) {
		click("account");
		click("myaccount");
		
		type("emailID", mail);
		type("loginpassword",pwd);
		click("loginbtn");
		
		
		return new HomePage();
		
	}
	
	public RegistrationPage goToCreateAnAccount() {
				
		click("account");
		click("myaccount");
		click("createanaccount");
		
		return new RegistrationPage();
	}
	
	
}
