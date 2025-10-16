package pages;

import java.nio.file.Paths;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddLandLeadPage {
	private Page page;
	public String ExpectedLocationLink;
	// Locators 
	private String Territory = "#territory";
	private String FirstName = "#firstName";
	private String LastName = "#lastName";
	private String Email = "#email";
	private String LeadcountryCode = "(//div[@aria-label='Select Country Code'])[1]";
    private String searchCountryCode = "(//input[@aria-label='Search for a country'])";
    private String clickSearchedFirstOption = "//li[@role='option']";
	private String Phone = "#phoneNumber"; 
	private String BrokerName = "#agentName";
	private String BrokercountryCode = "(//div[@aria-label='Select Country Code'])[2]";
	private String BrokerPhone = "#agentPhoneNumber";
	private String Location = "//button[contains(text(),'Click to Select Location')]";
	private String SearchLocation = "//input[@placeholder='Enter location']";
	private String EnterLocationLink = "//input[@placeholder='Enter Google Maps Location Link']";
	private String PinYourLocationBtn ="//button[@class='px-4 py-2 bg-black text-white rounded w-1/2']";
	private String LandType = "#landType";
	private String TP_Name = "#tpName";
	private String TP_number = "#tpNumber";
	private String Plot_FP_number = "#fpNumber";
	private String SurveyNumber = "#surveyNumber";
	private String AreaType = "#areaType";
	private String PlotArea = "#landArea";
	private String PropertyCard = "(//label[@for='propertyCard'])[2]";
	private String SatBara = "(//label[@for='satBar'])[2]";
	private String Partplan = "(//label[@for='partPlan'])[2]";
	private String ZoningCertificate = "(//label[@for='zoningCertificate'])[2]";
	private String LandZone = "#zone";
	private String TypeOFProposal = "#landProposal";
	private String LandJantriValue = "#jantriValue";
	private String CancelBtn = "(//button[@type='button'])[5]";
	private String SubmitBtn = "//button[@type='submit']";
	private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
    private String selectFundOption = "//div[text()='Land']";
    private String addLeadBtn = "//button[normalize-space()='+ Add Lead']";
//    validation in lead card
    private String LeadNameInCard = "(//h3[@class='font-semibold text-lg text-gray-800 break-words'])[1]";
    private String fullDetailsText = "//p[contains(text(), 'Mobile no')]";
//    validation in history
    private String FirstNameinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[1]";
    private String LastNameinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[2]";
    private String EmailinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[3]";
    private String CountryCodeinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[4]";
    private String PhoneinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[5]";
    private String UserTypeinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[6]";
    private String BrokerNameinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[7]";
    private String BrokercountrycodeinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]";
    private String BrokerPhoneinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]";
    // private String LocationinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[10]";
    private String LandTypeinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[11]";
    private String AreainHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[12]";
    private String AreaTypeinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[13]";
    private String propertyCardinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[14]";
    private String satBarinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[15]";
    private String partPlaninHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[16]";
    private String zoningCertificateinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[17]";
    private String landZoneinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[18]";
    private String landForinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[19]";
    private String jantriValueinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[20]";
    private String surveyNumberinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[21]";
    private String tpNameinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[22]";
    private String tpNoinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[23]";
    private String fpNumberinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[24]";
    // private String sourceNameinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[25]";
    // private String subSourceNameinHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[26]";
    
    
    
	 public AddLandLeadPage(Page page) {
	        this.page = page;
	    }
	 
	 public void VerifyLeadbasicDetails(String Territory, String Fname, String Lname,
			 String email, String LeadCountryCode, String phone, String area, String plot, String land, 
			 String proposal, String jantri) throws InterruptedException {
		 page.click("//div[contains(text(), 'Re-Inquiry')]");
		 page.click("//div[contains(text(), 'New Lead')]");
//		 Lead card validation
		 String ActualFullName =page.textContent(LeadNameInCard).trim();
		 assert ActualFullName.contains(Fname+" "+Lname) : "First name is not mach"; 
//		    open the history 
		    page.click("(//h3[@class='font-semibold text-lg text-gray-800 break-words'])[1]");
		    Thread.sleep(1000);
		    
		    Locator verifyButton = page.locator("//span[text()='Create']");
	        if (verifyButton.count() > 0) {
	            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	            verifyButton.click();
	            System.out.println("Verify button clicked!");
	        } else {
	            System.out.println("Verify button not found, reloading page...");
	            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
		        page.click("div.cursor-pointer.flex.items-center.justify-end");
	            page.click("//div[contains(text(),'Re-Inquiry')]");
	            page.click("//div[contains(text(),'New Lead')]");
	            page.click(fullDetailsText);
	            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	            verifyButton.click();
	            System.out.println("Verify button clicked after reload!");
	        }
	        Thread.sleep(500);
	        //		    validate lead history details 
	        String ActualFirstNameinHistory =page.textContent(FirstNameinHistory).trim();
	        String ActualLastNameinHistory =page.textContent(LastNameinHistory).trim();
	        String ActualEmailinHistory =page.textContent(EmailinHistory).trim();
	        String ActualCountryCodeinHistory =page.textContent(CountryCodeinHistory).trim();
	        String ActualPhoneinHistory =page.textContent(PhoneinHistory).trim();
	        String ActualAreainHistory =page.textContent(AreainHistory).trim();
	        String ActualAreaTypeinHistory =page.textContent(AreaTypeinHistory).trim();
	        String ActuallandZoneinHistory =page.textContent(landZoneinHistory).trim();
	        String ActuallandForinHistory =page.textContent(landForinHistory).trim();
	        String ActualjantriValueinHistory =page.textContent(jantriValueinHistory).trim();
//	        String ActualSourceNameinHistory =page.textContent(sourceNameinHistory).trim();
//	        String ActualsubSourceName =page.textContent(subSourceNameinHistory).trim();
	        
	        assert ActualFirstNameinHistory.contains(Fname) : "First name is not mach";
	        assert ActualLastNameinHistory.contains(Lname) : "Last name is not mach";
	        assert ActualEmailinHistory.contains(email) : "Email is not mach";
	        assert ActualCountryCodeinHistory.contains(LeadCountryCode) : "Country Code is not mach";
	        assert ActualPhoneinHistory.contains(phone) : "Phone number is not mach";
	        assert ActualAreainHistory.contains(area) : "Area is not mach";
	        assert ActualAreaTypeinHistory.contains(plot) : "Area type is not mach";	        
	        assert ActuallandZoneinHistory.contains(land) : "land Zone is not mach";
	        assert ActuallandForinHistory.contains(proposal) : "land For is not mach";
	        assert ActualjantriValueinHistory.contains(jantri) : "jantri Value is not mach";
//	        assert ActualSourceNameinHistory.contains(Fname) : "Source Name is not mach";
//	        assert ActualsubSourceName.contains(Fname) : "Sub Source is not mach";
	}
	 public void valitionLeadtype(String leadType) {
		 String ActualUserTypeinHistory =page.textContent(UserTypeinHistory).trim();
		 assert ActualUserTypeinHistory.contains(leadType) : "User Type is not mach";
		 
	 }
public void valitionBrokerleadtype(String leadType, String BrokerName,String BrokerCountry,String BrokerNumber) {
    String ActualUserTypeinHistory =page.textContent(UserTypeinHistory).trim();
    String ActualBrokerNameinHistory =page.textContent(BrokerNameinHistory).trim();
    String ActualBrokercountrycodeinHistory =page.textContent(BrokercountrycodeinHistory).trim();
    String ActualBrokerPhoneinHistory =page.textContent(BrokerPhoneinHistory).trim();
    
    assert ActualUserTypeinHistory.contains(leadType) : "User Type is not mach";
    assert ActualBrokerNameinHistory.contains(BrokerName) : "Broker name is not mach";
    assert ActualBrokercountrycodeinHistory.contains(BrokerCountry) : "Broker country code is not mach";
    assert ActualBrokerPhoneinHistory.contains(BrokerNumber) : "Broker phone is not mach";
		 
	 }

public void ValidationLocation() {
	 Locator marker = page.locator(EnterLocationLink);
     ExpectedLocationLink = marker.getAttribute("value");
	String ActualLocationinHistory =page.textContent(EnterLocationLink);
	assert ActualLocationinHistory.contains(ExpectedLocationLink) : "Location is not mach Expected is "+ExpectedLocationLink+" GOT "+ActualLocationinHistory;
}

public void ValidationAgricultureLandType(String type) {
	String ActualLandTypeinHistory =page.textContent(LandTypeinHistory).trim();
	assert ActualLandTypeinHistory.contains(type) : "Land Type is not mach";
}
public void ValidationNon_AgricultureLandType(String type, String TPName, String TPNumber, String FP, String Survey) {
	String ActualLandTypeinHistory =page.textContent(LandTypeinHistory).trim();
	String ActualsurveyNumberinHistory =page.textContent(surveyNumberinHistory).trim();
    String ActualtpNameinHistory =page.textContent(tpNameinHistory).trim();
    String ActualtpNoinHistory =page.textContent(tpNoinHistory).trim();
    String ActualfpNumberinHistory =page.textContent(fpNumberinHistory).trim();
    
	assert ActualLandTypeinHistory.contains(type) : "Land Type is not mach";
	assert ActualsurveyNumberinHistory.contains(Survey) : "survey Number is not mach";
    assert ActualtpNameinHistory.contains(TPName) : "TP name is not mach";
    assert ActualtpNoinHistory.contains(TPNumber) : "TP number is not mach";
    assert ActualfpNumberinHistory.contains(FP) : "FP Number is not mach";
}

public void varifyUploadedDocument(String property, String Satbar, String Part, String Zoning ) {
	String ActualpropertyCardinHistory =page.textContent(propertyCardinHistory).trim();
    String ActualsatBarinHistory =page.textContent(satBarinHistory).trim();
    String ActualpartPlaninHistory =page.textContent(partPlaninHistory).trim();
    String ActualzoningCertificateinHistory =page.textContent(zoningCertificateinHistory).trim();
	
	assert ActualpropertyCardinHistory.contains(property) : "property Card is not mach";
    assert ActualsatBarinHistory.contains(Satbar) : "sat Bar is not mach";
    assert ActualpartPlaninHistory.contains(Part) : "part Plan is not mach";
    assert ActualzoningCertificateinHistory.contains(Zoning) : "zoning Certificate is not mach";
	
}
	 
//	 methods
	 public void SelectTerritory(String territory) {
		 page.selectOption(Territory, new SelectOption().setLabel(territory));
	 }
	 public void EnterFirstName(String FName) {
		 page.fill(FirstName, FName);
	 }
	 public void EnterLastName(String LName) {
		 page.fill(LastName, LName);
	 }
	 public void EnterEmail(String email) {
		 page.fill(Email, email);
	 }
	 public void SelectLeadCountryCode(String leadCoundCode) throws InterruptedException {
		 page.click(LeadcountryCode);
	        page.fill(searchCountryCode, leadCoundCode);
	        Thread.sleep(500);
	        page.click(clickSearchedFirstOption);
	 }
	 public void EnterLeadPhone(String phone) {
		 page.fill(Phone, phone);
	 }
	 public void ClickLeadTypeOption(String landtype) {
		 page.click("//input[@value='"+landtype+"']");
	 }
	 public void EnterBrokerName(String brokerName) {
		 page.fill(BrokerName, brokerName);
	 }
	 public void SelectBrokerCountryCode(String leadCoundCode) throws InterruptedException {
		 page.click(BrokercountryCode);
	     page.fill(searchCountryCode, leadCoundCode);
	     Thread.sleep(500);
	     page.click(clickSearchedFirstOption);
	 }
	 public void EnterBrokerPhone(String phone) {
		 page.fill(BrokerPhone, phone);
	 }
	 public void SelectLocation(String location) throws InterruptedException {
		 page.click(Location);
		 Thread.sleep(2000);
		 page.fill(SearchLocation, location);
//		 page.keyboard().press("Backspace");
		 Thread.sleep(500);
		 page.waitForSelector("div.pac-item");
		 page.locator("div.pac-item").first().click();
		 Thread.sleep(1000);
		// Locate element by XPath
		 Locator marker = page.locator("//gmp-advanced-marker[@class='yNHHyP-marker-view']");
         ExpectedLocationLink = marker.getAttribute("position");
         System.out.println("Marker Position: https://maps.google.com/?q=" + ExpectedLocationLink);

		 page.click(PinYourLocationBtn);
		 Thread.sleep(2000);
		 
	 }
	 public void EnterLocationlink(String location) throws InterruptedException {
		 page.fill(EnterLocationLink, location);
		 
	 }
	 public void SelectLandType(String Type) {
		 page.selectOption(LandType, new SelectOption().setLabel(Type));
	 }
	 public void EnterTPName(String TPName) {
		 page.fill(TP_Name, TPName);
	 }
	 public void EnterTPNumber(String TPNumber) {
		 page.fill(TP_number, TPNumber);
	 }
	 public void EnterPlotFPnumber(String PlotFPnumber) {
		 page.fill(Plot_FP_number, PlotFPnumber);
	 }
	 public void EnterSurveyNumber(String surveyNumber) {
		 page.fill(SurveyNumber, surveyNumber);
	 }
	 public void SelectAreaType(String Type) {
		 page.selectOption(AreaType, new SelectOption().setLabel(Type));
	 }
	 public void EnterPlotArea(String Area) {
		 page.fill(PlotArea, Area);
	 }
	 public void UploadPropertyCard(String filepath) {
		 page.setInputFiles(PropertyCard, Paths.get(filepath));

	 }
	 public void UploadSatBara(String filepath) {
		 page.setInputFiles(SatBara, Paths.get(filepath));

	 }
	 
	 public void UploadPartPlan(String filepath) {
		 page.setInputFiles(Partplan, Paths.get(filepath));

	 }
	 
	 public void UploadZoningCertificate(String filepath) {
		 page.setInputFiles(ZoningCertificate, Paths.get(filepath));

	 }
	 public void SelectLandZone(String Type) {
		 page.selectOption(LandZone, new SelectOption().setLabel(Type));
	 }
	 public void SelectProposal(String Type) {
		 page.selectOption(TypeOFProposal, new SelectOption().setLabel(Type));
	 }
	 public void EnterLandJantriValue(String Value) {
		 page.fill(LandJantriValue, Value);
	 }
	 public void ClickCancelBtn() {
		 page.click(CancelBtn);
	 }
	 public void ClickSubmitBtn() {
		 page.click(SubmitBtn);
	 }
	 public void OpenAddLandLeadForm() throws InterruptedException {
		 page.click(followupMenu);
	        Thread.sleep(1000);
	        page.click(openAddLeadOptions);
	        page.click(selectFundOption);
	        page.click(addLeadBtn);
	        Thread.sleep(1000);
	 }
	

}
