package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddCPLeadPage {
    private Page page;
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String clickSearchedFirstOption = "//li[@role='option']";
    private String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
    private String selectFundOption = "//div[text()='Fund']";
    private String SigIn_Name="(//p[@class='mantine-focus-auto m_b6d8b162 mantine-Text-root'])[1]";
    
    public AddCPLeadPage(Page page) {
        this.page = page;
    }
    public void addLeadWithBasic(String projectName, String fname, String lname, String country,
                        String phone, String mail, String source, String subSource, String note) throws InterruptedException {
    	
                        }
    
}

