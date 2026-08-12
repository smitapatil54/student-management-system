import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/");

        Thread.sleep(3000);

        driver.findElement(By.name("username")).sendKeys("Admin");

        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(10000);

        System.out.println(driver.getTitle());

        driver.quit();
    }
}