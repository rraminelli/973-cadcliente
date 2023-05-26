package br.com.ada3.cadcliente;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {

        System.setProperty("webdriver.chrome.driver", "seleniumdriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

    }

    @Test
    void testandoDriver() {

        driver.get("https://ada.tech/");

        String titulo = driver.getTitle();
        Assertions.assertEquals("Ada | A Nova Educação", titulo);

        driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);

        //WebElement searchBox = driver.findElement(By.name("q"));

        WebElement botaoAluno = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[3]/a[1]"));

        botaoAluno.click();

        String tituloPaginaAluno = driver.getTitle();

        Assertions.assertEquals("Ada | Programas de Formação em Tecnologia", tituloPaginaAluno);

    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }
}