package tests.Add_Lead;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.PhoneNumber;
@Listeners(ExtentTestNGListener.class)

public class AddLandLeadTest extends BaseTest{
	public String phone;
	public String firstName;
	public String LastName;
	@BeforeMethod
	public void beforemethod() throws InterruptedException {
		addLandLead.OpenAddLandLeadForm();
		phone = PhoneNumber.generateUniquePhoneNumber();
		firstName = faker.name().firstName();
	    LastName = faker.name().lastName();
		
	}
	@AfterMethod
public void Aftermethod() throws InterruptedException {
		page.reload();
		Locator verifyButton = page.locator("(//p[@data-size='sm'])[4]");
        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
         
		
	}
	@Test (priority = 1)
	public void Sachana() throws InterruptedException { 
		String Territory ="Sachana";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+91";
		String LeadType ="LandOwner";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Agriculture";
		String AreaType ="Sq Yards";
		String PlotArea ="500";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="R1";
		String Proposal ="For Sale";
		String LandJantriValue ="2500";
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationAgricultureLandType(LandType);
		
		
	}
	@Test(priority = 2)
	public void Ognaj() throws InterruptedException {
		String Territory ="Ognaj";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+1";
		String LeadType ="Broker";
		String BrokerName ="Nikhil Sinha";
		String Brokercountry ="+34";
		String Brokerphone ="9845012398";
		String Location ="https://maps.app.goo.gl/WBNMqmRqwXfpae6a9";
		String LandType ="Non-Agriculture";
		String TPName ="TP-02";
		String TPNumber ="T002";
		String PlotFPnumber ="FP-202";
		String SurveyNumber ="456/2A";
		String AreaType ="Sq Mtr";
		String PlotArea ="420";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="R2";
		String Proposal ="Joint Venture";
		String LandJantriValue ="3200";
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.EnterBrokerName(BrokerName);
		addLandLead.SelectBrokerCountryCode(Brokercountry);
		addLandLead.EnterBrokerPhone(Brokerphone);
		addLandLead.EnterLocationlink(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.EnterTPName(TPName);
		addLandLead.EnterTPNumber(TPNumber);
		addLandLead.EnterPlotFPnumber(PlotFPnumber);
		addLandLead.EnterSurveyNumber(SurveyNumber);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionBrokerleadtype(LeadType,BrokerName,Brokercountry,Brokerphone);
//		addLandLead.ValidationLocation();
		addLandLead.ValidationNon_AgricultureLandType(LandType,TPName,TPNumber,PlotFPnumber,SurveyNumber);
	}
	@Test(priority = 3)
	public void Shilaj() throws InterruptedException { 
		String Territory ="Shilaj";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+44";
		String LeadType ="Developer";
		String Location ="Satellite road";
		String LandType ="Agriculture";
		String AreaType ="Bigha";
		String PlotArea ="2";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="R3";
		String Proposal ="Joint Development";
		String LandJantriValue ="1800";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
//		addLandLead.ValidationLocation();
		addLandLead.SelectLandType(LandType);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
		addLandLead.ValidationAgricultureLandType(LandType);
		
	}
	@Test(priority = 4)
	public void Satellite() throws InterruptedException {
		String Territory ="Satellite";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+61";
		String LeadType ="LandOwner";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Non-Agriculture";
		String TPName ="TP-04";
		String TPNumber ="T004";
		String PlotFPnumber ="A-12";
		String SurveyNumber ="321/B";
		String AreaType ="Acre";
		String PlotArea ="1.5";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="RAH";
		String Proposal ="Open for discussion";
		String LandJantriValue ="4000";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.EnterTPName(TPName);
		addLandLead.EnterTPNumber(TPNumber);
		addLandLead.EnterPlotFPnumber(PlotFPnumber);
		addLandLead.EnterSurveyNumber(SurveyNumber);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationNon_AgricultureLandType(LandType,TPName,TPNumber,PlotFPnumber,SurveyNumber);
	}
	@Test(priority = 5)
	public void Bodakdev() throws InterruptedException { 
		String Territory ="Bodakdev";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+81";
		String LeadType ="Broker";
		String BrokerName ="Yash Thakur";
		String Brokercountry ="+86";
		String Brokerphone ="8822334455";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Agriculture";
		String AreaType ="Sq Yards";
		String PlotArea ="275";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="Industrial";
		String Proposal ="For Sale";
		String LandJantriValue ="2750";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.EnterBrokerName(BrokerName);
		addLandLead.SelectBrokerCountryCode(Brokercountry);
		addLandLead.EnterBrokerPhone(Brokerphone);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionBrokerleadtype(LeadType,BrokerName,Brokercountry,Brokerphone);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationAgricultureLandType(LandType);
	}
	@Test(priority = 6)
	public void Panjrapole() throws InterruptedException {
		String Territory ="Panjrapole";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+49";
		String LeadType ="Developer";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Non-Agriculture";
		String TPName ="TP-Ahmedabad-West";
		String TPNumber ="AW-06 (Ahmedabad-West)";
		String PlotFPnumber ="FP-507";
		String SurveyNumber ="258/4C";
		String AreaType ="Sq Mtr";
		String PlotArea ="310";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="Logistic";
		String Proposal ="Joint Venture";
		String LandJantriValue ="3500";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.EnterTPName(TPName);
		addLandLead.EnterTPNumber(TPNumber);
		addLandLead.EnterPlotFPnumber(PlotFPnumber);
		addLandLead.EnterSurveyNumber(SurveyNumber);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationNon_AgricultureLandType(LandType,TPName,TPNumber,PlotFPnumber,SurveyNumber);
	}
	@Test(priority = 7)
	public void GiftCity() throws InterruptedException { 
		String Territory ="Gift City";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+65";
		String LeadType ="Broker";
		String BrokerName ="Aman Dubey";
		String Brokercountry ="+60";
		String Brokerphone ="9760011223";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Agriculture";
		String AreaType ="Bigha";
		String PlotArea ="3";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="Any Other";
		String Proposal ="Joint Development";
		String LandJantriValue ="5000";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.EnterBrokerName(BrokerName);
		addLandLead.SelectBrokerCountryCode(Brokercountry);
		addLandLead.EnterBrokerPhone(Brokerphone);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionBrokerleadtype(LeadType,BrokerName,Brokercountry,Brokerphone);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationAgricultureLandType(LandType);
	}
	@Test(priority = 8)
	public void Ambli() throws InterruptedException {
		String Territory ="Ambli";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+971";
		String LeadType ="LandOwner";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Non-Agriculture";
		String TPName ="TP-Gandhinagar-03";
		String TPNumber ="GN-03 (Gandhinagar)";
		String PlotFPnumber ="Plot-77";
		String SurveyNumber ="741/5";
		String AreaType ="Acre";
		String PlotArea ="0.75";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="R1";
		String Proposal ="Open for discussion";
		String LandJantriValue ="2200";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.EnterTPName(TPName);
		addLandLead.EnterTPNumber(TPNumber);
		addLandLead.EnterPlotFPnumber(PlotFPnumber);
		addLandLead.EnterSurveyNumber(SurveyNumber);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationNon_AgricultureLandType(LandType,TPName,TPNumber,PlotFPnumber,SurveyNumber);
	}
	@Test(priority = 9)
	public void VaishnoDevi() throws InterruptedException { 
		String Territory ="Vaishno Devi";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+92";
		String LeadType ="Developer";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Agriculture";
		String AreaType ="Sq Yards";
		String PlotArea ="650";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="Industrial";
		String Proposal ="For Sale";
		String LandJantriValue ="2900";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationAgricultureLandType(LandType);
	}
	@Test(priority = 10)
	public void Shela() throws InterruptedException {
		String Territory ="Shela";
		String email =firstName+LastName+"@yopmail.com";
		String LeadCountryCode ="+880";
		String LeadType ="LandOwner";
		String Location ="Limbani Complex Ambli Road";
		String LandType ="Non-Agriculture";
		String TPName ="TP-Rajkot-South";
		String TPNumber ="RS-08 (Rajkot-South)";
		String PlotFPnumber ="FP-202";
		String SurveyNumber ="963/6B";
		String AreaType ="Bigha";
		String PlotArea ="1";
		String PropertyCard ="src/test/java/Documents/propertyCard.pdf";
		String SatBara ="src/test/java/Documents/7:12.pdf";
		String PartPlan ="src/test/java/Documents/partPlan.jpg";
		String ZoningCertificate ="src/test/java/Documents/ZoningCertificate.jpg";
		String LandZone ="R2";
		String Proposal ="Joint Venture";
		String LandJantriValue ="3100";
		
		addLandLead.SelectTerritory(Territory);
		addLandLead.EnterFirstName(firstName);
		addLandLead.EnterLastName(LastName);
		addLandLead.EnterEmail(email);
		addLandLead.SelectLeadCountryCode(LeadCountryCode);
		addLandLead.EnterLeadPhone(phone);
		addLandLead.ClickLeadTypeOption(LeadType);
		addLandLead.SelectLocation(Location);
		addLandLead.SelectLandType(LandType);
		addLandLead.EnterTPName(TPName);
		addLandLead.EnterTPNumber(TPNumber);
		addLandLead.EnterPlotFPnumber(PlotFPnumber);
		addLandLead.EnterSurveyNumber(SurveyNumber);
		addLandLead.SelectAreaType(AreaType);
		addLandLead.EnterPlotArea(PlotArea);
		addLandLead.UploadPropertyCard(PropertyCard);
		addLandLead.UploadSatBara(SatBara);
		addLandLead.UploadPartPlan(PartPlan);
		addLandLead.UploadZoningCertificate(ZoningCertificate);
		addLandLead.SelectLandZone(LandZone);
		addLandLead.SelectProposal(Proposal);
		addLandLead.EnterLandJantriValue(LandJantriValue);
		addLandLead.ClickSubmitBtn();
		addLandLead.VerifyLeadbasicDetails(Territory, firstName, LastName, email, LeadCountryCode, phone,PlotArea ,AreaType,LandZone,Proposal,LandJantriValue);
		addLandLead.valitionLeadtype(LeadType);
//		addLandLead.ValidationLocation(Location);
		addLandLead.ValidationNon_AgricultureLandType(LandType,TPName,TPNumber,PlotFPnumber,SurveyNumber);
	}
	
	

}
