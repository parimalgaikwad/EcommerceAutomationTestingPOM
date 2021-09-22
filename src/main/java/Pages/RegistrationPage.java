package Pages;

import Utils.Utils;

public class RegistrationPage extends Utils {

	
	public String createAccount(String fname, String lname, String email , String pwd 
			,String cpwd) {
		
		type("firstname",fname);
		type("lastname",lname);
		type("mail",email);
		type("password",pwd);
		type("confirmpassword",cpwd);
		click("register");
		return getText("successmsg");
		
	}
	
	
}
