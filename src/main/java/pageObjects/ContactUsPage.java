package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {
	WebDriver driver;
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@name='email']") private WebElement Email;
	@FindBy(xpath = "//input[@name='subject']") private WebElement Subject;
	@FindBy(id = "message") private WebElement Message;
	@FindBy(xpath = "//input[@name='upload_file']") private WebElement ChooseFile;
	@FindBy(xpath = "//input[@name='submit']") private WebElement Submitt;
	@FindBy(xpath = "//div[@class='contact-form']/div[3]/a" ) private WebElement HomeButton;
	@FindBy(xpath = "//div[@class='contact-form']/h2") private WebElement GetInTouch;
	public String getInTouchText() {
		return GetInTouch.getText();
	}
	public WebElement ContactUsEmailValidation() {
		return Email;
	}
	public WebElement EnterSubjectText() {
		return Subject;
	}
	public WebElement EnterMessageText() {
		return Message;
	}
	public WebElement uplodeFileValidaton() throws Exception {
		return ChooseFile;
	}

}
