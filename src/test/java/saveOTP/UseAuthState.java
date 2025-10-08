package saveOTP;

import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class UseAuthState {
  public static void main(String[] args) throws InterruptedException {
    String storagePath = "auth.json";
    String protectedPage = "https://admin-core-development.realestateos.io/users"; // a page that requires auth

    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false)); // set true for headless CI runs
      // Create a context preloaded with the saved storage state (cookies + storage)
      Browser.NewContextOptions options = new Browser.NewContextOptions()
        .setStorageStatePath(Paths.get(storagePath));

      BrowserContext context = browser.newContext(options);
      Page page = context.newPage();

      // Directly visit a protected page — you should already be authenticated
      page.navigate(protectedPage);
      page.waitForLoadState();

      System.out.println("Visited protected page. You should be logged in via loaded storage state.");
//      Thread.sleep(10000);

      // ... continue with tests
      // e.g., page.locator("selector").click() etc.

      // Close when done
      context.close();
      browser.close();
    }
  }
}

