package br.edu.utfpr.exemplomaven;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class ExemploTest {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    private static String CHROMEDRIVER_LOCATION = "C:\\chromedriver.exe";

    private static int scId = 0;

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
    }

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //Opcao headless para MacOS e Linux
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void test02() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://github.com/");

        WebElement signUpButton = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/div[2]/div[1]/form/button"));
        signUpButton.click();

        //check msg: "There were problems creating your account."
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/div[2]"));
        assertEquals("There were problems creating your account.", errorMsg.getText().trim());

        //check msg: "Login can't be blank"
        WebElement errorMsg02 = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/dl[1]/dd[2]"));
        assertEquals("Login can't be blank", errorMsg02.getText().trim());

        //fill the username
        WebElement username = driver.findElement(By.id("user_login"));
        username.sendKeys("lucasjcs");

        //click on button "create account"
        WebElement caButton = driver.findElement(By.id("signup_button"));
        caButton.click();

        try {
            errorMsg02 = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/dl[1]/dd[2]"));
            fail();
        } catch (NoSuchElementException e) {
        }
        driver.close();
    }
}