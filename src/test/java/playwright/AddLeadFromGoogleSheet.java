package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class AddLeadFromGoogleSheet {

    // Method to read CSV from Google Sheet URL
    public static List<String[]> readCsvFromGoogleSheet(String csvUrl) throws Exception {
        URL url = new URL(csvUrl);
        try (CSVReader reader = new CSVReader(new InputStreamReader(url.openStream()))) {
            return reader.readAll();
        }
    }

    public static void main(String[] args) {
        String csvUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=1747749490";
        String formUrl = "https://shivalikgroup.com/projects/greenfield";

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            List<String[]> csvData = readCsvFromGoogleSheet(csvUrl);

            // Skip header row (assumes first row is header)
            for (int i = 1; i < csvData.size(); i++) {
                String[] row = csvData.get(i);

                String fname = row[0];
                String lname = row[1];
                String email = row[2];
                String phone = row[3];

                page.navigate(formUrl);

                // Fill text fields
                page.getByText("Enquire Now").click();
                page.fill("#firstName", fname);
                page.fill("#lastName", lname);
                page.fill("#email", email);
                page.fill("//input[@name='phoneNumber']", phone);
                page.click("//div[@id='react-select-2-placeholder']"); 
                page.click("//div[text()='I want to Buy']");

                // Submit form
                page.click("button[type='submit']");
                Thread.sleep(1000);
            }

            browser.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
