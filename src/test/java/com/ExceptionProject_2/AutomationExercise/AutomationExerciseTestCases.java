package com.ExceptionProject_2.AutomationExercise;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.SignUpOrLoginPage;
import pageObjects.WindowHandles;


public class AutomationExerciseTestCases {
	WebDriver driver;ChromeOptions options;
	HomePage hp; SignUpOrLoginPage lp; ContactUsPage cu; ProductsPage pp; CartPage cp; WindowHandles handles;

	@BeforeTest
	public void initialisation() {
		options = new ChromeOptions();
		options.addExtensions(new File("./Extensions/AdBlock.crx"));
		driver = new ChromeDriver();
		hp = new HomePage(driver); lp = new SignUpOrLoginPage(driver); cu = new ContactUsPage(driver);
		pp = new ProductsPage(driver); cp = new CartPage(driver);
		handles = new WindowHandles(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.automationexercise.com/");
	}

	@Test
	public void headerElements() {
		List<WebElement> headerElements = hp.ListOfHomePageElements();
		for(int i = 0; i<headerElements.size(); i++) {
			System.out.println(headerElements.get(i).getText());
		}
	}
	@Test
	public void Brands() {

		List<WebElement> Brand = pp.BrandNamesText();
		for(int i=0;i<Brand.size();i++) {
			System.out.println(Brand.get(i).getText());
		}
	}

	@Test(priority=1)
	public void registerPageValidation() throws InterruptedException   {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet = ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		String newUser= hp.NewUserSignUpValidation();
		Assert.assertEquals(newUser, sheet.getRow(1).getCell(2).toString());
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");	
		WebElement N=lp.EnterName();
		N.sendKeys(sheet2.getRow(1).getCell(0).toString());
		WebElement E= lp.EnterEmail();
		E.sendKeys(sheet2.getRow(1).getCell(1).toString());
		lp.SignupButtonValidation();
		String informationTxt=lp.VerifySignupText();
		Assert.assertEquals(informationTxt, "ENTER ACCOUNT INFORMATION");
		lp.RadioButton();
		WebElement pass=lp.PasswordText();
		pass.sendKeys(sheet2.getRow(1).getCell(3).toString());
		lp.DayValidation();
		lp.monthValidation();
		lp.YearValidation();
		lp.SelectBox1();
		lp.SelectBox2();
		WebElement First=lp.FirstnameTextBox();
		First.sendKeys(sheet2.getRow(1).getCell(4).toString());
		WebElement Last=lp.LastNameTextBox();
		Last.sendKeys(sheet2.getRow(1).getCell(5).toString());
		WebElement Company = lp.CompanyValidation();
		Company.sendKeys(sheet2.getRow(1).getCell(6).toString());
		WebElement Adress1 = lp.Address1Validation();
		Adress1.sendKeys(sheet2.getRow(1).getCell(7).toString());
		WebElement Adress2 = lp.Address2Validation();
		Adress2.sendKeys(sheet2.getRow(1).getCell(8).toString());
		lp.CountryDropDown();
		WebElement state = lp.EnterState();
		state.sendKeys(sheet2.getRow(3).getCell(0).toString());
		WebElement city = lp.EnterCity();
		city.sendKeys(sheet2.getRow(3).getCell(1).toString());
		WebElement ZipCode = lp.EnterZipCode();
		ZipCode.sendKeys(sheet2.getRow(3).getCell(2).toString());
		WebElement MobileNo = lp.EnterMobileNumber();
		MobileNo.sendKeys(sheet2.getRow(3).getCell(3).toString());
		lp.ClickOnCreateAccountButton();
		String Account = lp.AccountCreatedValidation();
		Assert.assertEquals(Account, sheet2.getRow(3).getCell(4).toString());
		lp.ClickOnContinueButton();
		String Logged = lp.LoggedUserValidation();
		Assert.assertEquals(Logged, sheet2.getRow(3).getCell(5).toString());
		lp.ClickOnDelete();
		String Deleted = lp.AccountDleledText();
		Assert.assertEquals(Deleted, sheet2.getRow(3).getCell(6).toString());
		lp.ClickOnContinue();

	}
	@Test(priority=2)
	public void validCredentials() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet = ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		String login = lp.loginToYourAccountText();
		Assert.assertEquals(login, sheet2.getRow(3).getCell(7).toString());
		WebElement Email = lp.LoginEmailValidation();
		Email.sendKeys(sheet2.getRow(5).getCell(0).toString());
		WebElement Password = lp.LoginPasswordValidation();
		Password.sendKeys(sheet2.getRow(5).getCell(1).toString());
		lp.ClickOnLogginButton();
	}
	@Test(priority=3)
	public void invalidCredentials() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet = ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		String login = lp.loginToYourAccountText();
		Assert.assertEquals(login, sheet2.getRow(3).getCell(7).toString());
		WebElement Email = lp.LoginEmailValidation();
		Email.sendKeys(sheet2.getRow(5).getCell(2).toString());
		WebElement Password = lp.LoginPasswordValidation();
		Password.sendKeys(sheet2.getRow(5).getCell(3).toString());
		lp.ClickOnLogginButton();
		String text = lp.IncorectCredentialText();
		Assert.assertEquals(text, sheet2.getRow(5).getCell(4).toString());

	}
	@Test(priority=4)
	public void LougoutUserValidation() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet = ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		String login = lp.loginToYourAccountText();
		Assert.assertEquals(login, sheet2.getRow(3).getCell(7).toString());
		WebElement Email = lp.LoginEmailValidation();
		Email.sendKeys(sheet2.getRow(5).getCell(0).toString());
		WebElement Password = lp.LoginPasswordValidation();
		Password.sendKeys();
		lp.ClickOnLogginButton();
		String loginUser = lp.loginUserValiDation();
		Assert.assertEquals(loginUser, sheet2.getRow(8).getCell(0).toString());

	}
	@Test(priority=5)
	public void RegisterUserWithExistingEmail() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		String newUser= hp.NewUserSignUpValidation();
		Assert.assertEquals(newUser, sheet.getRow(1).getCell(2).toString());
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		WebElement name = lp.EnterName();
		name.sendKeys(sheet2.getRow(5).getCell(5).toString());
		WebElement pass = lp.EnterEmail();
		pass.sendKeys(sheet2.getRow(5).getCell(6).toString());
		lp.SignupButtonValidation();
		String signUpText = lp.existedErrorMessage();
		Assert.assertEquals(signUpText, sheet2.getRow(5).getCell(7).toString());
	}
	@Test(priority=6)
	public void ContactUsFormValidation() throws Exception {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.clickOnContactUs();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("ContactUsForm");
		Assert.assertEquals(driver.getCurrentUrl(), sheet3.getRow(1).getCell(0).toString());
		String getIn = cu.getInTouchText();
		Assert.assertEquals(getIn, sheet3.getRow(3).getCell(0).toString());
		lp.EnterName().sendKeys(sheet3.getRow(5).getCell(0).toString());
		WebElement contactUsEmail = cu.ContactUsEmailValidation();
		contactUsEmail.sendKeys(sheet3.getRow(5).getCell(1).toString());
		WebElement subject =cu.EnterSubjectText();
		subject.sendKeys(sheet3.getRow(5).getCell(2).toString());
		WebElement message = cu.EnterMessageText();
		message.sendKeys(sheet3.getRow(5).getCell(3).toString());
		cu.uplodeFileValidaton().sendKeys("C:\\Users\\user\\ChooseFile.txt");
	}
	@Test(priority=7)
	public void verifyTestCasePage() throws Exception {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.clickOnTestCases();
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(4).getCell(0).toString());

	}
	@Test(priority=8)
	public void verifyAllProductsAndProductDetailsPage() throws Exception {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.clickOnProducts();
		XSSFSheet sheet4 = ReadExcelFile.readExcelData("ProductsPageData");
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(1).getCell(0).toString());
		Assert.assertEquals(pp.AllProductsText(), sheet4.getRow(1).getCell(1).toString());
		List<WebElement> productsList =	pp.listOfProducts();
		for(int i=0;i<productsList.size();i++) {
			System.out.println(productsList.get(i).getText());
		}
		pp.clickOnfirstProduct();
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(3).getCell(0).toString());
		Assert.assertEquals(pp.productName(), sheet4.getRow(4).getCell(1).toString());
		Assert.assertEquals(pp.productCategory(), sheet4.getRow(5).getCell(1).toString());
		Assert.assertEquals(pp.productPrice(), sheet4.getRow(6).getCell(1).toString());
		Assert.assertEquals(pp.productAvailability(), sheet4.getRow(7).getCell(1).toString());
		Assert.assertEquals(pp.productCodition(), sheet4.getRow(8).getCell(1).toString());
		Assert.assertEquals(pp.productBrand(), sheet4.getRow(9).getCell(1).toString());
	}
	@Test(priority=9)
	public void VerifySearchedProduct() throws Exception {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.clickOnProducts();
		XSSFSheet sheet4 = ReadExcelFile.readExcelData("ProductsPageData");
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(1).getCell(0).toString());
		Assert.assertEquals(pp.AllProductsText(), sheet4.getRow(1).getCell(1).toString());
		pp.SearchBoxValidation().sendKeys(sheet4.getRow(11).getCell(1).toString());
		pp.clickOnSearchButton();
		Assert.assertEquals(pp.AllProductsText(), sheet4.getRow(12).getCell(1).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(13).getCell(1).toString());
	}
	@Test(priority=10)
	public void SuscriptionInHomePage() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.scrollDownToFooter();
		Assert.assertEquals(hp.SubscriptionText(), sheet.getRow(6).getCell(0).toString());
		hp.HompageEmailAdress().sendKeys(sheet.getRow(9).getCell(0).toString());
		hp.ClickOnArrowButton();
		Assert.assertEquals(hp.successfullySubscribe(), sheet.getRow(9).getCell(1).toString());
	}
	@Test(priority=11)
	public void verifySubscriptionInCartPage() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.clickOnCartPage();
		Assert.assertEquals(hp.SubscriptionText(), sheet.getRow(6).getCell(0).toString());
		hp.HompageEmailAdress().sendKeys(sheet.getRow(9).getCell(0).toString());
		hp.ClickOnArrowButton();
		Assert.assertEquals(hp.successfullySubscribe(), sheet.getRow(9).getCell(1).toString());
	}
	@Test(priority=12)
	public void addProductsInCart() throws InterruptedException  {
		XSSFSheet prodsheet = ReadExcelFile.readExcelData("ProductsPageData");
		hp.clickOnProducts();
		pp.ClickOnFirstProductAddToCart();		
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnViewCart();
		List<WebElement> CartProductNames = cp.cartProductNames();
		for(int i = 0,r = 29; i<CartProductNames.size();i++,r++) {
			Assert.assertEquals(CartProductNames.get(i).getText(), prodsheet.getRow(r).getCell(1).toString());
		}
		List<WebElement> CartProductPrices = cp.cartProductPrices();
		for(int i = 0,r = 29; i<CartProductPrices.size();i++,r++) {
			Assert.assertEquals(CartProductPrices.get(i).getText(), prodsheet.getRow(r).getCell(2).toString());
		}
		List<WebElement> CartProductQuantities = cp.cartProductQuantities();
		for(int i = 0,r = 29; i<CartProductQuantities.size();i++,r++) {
			Assert.assertEquals(CartProductQuantities.get(i).getText(), prodsheet.getRow(r).getCell(3).getRawValue());
		}
		for(int i = 0,r = 29; i<CartProductQuantities.size();i++,r++) {
			String[] pricesplit = CartProductPrices.get(i).getText().split(" ");
			int p1 = Integer.parseInt(pricesplit[1]);
			int q1 = Integer.parseInt(CartProductQuantities.get(i).getText());
			int t1 = p1*q1;
			Assert.assertEquals(t1, prodsheet.getRow(r).getCell(4).getNumericCellValue());
		}
	}

	@Test(priority=13)
	public void information() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet = ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.ClickOnAnyProduct();
		XSSFSheet sheet1 = ReadExcelFile.readExcelData("ProductPage");
		Assert.assertTrue(hp.currentURL().contains(sheet1.getRow(0).getCell(1).toString()));//chang
		cp.clearQuantity();
		cp.quantity();
		XSSFSheet sheet2 = ReadExcelFile.readExcelData("CartPage");
		cp.enterQuantity().sendKeys(sheet2.getRow(3).getCell(0).getRawValue());
		hp.clickOnAddToCartButton();	
	}
	@Test(priority=14)
	public void RegisterWhileCheckOut() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		pp.ClickOnFirstProductAddToCart();
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet1= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet1.getRow(5).getCell(0).toString());
		cp.clickOnProceedToCheckOut();
		cp.clickOnRegisterOrLoginButton();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		lp.EnterName().sendKeys(sheet2.getRow(1).getCell(0).toString());
		lp.EnterEmail().sendKeys(sheet2.getRow(1).getCell(1).toString());
		lp.SignupButtonValidation();
		lp.RadioButton();
		lp.PasswordText().sendKeys(sheet2.getRow(1).getCell(3).toString());
		lp.DayValidation();
		lp.monthValidation();
		lp.YearValidation();
		lp.SelectBox1();
		lp.SelectBox2();
		lp.FirstnameTextBox().sendKeys(sheet2.getRow(1).getCell(4).toString());
		lp.LastNameTextBox().sendKeys(sheet2.getRow(1).getCell(5).toString());
		lp.CompanyValidation().sendKeys(sheet2.getRow(1).getCell(6).toString());
		lp.Address1Validation().sendKeys(sheet2.getRow(1).getCell(7).toString());
		lp.Address2Validation().sendKeys(sheet2.getRow(1).getCell(8).toString());
		lp.EnterState().sendKeys(sheet2.getRow(3).getCell(0).toString());
		lp.EnterCity().sendKeys(sheet2.getRow(3).getCell(1).toString());
		lp.EnterZipCode().sendKeys(sheet2.getRow(3).getCell(2).toString());
		lp.EnterMobileNumber().sendKeys(sheet2.getRow(3).getCell(3).toString());
		lp.ClickOnCreateAccountButton();
		Assert.assertEquals(lp.AccountCreatedValidation(),sheet2.getRow(3).getCell(4).toString());
		lp.ClickOnContinueButton();
		Assert.assertEquals(lp.LoggedUserValidation(), sheet2.getRow(3).getCell(5).toString() );
		hp.clickOnCartPage();
		cp.clickOnProceedToCheckOut();
		Assert.assertEquals(cp.yourDeliveryAddressDtailsText(), cp.yourBillingAddressDtailsText());
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		cp.EnterAboutYourOrderText().sendKeys(sheet3.getRow(10).getCell(0).toString());
		cp.clickOnPlaceOrder();
		cp.EnterNameOnCard().sendKeys(sheet3.getRow(12).getCell(1).toString());
		cp.EnterCardNumber().sendKeys(sheet3.getRow(13).getCell(1).toString());
		cp.EnterCvc().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterMonth().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterYear().sendKeys(sheet3.getRow(15).getCell(1).toString());
		cp.clickOnPayAndConformOrder();
		Assert.assertEquals(cp.OrderSuccessfullyCompleatedText(), sheet3.getRow(18).getCell(0).toString());
		lp.ClickOnDelete();
		Assert.assertEquals(hp.currentURL(), sheet.getRow(20).getCell(0).toString());
		Assert.assertEquals(cp.AccountPerminentlyDeletedText(), sheet.getRow(22).getCell(0).toString());
		cp.clickOnContinue();

	}
	@Test(priority=15)
	public void rigisterBeforeCheckout() throws Exception  {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		Assert.assertEquals(hp.NewUserSignUpValidation(), sheet.getRow(1).getCell(2).toString() );
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		lp.EnterName().sendKeys(sheet2.getRow(1).getCell(0).toString());
		lp.EnterEmail().sendKeys(sheet2.getRow(1).getCell(1).toString());
		lp.SignupButtonValidation();
		lp.RadioButton();
		lp.PasswordText().sendKeys(sheet2.getRow(1).getCell(3).toString());
		lp.DayValidation();
		lp.monthValidation();
		lp.YearValidation();
		lp.SelectBox1();
		lp.SelectBox2();
		lp.FirstnameTextBox().sendKeys(sheet2.getRow(1).getCell(4).toString());
		lp.LastNameTextBox().sendKeys(sheet2.getRow(1).getCell(5).toString());
		lp.CompanyValidation().sendKeys(sheet2.getRow(1).getCell(6).toString());
		lp.Address1Validation().sendKeys(sheet2.getRow(1).getCell(7).toString());
		lp.Address2Validation().sendKeys(sheet2.getRow(1).getCell(8).toString());
		lp.EnterState().sendKeys(sheet2.getRow(3).getCell(0).toString());
		lp.EnterCity().sendKeys(sheet2.getRow(3).getCell(1).toString());
		lp.EnterZipCode().sendKeys(sheet2.getRow(3).getCell(2).toString());
		lp.EnterMobileNumber().sendKeys(sheet2.getRow(3).getCell(3).toString());
		lp.ClickOnCreateAccountButton();
		Assert.assertEquals(lp.AccountCreatedValidation(),sheet2.getRow(3).getCell(4).toString());
		lp.ClickOnContinueButton();
		Assert.assertEquals(lp.LoggedUserValidation(), sheet2.getRow(3).getCell(5).toString() );
		pp.ClickOnFirstProductAddToCart();
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet3.getRow(5).getCell(0).toString());
		cp.clickOnProceedToCheckOut();
		Assert.assertEquals(cp.yourDeliveryAddressDtailsText(), cp.yourBillingAddressDtailsText());
		cp.EnterAboutYourOrderText().sendKeys(sheet3.getRow(10).getCell(0).toString());
		cp.clickOnPlaceOrder();
		cp.EnterNameOnCard().sendKeys(sheet3.getRow(12).getCell(1).toString());
		cp.EnterCardNumber().sendKeys(sheet3.getRow(13).getCell(1).toString());
		cp.EnterCvc().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterMonth().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterYear().sendKeys(sheet3.getRow(15).getCell(1).toString());
		cp.clickOnPayAndConformOrder();
		Assert.assertEquals(cp.OrderSuccessfullyCompleatedText(), sheet3.getRow(18).getCell(0).toString());
		lp.ClickOnDelete();
		Assert.assertEquals(hp.currentURL(), sheet.getRow(20).getCell(0).toString());
		Assert.assertEquals(cp.AccountPerminentlyDeletedText(), sheet.getRow(22).getCell(0).toString());
		cp.clickOnContinue();

	}
	@Test(priority=16)
	public void LoginBeforeCheckout() throws InterruptedException{
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();	
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		Assert.assertEquals(lp.loginToYourAccountText(), sheet2.getRow(3).getCell(7).toString());
		lp.LoginEmailValidation().sendKeys(sheet2.getRow(5).getCell(0).toString());
		lp.LoginPasswordValidation().sendKeys(sheet2.getRow(5).getCell(1).toString());
		lp.ClickOnLogginButton();
		Assert.assertEquals(lp.LoggedUserValidation(), sheet2.getRow(8).getCell(0).toString());	
		pp.ClickOnFirstProductAddToCart();
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet3.getRow(5).getCell(0).toString());
		cp.clickOnProceedToCheckOut();
		Assert.assertEquals(cp.yourDeliveryAddressDtailsText(), cp.yourBillingAddressDtailsText());
		cp.EnterAboutYourOrderText().sendKeys(sheet3.getRow(10).getCell(0).toString());
		cp.clickOnPlaceOrder();
		cp.EnterNameOnCard().sendKeys(sheet3.getRow(12).getCell(1).toString());
		cp.EnterCardNumber().sendKeys(sheet3.getRow(13).getCell(1).toString());
		cp.EnterCvc().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterMonth().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterYear().sendKeys(sheet3.getRow(15).getCell(1).toString());
		cp.clickOnPayAndConformOrder();
		Assert.assertEquals(cp.OrderSuccessfullyCompleatedText(), sheet3.getRow(18).getCell(0).toString());
		lp.ClickOnDelete();
		Assert.assertEquals(hp.currentURL(), sheet.getRow(20).getCell(0).toString());
		Assert.assertEquals(cp.AccountPerminentlyDeletedText(), sheet.getRow(22).getCell(0).toString());
		cp.clickOnContinue();

	}
	@Test(priority=17)
	public void removeProductsFromCart() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		pp.ClickOnFirstProductAddToCart();
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet3.getRow(5).getCell(0).toString());
		cp.clickOnCartDeleteIcon();

	}

	@Test(priority=18)
	public void viewCategoryProducts() throws InterruptedException {
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		List<WebElement> CategoryList = hp.CategoryVisibilityText();
		for(int i = 0,r = 11 ;i<CategoryList.size(); i++,r++) {
			Assert.assertEquals(CategoryList.get(i).getText(), sheet.getRow(r).getCell(1).toString());
		}
		hp.clickOnWomenCategory();
		hp.ClickOnDress();
		//Assert.assertEquals(hp.currentURL(), sheet.getRow(19).getCell(0).toString());
		Assert.assertEquals(hp.WomenDressProductsText(), sheet.getRow(17).getCell(0).toString());
		hp.scrolltoKids();
		hp.clickOnMen();
		hp.clikOnAnyMenSubGategory();
		Assert.assertEquals(hp.WomenDressProductsText(), sheet.getRow(14).getCell(1).toString());
	}
	@Test(priority=19)
	public void CartProducts() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.clickOnProducts();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("ProductsPageData");
		List<WebElement> BrandsList =	hp.BrandNamesText();
		for(int i = 0,r = 20 ;i<BrandsList.size();i++,r++) {
			Assert.assertEquals(BrandsList, sheet2.getRow(r).getCell(1).toString());
		}
	}
	@Test(priority=20)
	public void searchProductsAndVerifyAfterLogin() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnProducts();
		XSSFSheet sheet4 = ReadExcelFile.readExcelData("ProductsPageData");
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(1).getCell(0).toString());
		Assert.assertEquals(pp.AllProductsText(), sheet4.getRow(1).getCell(1).toString());
		pp.SearchBoxValidation().sendKeys(sheet4.getRow(11).getCell(1).toString());
		pp.clickOnSearchButton();
		Assert.assertEquals(pp.AllProductsText(), sheet4.getRow(12).getCell(1).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(13).getCell(1).toString());
		pp.clickOnsearchedProductsToCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet3.getRow(5).getCell(0).toString());
		Assert.assertEquals(pp.FifthProductOfWinterTop(), sheet4.getRow(11).getCell(1).toString());
	}
	@Test(priority=21)
	public void addReviewOnProduct() throws InterruptedException {
		//handles.handledWindowSwitch();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		hp.clickOnProducts();
		XSSFSheet sheet4 = ReadExcelFile.readExcelData("ProductsPageData");
		Assert.assertEquals(driver.getCurrentUrl(), sheet4.getRow(1).getCell(0).toString());
		Assert.assertEquals(pp.AllProductsText(), sheet4.getRow(1).getCell(1).toString());
		pp.clickOnFirstViewProduct();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		pp.enterReviewName().sendKeys(sheet2.getRow(1).getCell(0).toString());
		pp.enterReviewEmail().sendKeys(sheet2.getRow(1).getCell(1).toString());
		pp.enterReview().sendKeys(sheet.getRow(26).getCell(0).toString());
		pp.clickOnSubmit();
		Assert.assertEquals(pp.thankYouReview().getText(), sheet4.getRow(28).getCell(1).toString());
	}

	@Test(priority=22)
	public void addToCartFromRecommendedItems() {
		hp.scrollBy();
		Assert.assertEquals(hp.recommmendedItemsText().getText(), "RECOMMENDED ITEMS");
		hp.ClickOnRecommendedItemAddToCart();
		pp.clickOnViewCart();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet3.getRow(5).getCell(0).toString());

	}
	@Test(priority=23)
	public void verifyAddressDetailsInCheckoutPage() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		Assert.assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(1).toString());
		hp.clickOnSignUpLogin();
		Assert.assertEquals(hp.NewUserSignUpValidation(), sheet.getRow(1).getCell(2).toString() );
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		lp.EnterName().sendKeys(sheet2.getRow(1).getCell(0).toString());
		lp.EnterEmail().sendKeys(sheet2.getRow(1).getCell(1).toString());
		lp.SignupButtonValidation();
		lp.RadioButton();
		lp.PasswordText().sendKeys(sheet2.getRow(1).getCell(3).toString());
		lp.DayValidation();
		lp.monthValidation();
		lp.YearValidation();
		lp.SelectBox1();
		lp.SelectBox2();
		lp.FirstnameTextBox().sendKeys(sheet2.getRow(1).getCell(4).toString());
		lp.LastNameTextBox().sendKeys(sheet2.getRow(1).getCell(5).toString());
		lp.CompanyValidation().sendKeys(sheet2.getRow(1).getCell(6).toString());
		lp.Address1Validation().sendKeys(sheet2.getRow(1).getCell(7).toString());
		lp.Address2Validation().sendKeys(sheet2.getRow(1).getCell(8).toString());
		lp.EnterState().sendKeys(sheet2.getRow(3).getCell(0).toString());
		lp.EnterCity().sendKeys(sheet2.getRow(3).getCell(1).toString());
		lp.EnterZipCode().sendKeys(sheet2.getRow(3).getCell(2).toString());
		lp.CountryDropDown();
		lp.EnterMobileNumber().sendKeys(sheet2.getRow(3).getCell(3).toString());
		lp.ClickOnCreateAccountButton();
		Assert.assertEquals(lp.AccountCreatedValidation(),sheet2.getRow(3).getCell(4).toString());
		lp.ClickOnContinueButton();
		Assert.assertEquals(lp.LoggedUserValidation(), sheet2.getRow(3).getCell(5).toString());
		pp.ClickOnFirstProductAddToCart();
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet3.getRow(5).getCell(0).toString());
		cp.clickOnProceedToCheckOut();
		List<WebElement> DeliveryAddressValidation = cp.deliveryAddressDetails();
		Assert.assertEquals(DeliveryAddressValidation.get(1).getText(),"Mrs."+" "+sheet2.getRow(1).getCell(4).toString()+" "+sheet2.getRow(1).getCell(5).toString());
		Assert.assertEquals(DeliveryAddressValidation.get(2).getText(),sheet2.getRow(1).getCell(6).toString());
		Assert.assertEquals(DeliveryAddressValidation.get(3).getText(),sheet2.getRow(1).getCell(7).toString());
		Assert.assertEquals(DeliveryAddressValidation.get(4).getText(),sheet2.getRow(1).getCell(8).toString());
		Assert.assertEquals(DeliveryAddressValidation.get(5).getText(),sheet2.getRow(3).getCell(1).toString()+" "+sheet2.getRow(3).getCell(0).toString()+" "+sheet2.getRow(3).getCell(2).toString());
		Assert.assertEquals(DeliveryAddressValidation.get(6).getText(),sheet2.getRow(3).getCell(8).toString());
		Assert.assertEquals(DeliveryAddressValidation.get(7).getText(),sheet2.getRow(3).getCell(3).toString());
		/*Billing Address Validation with Excel sheet data*/
		List<WebElement> BillingAddressValidation = cp.billingAddressDetails();
		Assert.assertEquals(BillingAddressValidation.get(1).getText(),"Mrs."+" "+sheet2.getRow(1).getCell(4).toString()+" "+sheet2.getRow(1).getCell(5).toString());
		Assert.assertEquals(BillingAddressValidation.get(2).getText(),sheet2.getRow(1).getCell(6).toString());
		Assert.assertEquals(BillingAddressValidation.get(3).getText(),sheet2.getRow(1).getCell(7).toString());
		Assert.assertEquals(BillingAddressValidation.get(4).getText(),sheet2.getRow(1).getCell(8).toString());
		Assert.assertEquals(BillingAddressValidation.get(5).getText(),sheet2.getRow(3).getCell(1).toString()+" "+sheet2.getRow(3).getCell(0).toString()+" "+sheet2.getRow(3).getCell(2).toString());
		Assert.assertEquals(BillingAddressValidation.get(6).getText(),sheet2.getRow(3).getCell(8).toString());
		Assert.assertEquals(BillingAddressValidation.get(7).getText(),sheet2.getRow(3).getCell(3).toString());
		lp.ClickOnDelete();
		lp.ClickOnContinue();
	}
	@Test(priority=24)
	public void downLoadInvoiceAfterPurchaseOrder() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		pp.ClickOnFirstProductAddToCart();
		pp.clickOnContinueShoppingButton();
		pp.clickOnSecondProductAddtoCart();
		pp.clickOnContinueShoppingButton();
		hp.clickOnCartPage();
		XSSFSheet sheet1= ReadExcelFile.readExcelData("CartPage");
		Assert.assertEquals(hp.currentURL(), sheet1.getRow(5).getCell(0).toString());
		cp.clickOnProceedToCheckOut();
		cp.clickOnRegisterOrLoginButton();
		XSSFSheet sheet2= ReadExcelFile.readExcelData("LoginPageData");
		lp.EnterName().sendKeys(sheet2.getRow(1).getCell(0).toString());
		lp.EnterEmail().sendKeys(sheet2.getRow(1).getCell(1).toString());
		lp.SignupButtonValidation();
		lp.RadioButton();
		lp.PasswordText().sendKeys(sheet2.getRow(1).getCell(3).toString());
		lp.DayValidation();
		lp.monthValidation();
		lp.YearValidation();
		lp.SelectBox1();
		lp.SelectBox2();
		lp.FirstnameTextBox().sendKeys(sheet2.getRow(1).getCell(4).toString());
		lp.LastNameTextBox().sendKeys(sheet2.getRow(1).getCell(5).toString());
		lp.CompanyValidation().sendKeys(sheet2.getRow(1).getCell(6).toString());
		lp.Address1Validation().sendKeys(sheet2.getRow(1).getCell(7).toString());
		lp.Address2Validation().sendKeys(sheet2.getRow(1).getCell(8).toString());
		lp.EnterState().sendKeys(sheet2.getRow(3).getCell(0).toString());
		lp.EnterCity().sendKeys(sheet2.getRow(3).getCell(1).toString());
		lp.EnterZipCode().sendKeys(sheet2.getRow(3).getCell(2).toString());
		lp.EnterMobileNumber().sendKeys(sheet2.getRow(3).getCell(3).toString());
		lp.ClickOnCreateAccountButton();
		Assert.assertEquals(lp.AccountCreatedValidation(),sheet2.getRow(3).getCell(4).toString());
		lp.ClickOnContinueButton();
		Assert.assertEquals(lp.LoggedUserValidation(), sheet2.getRow(3).getCell(5).toString() );
		hp.clickOnCartPage();
		cp.clickOnProceedToCheckOut();
		//Assert.assertEquals(cp.yourDeliveryAddressDtailsText(), cp.yourBillingAddressDtailsText());
		XSSFSheet sheet3= ReadExcelFile.readExcelData("CartPage");
		cp.EnterAboutYourOrderText().sendKeys(sheet3.getRow(10).getCell(0).toString());
		cp.clickOnPlaceOrder();
		cp.EnterNameOnCard().sendKeys(sheet3.getRow(12).getCell(1).toString());
		cp.EnterCardNumber().sendKeys(sheet3.getRow(13).getCell(1).toString());
		cp.EnterCvc().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterMonth().sendKeys(sheet3.getRow(14).getCell(1).toString());
		cp.EnterYear().sendKeys(sheet3.getRow(15).getCell(1).toString());
		cp.clickOnPayAndConformOrder();
		//Assert.assertEquals(cp.OrderSuccessfullyCompleatedText(), sheet3.getRow(18).getCell(0).toString());
		cp.clickOnDownloadInvoice();
		cp.isInvoiceDownloaded();
		lp.ClickOnDelete();
		lp.ClickOnContinue();
	}
	@Test(priority=25)
	public void verifyScrollUpUsingArrowAndVerifyScrollDownFunctionality() {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.scrollDownToFooter();
		Assert.assertEquals(hp.SubscriptionText(), sheet.getRow(6).getCell(0).toString());
		hp.ClickOnArrowAtBottomMoveToUpward();
		Assert.assertEquals(hp.fullFledgedAutomationText(), sheet.getRow(28).getCell(0).toString());
	}
	@Test(priority=26)
	public void verifyScrollUpWithoutArrowButtonAndScrollDownFunctionality() throws InterruptedException {
		String HomepageText = hp.verifyHomePage();
		XSSFSheet sheet= ReadExcelFile.readExcelData("HomePageData");
		Assert.assertEquals(HomepageText, sheet.getRow(1).getCell(0).toString());
		hp.scrollDownToFooter();
		Assert.assertEquals(hp.SubscriptionText(), sheet.getRow(6).getCell(0).toString());
		hp.scrollUpPageToTop();

	}

}


