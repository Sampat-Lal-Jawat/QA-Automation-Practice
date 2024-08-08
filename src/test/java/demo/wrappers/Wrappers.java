package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    static ChromeDriver driver;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
    }

    public static void clickOnElement(ChromeDriver driver, WebElement element){
       try {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
       } catch (Exception e) {
          e.printStackTrace();
       }
    }

    public static void sendKeys(WebElement element, String keysToSend) {
        try{
            element.clear();
            element.sendKeys(keysToSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEpochTimeString(){
            long epochTime = System.currentTimeMillis()/1000;
            String epochTimeString = String.valueOf(epochTime);
            return epochTimeString;
    }

    public static void selectRadioButtonOrCheckBox(ChromeDriver driver, String value) {
        try{
        WebElement radioButtonOrCheckBox = driver.findElement(By.xpath("//span[contains(@class,'OIC90c') and contains(text(), '" + value + "')]"));
        radioButtonOrCheckBox.click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void selectDropdown(ChromeDriver driver, String value) {
       try{
         WebElement dropdownOption = driver.findElement(By.xpath("//div[contains(@class,'QXL7Te')]//div[contains(@data-value,'" + value + "')]"));
        dropdownOption.click();
       } catch(Exception e){
         e.printStackTrace();
       }
    }

    public static String getSevenDaysAgoDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysMinusdate = currentDate.minusDays(7);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatedDate = sevenDaysMinusdate.format(dateFormatter);
        return formatedDate;
    }

}
