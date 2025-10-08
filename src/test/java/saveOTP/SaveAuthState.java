package saveOTP;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveAuthState {
  public static void main(String[] args) {
    // Replace these with your app's URLs
    String loginUrl = "https://admin-core-development.realestateos.io/login";
    String afterLoginUrl = "https://admin-core-development.realestateos.io/users";
    String storagePath = "auth.json";

    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false)); // visible so you can login manually (or automate)
      BrowserContext context = browser.newContext();
      Page page = context.newPage();

      page.navigate(loginUrl);

      System.out.println("==> Please complete login (enter OTP if needed) in the opened browser.");
      System.out.println("Press ENTER here once you're logged in and redirected to your app.");
      // If you want to automate login you can replace manual step with page.fill/click flows.

      // Wait for user to press Enter in console after completing login
      new Scanner(System.in).nextLine();

      // Optional: verify login by checking an element or URL
      page.navigate(afterLoginUrl); // navigate to a protected page to ensure auth is valid
      page.waitForLoadState();

      // Save storage state (cookies + localStorage/sessionStorage) to file
      context.storageState(new BrowserContext.StorageStateOptions()
        .setPath(Paths.get(storagePath)));

      System.out.println("Saved authenticated storage state to " + storagePath);

      context.close();
      browser.close();
    }
  }
}

