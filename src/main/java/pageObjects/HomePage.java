package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Random;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
	WebDriver driver;JavascriptExecutor js;WebDriverWait wait;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js= (JavascriptExecutor)driver;	
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li") private List<WebElement> HomePageElements;
	@FindBy(xpath = "(//div[@id='slider-carousel']//h1)[1]") private WebElement HomePageText;
	@FindBy(xpath = "//div[@class='signup-form']/h2") private WebElement NewUserSignUp;
	@FindBy(xpath = "//div[@class='single-widget']/h2") private WebElement Subscription;
	@FindBy(id = "success-subscribe") private WebElement SubScriptionMeaasge;
	@FindBy(id = "susbscribe_email") private WebElement YourEmailAddress;
	@FindBy(id = "subscribe") private WebElement ArrowButton;

	@FindBy(xpath = "//div[@class='product-image-wrapper']/div[2]") private List<WebElement> AnyProduct;
	@FindBy(xpath = "//button[@class='btn btn-default cart']") private WebElement AddToCartButton;
	@FindBy(xpath = "//div[@class='features_items']/div[2]/div/div/div/a") private WebElement HomePageAddtoCart;
	@FindBy(xpath = "//div[@class='product-information']") private WebElement ProductInformation;
	@FindBy(xpath="//div[@id='accordian']//h4") private List<WebElement> CategoryVisibility;
	@FindBy(xpath = "(//a[@data-toggle='collapse'])[1]") private WebElement WomenCategory;
	@FindBy(xpath = "//a[@href='/category_products/1']") private WebElement Dress;
	@FindBy(xpath = "//h2[text()='Women - Dress Products']") private WebElement WomenDressTex;
	@FindBy(xpath = "//a[@href='#Men']") private WebElement Men;
	@FindBy(xpath = "//div[@id='Men']//ul/li") private List<WebElement> MenSubGategory;
	@FindBy(xpath = "//a[text()='Jeans ']") private WebElement MenJeans;
	@FindBy(xpath = "//h2[text()='Brands']") private WebElement BrandsText;
	@FindBy(xpath = "//div[@class='brands-name']/ul/li") private List<WebElement> BrandNames;
	@FindBy(id = "Kids") private WebElement Kids;
	@FindBy(xpath = "//h2[text()='recommended items']") private WebElement RecommendedItemsText;
	@FindBy(xpath = "(//div[@class='productinfo text-center']/a[@data-product-id='1'])[2]") private WebElement RecommentedProductAddtOCart;
	@FindBy(id = "scrollUp") private WebElement ArrowMoveToUpward;
	@FindBy(xpath = "(//h2[text()='Full-Fledged practice website for Automation Engineers'])[1]") private WebElement FullFledgedAutomationText;


	public String currentURL() {
		return driver.getCurrentUrl();
	}	
	public List<WebElement> ListOfHomePageElements() {
		return HomePageElements;
	}	
	public String verifyHomePage() {
		return HomePageText.getText();
	}	
	public void clickOnProducts() throws InterruptedException {
		for(int i = 0; i<HomePageElements.size();i++) {
			if(HomePageElements.get(i).getText().equals(" Products")) {
				HomePageElements.get(i).click();
				wait.until(ExpectedConditions.visibilityOf(Men));
			}		
		}
	}
	public void clickOnSignUpLogin() {
		for(int i=0;i<HomePageElements.size();i++) {
			if(	HomePageElements.get(i).getText().equals("Signup / Login")) {
				HomePageElements.get(i).click();
				js.executeScript("arguments[0].scrollintoView", HomePageElements);
			}
		}
	}
	public void clickOnTestCases() throws InterruptedException {
		for(int i=0;i<HomePageElements.size();i++) {
			if(HomePageElements.get(i).getText().equals("Test Cases")) {
				HomePageElements.get(i).click();
				Thread.sleep(3000);
			}
		}
	}
	public void clickOnContactUs() {
		for(int i=0;i<HomePageElements.size();i++) {
			if(HomePageElements.get(i).getText().equals("Contact us")) {
				HomePageElements.get(i).click();
			}
		}		
	}
	public void clickOnCartPage() {
		for(int i=0;i<HomePageElements.size();i++) {
			if(HomePageElements.get(i).getText().equals("Cart")) {
				HomePageElements.get(i).click();
			}
		}	
	}
	public String NewUserSignUpValidation() {
		return NewUserSignUp.getText();

	}
	public void scrollDownToFooter() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
	}
	public String SubscriptionText() {
		return Subscription.getText();
	}
	public WebElement HompageEmailAdress() {
		return YourEmailAddress;
	}
	public void ClickOnArrowButton() {
		ArrowButton.click();
	}
	public String successfullySubscribe() {
		return SubScriptionMeaasge.getText();
	}
	public void ClickOnAnyProduct()  {
		Random rand = new Random();
		AnyProduct.get(rand.nextInt(AnyProduct.size())).click();
	}
	public String ProductDetails() {
		return ProductInformation.getText();
	}
	public void clickOnAddToCartButton() {
		AddToCartButton.click();
	}
	public void ClickOnHomePageAddtoCart() {
		HomePageAddtoCart.click();
	}
	public List<WebElement> CategoryVisibilityText() {
		return CategoryVisibility;
	}
	public void clickOnWomenCategory()  {
		js.executeScript("arguments[0].scrollIntoView()",WomenCategory );
		js.executeScript("arguments[0].click()",WomenCategory );
	}
	public void ClickOnDress()  {
		wait.until(ExpectedConditions.visibilityOf(Dress));
		js.executeScript("arguments[0].click()",Dress);
	}
	public String WomenDressProductsText() {
		return WomenDressTex.getText();
	}
	public void clickOnMen() {
		js.executeScript("arguments[0].click()",Men );
		wait.until(ExpectedConditions.visibilityOfAllElements(MenSubGategory));
	}
	public void scrolltoKids() {
		js.executeScript("arguments[0].scrollIntoView", Kids);
	}
	public void clikOnAnyMenSubGategory() {
		js.executeScript("arguments[0].click()", MenJeans);
	}
	public List<WebElement> BrandNamesText() {
		js.executeScript("arguments[0].scrollIntoView()", BrandsText);
		return BrandNames;
	}

	public void clickOnAnyBrand() {
		Random rand = new Random();
		BrandNames.get(rand.nextInt(BrandNames.size())).click();
	}
	public WebElement recommmendedItemsText() {
		return RecommendedItemsText;
	}
	public void scrollBy() {
		js.executeScript("window.scrollBy(0,7500)", "");
	}
	public void ClickOnRecommendedItemAddToCart() {
		RecommentedProductAddtOCart.click();
	}
	public void ClickOnArrowAtBottomMoveToUpward() {
		ArrowMoveToUpward.click();
	}
	public String fullFledgedAutomationText(){
		return FullFledgedAutomationText.getText();
	}
	public void scrollUpPageToTop() throws InterruptedException {
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
}


