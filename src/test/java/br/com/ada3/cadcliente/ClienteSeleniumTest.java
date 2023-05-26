package br.com.ada3.cadcliente;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClienteSeleniumTest {

    static WebDriver driver;

    static void init() {

        System.setProperty("webdriver.chrome.driver", "seleniumdriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

    }



}
