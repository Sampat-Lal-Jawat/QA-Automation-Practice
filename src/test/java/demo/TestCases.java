package demo;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @Test
    public void testCase01() throws InterruptedException{
    // 1. Navigate to google form.
     driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
     Thread.sleep(1000);

    //  2. Enter name in the 1st text box
     WebElement nameInputElement = driver.findElement(By.xpath("//div[contains(@class,'Xb9hP')]/input[@type='text']"));
     Wrappers.sendKeys(nameInputElement,"Crio Learner");
     Thread.sleep(500);
     
    //  3. Write down "I want to be the best QA Engineer! "epoch time"' 
     WebElement practiceAutomationTextArea = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
     String practiceAutomationText = "I want to be the best QA Engineer!";
     String epochTimeString = Wrappers.getEpochTimeString();
     Wrappers.sendKeys(practiceAutomationTextArea, practiceAutomationText + " " + epochTimeString);
     Thread.sleep(500);

    // 4. Enter your Automation Testing experience in the radio button
      Wrappers.selectRadioButtonOrCheckBox(driver, "6 - 10");
      Thread.sleep(500);
    

    // 5. Select Java, Selenium and TestNG from the check-box
      Wrappers.selectRadioButtonOrCheckBox(driver, "Java");
      Wrappers.selectRadioButtonOrCheckBox(driver, "Selenium");
      Wrappers.selectRadioButtonOrCheckBox(driver, "TestNG");
      Thread.sleep(500);

    // 6. Select Mr in the dropdown
      WebElement dropdown = driver.findElement(By.xpath("//span[contains(text(), 'Choose')]"));
      Wrappers.clickOnElement(driver, dropdown);
      Thread.sleep(300);

      Wrappers.selectDropdown(driver, "Mr");
      Thread.sleep(300);

    // 7. What was the date 7 days ago?
      WebElement dateElement = driver.findElement(By.xpath("//input[@type='date']"));
      Thread.sleep(300);
      String sevenDaysAgoDate = Wrappers.getSevenDaysAgoDate();
      Wrappers.sendKeys(dateElement, sevenDaysAgoDate);
      Thread.sleep(300);

    // 8.Provide current time in the time field
        WebElement hourField = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minuteField = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
     
        Thread.sleep(300);

      // Set the time to 07:30
        String hours = "07";
        String minutes = "30";

        Wrappers.sendKeys(hourField, hours);
        Wrappers.sendKeys(minuteField, minutes);
       Thread.sleep(300);

    // 9. Submit the form
    WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']"));
    Wrappers.clickOnElement(driver, submitButton);

    // 10 Print the success message on the console upon successful completion
    WebElement messageElement = driver.findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));
    String message = messageElement.getText();
    if(message.contains("Thanks for your response")){
        System.out.println(message);
    } else {
        System.out.println("Test case failed");
    }
} 
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}