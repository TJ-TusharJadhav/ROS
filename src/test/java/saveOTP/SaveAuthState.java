package saveOTP;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.Scanner;
import utils.ConfigManager;

public class SaveAuthState {

  public static void main(String[] args) {
    String environment = ConfigManager.getEnvironment();
    String loginUrl = ConfigManager.getLoginUrl();
    String afterLoginUrl = ConfigManager.getProtectedUrl();
    String storagePath = "auth.json";

    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();

      System.out.println("🌍 Environment: " + environment);
      page.navigate(loginUrl);
      System.out.println("==> Please complete login in browser.");
      new Scanner(System.in).nextLine();

      page.navigate(afterLoginUrl);
      page.waitForLoadState();

      context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(storagePath)));
      System.out.println("✅ Saved authenticated session to " + storagePath);
    }
  }
}
