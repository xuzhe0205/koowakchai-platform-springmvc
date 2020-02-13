package com.koowakchai.store.Automation.SignupScenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignupTest1 {
    public WebDriver driver;
    @Test
    public void testBuyProduct() {
        System.out.println("????!!!!");
        driver.findElement(By.id("twotabsearchtextbox")).click();
        WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
        searchInput.sendKeys("Bunnies By The Bay Plush Lamb Toy, Wee Kiddo");
        driver.findElement(By.className("nav-input")).click();
        WebElement productImg;
        productImg = driver.findElement(By.cssSelector("a[href*='Bunnies-Bay-Plush-Lamb']"));
        productImg.click();
        driver.findElement(By.id("add-to-cart-button")).click();
        driver.findElement(By.id("hlb-ptc-btn-native")).click();
    }
    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.baidu.com");
    }


    @AfterTest
    public void afterTest() {
    }
}
