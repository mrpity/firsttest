package com.gmail.email;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URI;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    //static WebDriver driver;
    static RemoteWebDriver driver;

    @BeforeClass
    public static void setup() {
        //System.setProperty("webdriver.chrome.driver", "/home/dkhodakivsky/Downloads/webdriver/chromedriver");
        //driver = new ChromeDriver();
        DesiredCapabilities capabilities = new DesiredCapabilities();


       capabilities.setCapability("screenResolution", "1920x1080");
       capabilities.setBrowserName("chrome");
        capabilities.setVersion("70.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("env", Collections.singletonList("LANG=ru_RU.UTF-8"));
        capabilities.setCapability("timeZone", "Europe/Kiev");
        capabilities.setCapability("sessionTimeout", "5m");
        capabilities.setCapability("name", LocalDateTime.now().toString());

      
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mail.ukr.net/desktop/login");
        System.out.println("driver=" + driver);
    }

    @Test
    public void userLogin() {
        System.out.println("driver=" + driver);
        WebElement loginField = driver.findElement(By.xpath("//*[@id='id-1']"));
        loginField.sendKeys("LOGIN");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id='id-2']"));
        passwordField.sendKeys("PASSSWWD");
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='button is-type-submit is-size-large form__submit']"));
        loginButton.click();
    }

    @AfterClass
    public static void tearDown() {
//        WebElement menuUser = driver.findElement(By.cssSelector(".login-button__menu-icon"));
//        menuUser.click();
//        WebElement logoutButton = driver.findElement(By.id("login__logout"));
//        logoutButton.click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.quit();
    }

}
