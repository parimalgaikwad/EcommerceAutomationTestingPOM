package Pages;

import Utils.Utils;

public class HomePage extends Utils {

	public String verifyWelcomeMsg() {

		return getText("welcomemsg");
	}

	public MobilePage goToMobilePage() {

		click("mobile");

		return new MobilePage();
	}

}
