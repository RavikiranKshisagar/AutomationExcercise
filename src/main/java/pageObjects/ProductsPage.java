package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	JavascriptExecutor js; Actions act; WebDriverWait wait;
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
		js= (JavascriptExecutor)driver;	
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	@FindBy(xpath = "//h2[@class='title text-center']") private WebElement AllProducts;
	@FindBy(xpath = "//div[@class='features_items']/div") private List<WebElement> ProductsList;
	@FindBy(xpath = "//div[@class='features_items']/div[2]/div/div[2]/ul/li/a") private WebElement FirstProduct;
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]") private WebElement AllProductsFirstProduct;
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/div[3]") private WebElement AllProductsSecondProduct;
	@FindBy(xpath = "//div[@class='product-details']/div[2]/div/h2") private WebElement EnterProductName;
	@FindBy(xpath = "//div[@class='product-details']/div[2]/div/p[1]") private WebElement Category;
	@FindBy(xpath = "//div[@class='product-details']/div[2]/div/span/span")	private WebElement Price;
	@FindBy(xpath = "//div[@class='product-details']/div[2]/div/p[2]")	private WebElement Availability;
	@FindBy(xpath = "//div[@class='product-details']/div[2]/div/p[3]")	private WebElement Condition;
	@FindBy(xpath = "//div[@class='product-details']/div[2]/div/p[4]")	private WebElement Brand;
	@FindBy(id = "search_product") private WebElement SearchProductBox;
	@FindBy(xpath = "//div[@class='product-image-wrapper']/div/div/p") private List<WebElement> ProductNamesRandomly;
	@FindBy(id = "submit_search") private WebElement SearchButton;
	@FindBy(xpath = "//div[@class='features_items']/h2") private WebElement SearchedProducts;
	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']") private WebElement ContinueShoppingButton;
	@FindBy(xpath = "//u[text()='View Cart']") private WebElement ViewCart;
	@FindBy(xpath = "//div[@class='product-information']") private WebElement FirstProductDetails;
	@FindBy(xpath = "//div[@class='brands-name']/ul/li") private List<WebElement> BrandNames;
	@FindBy(xpath = "//div[@class='overlay-content']/a[@data-product-id='1']") private WebElement FirstProductAddtoCart;
	@FindBy(xpath = "(//a[text()='View Product'])[1]") private WebElement FirstProductViewProduct;
	@FindBy(xpath = "//div[@class='overlay-content']/a[@data-product-id='2']") private WebElement SecondProductAddtoCart;
	@FindBy(xpath = "//div[@class='overlay-content']//p[contains(text(),'Blue Top')] ") private WebElement FirstProductName;
	@FindBy(xpath = "//div[@class='overlay-content']//p[contains(text(),'Men Tshirt')]") private WebElement SecondProductName;
	@FindBy(xpath = "(//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[1]//div[1]//a)[1]") private WebElement AddSearchedProductsToCart;
	@FindBy(xpath = "//table[@class='table table-condensed']/tbody/tr/td[2]") private WebElement FifthProductOfWinterTop;
	@FindBy(xpath = "//a[text()='Write Your Review']") private WebElement WriteYourReviewText;
	@FindBy(id = "name") private WebElement ReviewName;
	@FindBy(id = "email") private WebElement ReviewEmail;
	@FindBy(id = "review") private WebElement AddReviewTextArea;
	@FindBy(id = "button-review") private WebElement ReviewSubmit;
	@FindBy(xpath = "//span[text()='Thank you for your review.']") private WebElement ThankyouReviewText;
	public String AllProductsText() {
		return AllProducts.getText();
	}
	public List<WebElement> listOfProducts(){
		return ProductsList;
	}
	public void clickOnfirstProduct() throws Exception {
		FirstProduct.click();
		Thread.sleep(4000);
	}
	public String productName() {
		return EnterProductName.getText();
	}
	public String productCategory() {
		return Category.getText();
	}
	public String productPrice() {
		return Price.getText();
	}
	public String productAvailability() {
		return Availability.getText();
	}
	public String productCodition() {
		return Condition.getText();
	}
	public String productBrand() {
		return Brand.getText();
	}
	public WebElement SearchBoxValidation() {
		return SearchProductBox;
	}
	public void clickOnSearchButton() {
		SearchButton.click();
		//js.executeScript("arguments[0].scrollintoView()", SearchButton);
	}
	public String SearchedProductText() {
		return	SearchedProducts.getText();
	}
	public List<WebElement> BrandNamesText(){
		return BrandNames;
	}
	public void ClickOnFirstProductAddToCart() throws InterruptedException {
		js.executeScript("window.scrollBy(0,500)", "");
		//js.executeScript("arguments[0].scrollIntoView();", FirstProductViewProduct);
		wait.until(ExpectedConditions.visibilityOf(FirstProductViewProduct));
		act.moveToElement(AllProductsFirstProduct).perform();
		FirstProductAddtoCart.click();		
		Thread.sleep(1000);
	}
	public void clickOnContinueShoppingButton() {
		ContinueShoppingButton.click();
	}
	public void clickOnSecondProductAddtoCart() throws InterruptedException {
		//js.executeScript("arguments[0].scrollIntoView()", FirstProductViewProduct);
		act.moveToElement( AllProductsSecondProduct).perform();
		SecondProductAddtoCart.click();
		Thread.sleep(1000);
	}
	public void clickOnViewCart() {
		ViewCart.click();
	}
	public void clickOnsearchedProductsToCart() {
		AddSearchedProductsToCart.click();
	}
	public WebElement FifthProductOfWinterTop() {
		return FifthProductOfWinterTop;

	}
	public String WriteYOurReviewText() {
		return WriteYourReviewText.getText();
	}
	public WebElement enterReview() {
		return AddReviewTextArea;
	}
	public void clickOnSubmit() {
		js.executeScript("arguments[0].scrollIntoView();", ReviewSubmit);
		js.executeScript("arguments[0].click()", ReviewSubmit);
	}
	public void clickOnFirstViewProduct() {
		js.executeScript("arguments[0].scrollIntoView();", FirstProductViewProduct);
		FirstProductViewProduct.click();
	}
	public WebElement enterReviewName() {
		js.executeScript("arguments[0].scrollIntoView();", ReviewSubmit);
		return ReviewName;
	}
	public WebElement enterReviewEmail() {
		return ReviewEmail;
	}
	public WebElement thankYouReview() {
		return ThankyouReviewText;
	}
}