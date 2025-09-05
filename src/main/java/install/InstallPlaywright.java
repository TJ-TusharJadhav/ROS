package install;

import com.microsoft.playwright.*;

public class InstallPlaywright {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            System.out.println("Playwright installed and ready.");
        }
    }
}
