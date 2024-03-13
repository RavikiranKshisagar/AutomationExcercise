package pageObjects;
import java.io.File;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver; JavascriptExecutor js;
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor)driver;
	}
	@FindBy(xpath = "//a[@class='btn btn-default check_out']") private WebElement ProceedToCheckout;
	@FindBy(xpath = "//div[@class='modal-content']/div[2]/p[2]/a") private WebElement RegisterOrloginButton;
	@FindBy(id = "quantity") private WebElement Quantity;
	@FindBy(id = "address_delivery") private WebElement YourDeliveryAddressDetails;
	@FindBy(id = "address_invoice") private WebElement YourBillingAddressDetails;
	@FindBy(xpath = "//textarea[@name='message']") private WebElement AboutOrderTextBox;
	@FindBy(xpath = "//a[text()='Place Order']") private WebElement PlaceOrder;
	@FindBy(xpath = "//input[@name='name_on_card']") private WebElement NameOnCard;
	@FindBy(xpath = "//input[@name='card_number']") private WebElement CardNumber;
	@FindBy(xpath = "//input[@name='cvc']") private WebElement Cvc;
	@FindBy(xpath = "//input[@name='expiry_month']") private WebElement Month;
	@FindBy(xpath = "//input[@name='expiry_year']") private WebElement Year;
	@FindBy(id = "submit") private WebElement PayAndConformOrder;
	@FindBy(xpath = "(//div[@class='alert-success alert'])[1]") private WebElement OrderSuccessFullyCompleatedText;
	@FindBy(xpath = "//a[text()='Continue']") private WebElement Continue;
	@FindBy(xpath = "(//table[@class='table table-condensed']/tbody/tr/td[6])[1]") private WebElement CartDelete;
	@FindBy(xpath = "//p[text()='Your account has been permanently deleted!']") private WebElement AccountePermenentlyDeleted;
	@FindBy(xpath = "//table[@class='table table-condensed']/tbody/tr/td[2]/h4") private List<WebElement> CartProductNames;
	@FindBy(xpath = "//table[@class='table table-condensed']/tbody/tr/td[3]" ) private List<WebElement> CartProductPrices;
	@FindBy(xpath = "//table[@class='table table-condensed']/tbody/tr/td[4]" ) private List<WebElement> CartProductQuantitites;
	@FindBy(xpath = "//ul[@id='address_delivery']/li") private List<WebElement> DeliveryAddress;
	@FindBy(xpath = "//ul[@id='address_invoice']/li") private List<WebElement> BillingAddress;
	@FindBy(xpath = "//a[text()='Download Invoice']") private WebElement DownloadInvoice;
	
	public void quantity() {
		Quantity.click();
	}
	public void clearQuantity() {
		Quantity.clear();
	}
	public WebElement enterQuantity() {
		return Quantity;
	}
	public void clickOnProceedToCheckOut() {
		ProceedToCheckout.click();
	}
	public void clickOnRegisterOrLoginButton() {
		RegisterOrloginButton.click();
	}
	public String yourDeliveryAddressDtailsText() {
	return	YourDeliveryAddressDetails.getText();
	}
	public String yourBillingAddressDtailsText() {
		return	YourBillingAddressDetails.getText();
		}
	public WebElement EnterAboutYourOrderText() {
		return AboutOrderTextBox;
	}
	public void clickOnPlaceOrder() {
		PlaceOrder.click();
	}
	public WebElement EnterNameOnCard() {
		return NameOnCard;
	}
	public WebElement EnterCardNumber() {
		return CardNumber;
	}
	public WebElement EnterCvc() {
		return Cvc;
	}
	public WebElement EnterMonth() {
		return Month;
	}
	public WebElement EnterYear() {
		return Year;
	}
	public void clickOnPayAndConformOrder() {
		PayAndConformOrder.click();
	}
	public String OrderSuccessfullyCompleatedText() {
		return OrderSuccessFullyCompleatedText.getText();
	}
	public String AccountPerminentlyDeletedText() {
		return AccountePermenentlyDeleted.getText();
	}
	public void clickOnContinue() {
		Continue.click();
	}
	
	public void clickOnCartDeleteIcon() {
		CartDelete.click();
	}
	public List<WebElement> cartProductNames() {
		return CartProductNames;
	}
	public List<WebElement> cartProductPrices() {
		return CartProductPrices;
	}
	public List<WebElement> cartProductQuantities() {
		return CartProductQuantitites;
	}
	public List<WebElement> deliveryAddressDetails() {
		return DeliveryAddress;
	}
	public List<WebElement> billingAddressDetails() {
		return BillingAddress;
	}
	public void clickOnDownloadInvoice() {
		js.executeScript("arguments[0].click()", DownloadInvoice);
	}
	public void isInvoiceDownloaded() {
		String Home = System.getProperty("user.home");
		System.out.println(Home);
		File path = new File(Home+"\\Downloads\\");
		File[] FolderContent = path.listFiles();
		for(File file:FolderContent) {
			if(file.getName().equals("invoice")) {
				System.out.println("File Downloaded");
				break;
			}
		}
	}
	
}


