package pageObjects;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpOrLoginPage {
	WebDriver driver;
	JavascriptExecutor js;
	public SignUpOrLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js=(JavascriptExecutor)driver;
	}
	@FindBy(xpath = "//a[text()=' Signup / Login']") private WebElement SignUpOrLoginButton;
	@FindBy(xpath = "//input[@name='name']") private WebElement Name;
	@FindBy(xpath = "//div[@class='signup-form']/form/input[3]") private WebElement Email;
	@FindBy(xpath = "//button[text()='Signup']") private WebElement SignUpButton;
	@FindBy(xpath = "//div[@class='signup-form']/form/p") private WebElement SignUpErrorMessage;
	@FindBy(xpath = "//div[@class='login-form']/h2/b") private WebElement InformationText;
	@FindBy(xpath = "//div[@class='login-form']/h2") private WebElement LoginText;
	@FindBy(xpath = "//div[@class='login-form']/form/input[2]") private WebElement EmailAddress;
	@FindBy(xpath = "//input[@name='password']") private WebElement Password;
	@FindBy(xpath = "//button[text()='Login']") private WebElement LoginButton;
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[10]/a") private WebElement LoginUserName;
	@FindBy(xpath = "//div[@class='login-form']/form/p") private WebElement IncorectPassOrEmail;
	@FindBy(id = "id_gender2") private WebElement MrsButton;
	@FindBy(id = "name") private WebElement AccountInfomationName;
	@FindBy(id = "email") private WebElement AccountInformationEmail;
	@FindBy(id = "password") private WebElement SignupPagePassword;
	@FindBy(id = "days") private WebElement DropDownDay;
	@FindBy(id = "months") private WebElement DropDownMonth;
	@FindBy(id = "years") private WebElement DropDownYear;
	@FindBy(id = "newsletter") private WebElement NewsLetterCheckBox;
	@FindBy(id = "optin") private WebElement PtnersCheckBox;
	@FindBy(id = "first_name") private WebElement FirstName;
	@FindBy(id = "last_name") private WebElement LastName;
	@FindBy(id = "company") private WebElement Company;
	@FindBy(id = "address1") private WebElement Address1;
	@FindBy(id = "address2") private WebElement Address2;
	@FindBy(id = "country") private WebElement Country;
	@FindBy(id = "state") private WebElement StateName;
	@FindBy(id = "city") private WebElement CityName;
	@FindBy(id = "zipcode") private WebElement ZipCode;
	@FindBy(id = "mobile_number") private WebElement MobileNumber;
	@FindBy(xpath = "//button[text()='Create Account']") private WebElement CreateAccountButton;
	@FindBy(xpath = "//a[text()='Continue']") private WebElement ContinueButton;
	@FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/h2/b") private WebElement AccountCreated;
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[10]/a")  private WebElement LoggedUserName;
	@FindBy(id = "susbscribe_email") private WebElement YourEmailAddress;
	@FindBy(xpath = "//div[@class='container']/div/div[2]/div/ul/li[5]") private WebElement DeleteButton;
	@FindBy(xpath = "//div[@class='col-sm-9 col-sm-offset-1']/h2/b") private WebElement AccounteDeleted;
	public WebElement EnterName() {
		return Name;
	}
	public WebElement EnterEmail() {
		return Email;
	}

	public void SignupButtonValidation() {
		SignUpButton.click();
	}
	public String existedErrorMessage() {
		return SignUpErrorMessage.getText();
	}
	public String VerifySignupText() {
		return InformationText.getText();
	}
	public void RadioButton() {
		MrsButton.click();
	}
	public WebElement PasswordText() {
		return SignupPagePassword;
	}
	public void DayValidation() {
		Select s= new Select(DropDownDay);
		s.selectByIndex(12);
	}
	public void monthValidation() {
		Select s= new Select(DropDownMonth);
		s.selectByVisibleText("February");
	}
	public void YearValidation() throws InterruptedException  {
		Select s= new Select(DropDownYear);
		s.selectByValue("2021");
		Thread.sleep(6000);	
	}
	public void SelectBox1() throws InterruptedException {
		NewsLetterCheckBox.click();
		Thread.sleep(6000);
	}
	public void SelectBox2() {
		PtnersCheckBox.click();
	}
	public WebElement FirstnameTextBox() {
		return FirstName;
	}
	public WebElement LastNameTextBox() {
		return LastName;
	}
	public WebElement CompanyValidation() {
		return Company;
	}
	public WebElement Address1Validation() {
		return Address1;
	}
	public WebElement Address2Validation() {
		return Address2;
	}
	public void CountryDropDown() {
		Select s = new Select(Country);
		s.selectByVisibleText("India");
	}
	public WebElement EnterState() {
		return StateName;
	}
	public WebElement EnterCity() {
		return CityName;
	}
	public WebElement EnterZipCode() {
		return ZipCode;
	}
	public WebElement EnterMobileNumber() {
		return MobileNumber;
	}
	public void	ClickOnCreateAccountButton(){
		CreateAccountButton.click();
	}
	public String AccountCreatedValidation() {
		return AccountCreated.getText();
	}
	public void ClickOnContinueButton() {
		ContinueButton.click();
	}
	public String LoggedUserValidation() {
		return LoggedUserName.getText();
	}
	public void ClickOnDelete() {
		DeleteButton.click();
	}
	public String AccountDleledText() {
		return AccounteDeleted.getText();
	}
	public void ClickOnContinue() {
		ContinueButton.click();
	}
	public String loginToYourAccountText() {
		return LoginText.getText();
	}
	public WebElement LoginEmailValidation() {
		return EmailAddress;
	}
	public WebElement LoginPasswordValidation() {
		return Password;
	}
	public void ClickOnLogginButton() {
		LoginButton.click();
	}
	public String loginUserValiDation() {
		return LoginUserName.getText();
	}
	public String IncorectCredentialText() {
		return IncorectPassOrEmail.getText();
	}
}