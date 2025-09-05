package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {
    private Page page;

    // Locators
    private String usernameField = "#phone";
    private String getOtpBtn = "//button[@type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void loginWithOTP(String phone) throws InterruptedException {
        page.fill(usernameField, phone);
        page.click(getOtpBtn);

        Locator verifyButton = page.locator("button:has-text('Verify')");
        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        page.waitForCondition(() -> verifyButton.isEnabled());
        verifyButton.click();
    }
}
