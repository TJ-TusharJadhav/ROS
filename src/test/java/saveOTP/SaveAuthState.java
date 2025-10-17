package saveOTP;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import utils.ConfigManager;

public class SaveAuthState {

    public static void main(String[] args) {
        String environment = ConfigManager.getEnvironment();
        String storagePath = "auth-" + environment + ".json";

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            
            BrowserContext context;
            if (Files.exists(Paths.get(storagePath))) {
                System.out.println("✅ Loading existing auth state: " + storagePath);
                context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(storagePath)));
            } else {
                System.out.println("🌍 Environment: " + environment);
                context = browser.newContext();
                Page page = context.newPage();

                page.navigate(ConfigManager.getLoginUrl());
                System.out.println("==> Please complete login in browser.");
                new Scanner(System.in).nextLine();

                page.navigate(ConfigManager.getProtectedUrl());
                page.waitForLoadState();

                context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(storagePath)));
                System.out.println("✅ Saved authenticated session to " + storagePath);
            }

            // Now you can use 'context' to open pages with already-authenticated session
            Page page = context.newPage();
            page.navigate(ConfigManager.getProtectedUrl());
            System.out.println("🌟 Opened protected page with saved auth state.");
        }
    }
}
