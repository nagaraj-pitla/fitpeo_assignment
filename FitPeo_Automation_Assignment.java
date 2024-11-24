package Programs;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FitPeo_Automation_Assignment {
public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        try {
        
     // 1. Navigate to FitPeo Homepage
        	
		driver.get("https://fitpeo.com");	
		driver.manage().window().maximize();
		
	// 2. Navigate to Revenue Calculator Page
			WebElement calculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Calculator')]")));
            calculatorLink.click();
            
    // 3. Scroll down to the slider section
            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='range']")));
            js.executeScript("arguments[0].scrollIntoView()", slider);
            
     // 4. Adjust the slider to 820
            actions.clickAndHold(slider).moveByOffset(94, 0).release().perform();
            
     // 5. Update the value to 560       
          WebElement textBox = driver.findElement(By.xpath("//input[@type='number']"));
          textBox.click();
          actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).build().perform();         
          textBox.sendKeys("560");
          
      // 6.Validate Slider Value:
          if (slider.getAttribute("value").equals("560"))
        	  System.out.println("Slider value is 560");
          else
        	  System.out.println("Slider value is not 560");  
          
       //7.Select CPT Codes:
          driver.findElement(By.xpath("//span[contains(text(),'57')]")).click(); //CPT-99091
          driver.findElement(By.xpath("//span[contains(text(),'19.19')]")).click(); //CPT-99453
          driver.findElement(By.xpath("//span[contains(text(),'63')]")).click(); //CPT-99454
          driver.findElement(By.xpath("//span[contains(text(),'15')]")).click(); //CPT-99474
          
       // 8. Validate Total Recurring Reimbursement:   
          String Total_Reimbursment = driver.findElement(By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")).getText();
          System.out.println("Total Recurring Reimbursement is: " + Total_Reimbursment);
          if (Total_Reimbursment.contains("$75600"))
        	  System.out.println("All test cases passed successfully!");
          else
        	  System.out.println("Reimbursment amount is not matching");      
        }   
        
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
            // Close the browser
            driver.quit();
        }

}
}