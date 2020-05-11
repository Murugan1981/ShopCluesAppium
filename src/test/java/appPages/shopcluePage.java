package appPages;


import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

import appSteps.baseTestDriver;


public class shopcluePage extends baseTestDriver
{
	private AppiumDriver driver;
	
public shopcluePage(AppiumDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

//Object Reference  -->	// 	tv - textview
						//	et - editText
						//	iv - ImageView
						//	cb - Combobox
						//  btn - button

//Landing Page	- Menu Items like Home, Categories, Make Money, Offers and Account
	public By lp_Menu_tv_Account_xpath = By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_bottom_nav_account']");				//	--> Account
	public By lp_Menu_tv_Home_xpath = By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_bottom_nav_home']");					//	--> Home		
	public By lp_Menu_tv_Categories_xpath = By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_bottom_nav_cat']");				//	--> Categories
	public By lp_Menu_tv_MakeMoney_xpath = By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_bottom_nav_refer']");				//	--> Make Money
	public By lp_Menu_tv_Offers_xpath = By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/rl_wishlist']");							//	-->	Offers 
		
	// Home Page --> My Account --> SignIn to shop clues.
	public By signIn_et_EnterMobileOrEmail_xpath=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_mobile_number_or_emailid']");						//	Enter your mobile number or email id
	public By signIn_tv_LoginviaOTP_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_SignIn_via_otp']");											//	Login via OTP
	public By signIn_tv_LoginviaPassword_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_SignIn_via_password' or @text='Login via Password']");									//	Login via Password
	public By signIn_tv_LoginviaPassword_id=By.id("com.shopclues:id/tv_SignIn_via_password");
	public By signIn_et_Enteryourpassword_xpath=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_password']");											//	Enter your password
	public By signIn_iv_ViewPassword_xpath=By.xpath("//android.widget.ImageView[@resource-id='com.shopclues:id/iv_show_hide_password']");								//	Image [view password]
	public By signIn_tv_ForgotPassword_xpath=By.xpath("//[@resource-id='com.shopclues:id/tv_forgot_password']");										//	Forgot Password?
	public By signIn_tv_LoginBtn_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_login']");														//	Login Button
	public By signIn_tv_DisplayedEmail_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_email']");														//	Login Button
	
// Home Page --> My Account --> Sign Up to shop clues.
	public By signUp_et_EnterUrEmailId_xpath=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_email_id']");														//	Enter your Email ID
	public By signUp_et_EnterUrMobile_xpath=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_mobile_number']");														//	Enter your Email ID
	public By signUp_cb_RegWithPassword_xpath=By.xpath("//android.widget.CheckBox[@resource-id='com.shopclues:id/cb_register_with_password']");														//	Combo box - Register with Password
	public By signUp_et_EnterPassword_xpath=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_password']");														//	Edit- Enter your Password	
	public By signUp_iv_ViewPassword_xpath=By.xpath("//android.widget.ImageView[@resource-id='com.shopclues:id/iv_show_hide_password']");														//	Image View- Show Password
	public By signUp_tv_Register_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_register']");														//	Image View- Show Password
		
//Search Product	
	public By lp_ib_SearchProduct_xpath=By.xpath("//android.widget.ImageButton[@index=1]");									// Search Product
	public By Search_et_SearchingFor_xpath=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_search' or contains(@text,'What are you looking for')]");									// Search Product
	public By Search_et_SearchingFor_xpath1=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_search']");									// Search Product
	public By Search_tv_Filter_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_filter' or @text='Filter']");									// Search Product
	public By Search_tv_FilterBySCSurety_xpath=By.xpath("//android.widget.TextView[@text='Shopclues Surety']");									// Filter By Shopclues Surety
	public By Search_tv_FilterBySCSurety_SelectSurety_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_filter_item_name' or @text='Surety']");									// Filter By Shopclues Surety
	public By Search_tv_FilterByCategories_xpath=By.xpath("//android.widget.ListView[@resource-id='com.shopclues:id/lv_filter_list']//android.widget.RelativeLayout");	
	public By Search_tv_FilterByCategoryItem_xpath=By.xpath("//android.widget.ListView[@resource-id='com.shopclues:id/lv_filter_list']//android.widget.RelativeLayout//android.widget.TextView");	
	public By Search_tv_FilterByCategoryThermometer=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_name' or @text='Thermometers']");	
	public By Search_tv_FilterByProductRat_xpath=By.xpath("//android.widget.TextView[@text='Product Rating']");									// Filter By Product Rating
	public By Search_tv_FilterByMerchantRat_xpath=By.xpath("//android.widget.TextView[@text='Merchant Rating']");									// Filter By Product Rating
	public By Search_tv_FilterByDiscountRat_xpath=By.xpath("//android.widget.TextView[@text='Discount']");									// Filter By Discount Rating
	public By Search_tv_FilterByThermometer_xpath=By.xpath("//android.widget.TextView[@text='Thermometer Type']");									// Filter By Discount Rating	
	public By Search_tv_ThermoType_Digital_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_filter_item_name' and @text='Digital']");									// Thermometer Type - Digital
	public By Search_tv_ThermoType_IR_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_filter_item_name' and @text='Infrared']");									// Thermometer Type - IR
	public By Search_tv_ThermoType_Mercury_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_filter_item_name' and @text='Mercury']");									// Thermometer Type - IR	
	public By Search_tv_FilterByBrandRat_xpath=By.xpath("//android.widget.TextView[@text='Brand']");									// Filter By Brand Rating
	public By Search_tv_FilterByPriceRat_xpath=By.xpath("//android.widget.TextView[@text='Price']");									// Filter By Price Rating	
	public By Search_tv_FilterByRat1_xpath=By.xpath("//android.widget.TextView[@text='1 & above']");									// Filter By Rating 1&Above
	public By Search_tv_FilterByRat2_xpath=By.xpath("//android.widget.TextView[@text='2 & above']");									// Filter By Rating 2&Above
	public By Search_tv_FilterByRat3_xpath=By.xpath("//android.widget.TextView[@text='3 & above']");									// Filter By Rating 3&Above
	public By Search_tv_FilterByRat4_xpath=By.xpath("//android.widget.TextView[@text='4 & above']");									// Filter By Rating 4&Above	
	public By Search_tv_Discount20_xpath=By.xpath("//android.widget.TextView[@text='Less than 20%']");									// Filter By Discount	Less than 20% 
	public By Search_tv_Discount40_xpath=By.xpath("//android.widget.TextView[@text='21 - 40%']");									// Filter By Discount	21 - 40% 
	public By Search_tv_Discount60_xpath=By.xpath("//android.widget.TextView[@text='41 - 60%']");									// Filter By Discount	41 - 60% 
	public By Search_tv_Discount80_xpath=By.xpath("//android.widget.TextView[@text='61 - 80%']");									// Filter By Discount 	61 - 80% 	
	public By Search_btn_APPLYFILTER=By.xpath("//android.widget.Button[@resource-id='com.shopclues:id/btn_apply_filter' or @text='Apply Filter']");									// APPLY FILTER
	public By Search_tv_CategoryName=By.xpath("//android.widget.Button[@resource-id='com.shopclues:id/tv_name']");									// Category Name
	
// Click on My Account 	
	public By myAcct_btn_SignIn_xpath=By.xpath("//android.widget.Button[@resource-id='com.shopclues:id/btn_header_signin']");														//	SignIn
	public By myAcct_btn_SignUp_xpath=By.xpath("//android.widget.Button[@resource-id='com.shopclues:id/btn_header_signup']");														//	SignUp
	public By myAcct_tv_ReferNow_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_refer_now']");														//	ReferNow
	public By myAcct_tv_MyOrders_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='My Orders']");									//	My Orders
	public By myAcct_tv_Wishlist_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Wishlist']");										// 	Wishlist	
	public By myAcct_tv_VIPClub_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='VIP Club']");										//	VIP Club
	public By myAcct_tv_MyGoodybag_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='My Goodybag']");									//	My Goodybag	
	public By myAcct_tv_MyCluesBucks_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='My CluesBucks']");									//	My CluesBucks	
	public By myAcct_tv_ReferEarn_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Refer & Earn']");									//	Refer & Earn	
	public By myAcct_tv_MyProfile_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='My Profile']");									//	My Profile	
	public By myAcct_tv_Location_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Location']");									//	Location	
	public By myAcct_tv_RateYourPurchase_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Rate Your Purchase']");									//	Rate Your Purchase	
	public By myAcct_tv_HelpSupport_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Help & Support']");									//	Help & Support	
	public By myAcct_tv_RatetheApp_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Rate the App']");									//	Rate the App	
	public By myAcct_tv_Legal_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='Legal']");									//
	public By myAcct_tv_More_xpath=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_title' and @text='More']");									//

// Product and its Details	
	public By psd_img_SelectprdByPrdImage=By.xpath("//android.widget.ImageView[@resource-id='com.shopclues:id/img_product_image']");		//	Select Product by clicking on Image
	public By psd_tv_SelectprdByPrdName=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_product_name']");		//	Select Product by clicking on Image	
	public By psd_tv_ProductScrollView=By.xpath("//android.widget.ScrollView[@resource-id='com.shopclues:id/nestedscrollview']");		//	Select Product by clicking on Image
	public By psd_btn_AddToCart=By.xpath("//android.widget.Button[@resource-id='com.shopclues:id/btn_add_to_cart' or @text='ADD TO CART']");		//	ADD TO CART
	public By psd_btn_GoToCart=By.xpath("//android.widget.Button[@resource-id='com.shopclues:id/btn_buynow' or @text='GO TO CART']");		//	ADD TO CART
	public By psd_tv_PlaceOrder=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_place_order' or @text='Place Order']");		//	Place Order
	public By psd_et_Addr_Pincode=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_pincode' or @text='Enter Pincode']");		//	PinCode
	public By psd_et_Addr_PhoneNumber=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_contact' or @text='Phone Number']");		//	Phone Number
	public By psd_et_Addr_Address1=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_address1' or @text='House/Plot No']");		//	
	public By psd_et_Addr_Address2=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_address2' or @text='Street/Locality/Area']");		//	
	public By psd_et_Addr_City=By.xpath("//android.widget.EditText[@resource-id='com.shopclues:id/et_city' or @text='City']");		//	
	public By psd_et_Addr_TypeHome=By.xpath("//android.widget.RadioButton[@resource-id='com.shopclues:id/rb_home' or @text='Home']");		//	
	public By psd_et_Addr_TypeOffice=By.xpath("//android.widget.RadioButton[@resource-id='com.shopclues:id/rb_office' or @text='Office']");		//	
	public By psd_et_Addr_TypeOther=By.xpath("//android.widget.RadioButton[@resource-id='com.shopclues:id/rb_other' or @text='Other']");		//		
	public By psd_tv_Addr_SaveContinue=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_save' or @text='Save & Continue']");		//			
	public By psd_tv_PlaceOrderAndPayNow=By.xpath("//android.widget.TextView[@resource-id='com.shopclues:id/tv_pay_now' or @text='Place Order']");		//	
	public By psd_tv_SelectPaymentMethod=By.xpath("//android.widget.TextView[@text='Select Payment Mode']");		//

}
