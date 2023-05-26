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

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClienteSeleniumTest {

    static WebDriver driver;

    @BeforeAll
    static void init() {

        System.setProperty("webdriver.chrome.driver", "seleniumdriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

    }

    @Test
    void cadastrar_cliente() throws InterruptedException {

        driver.get("http://localhost:8080/cliente");

        driver.manage().timeouts().implicitlyWait(400, TimeUnit.MILLISECONDS);

        WebElement botaoNovo = driver.findElement(By.id("botaoNovo"));

        Thread.sleep(4000);

        botaoNovo.click();

        String tituloPaginaNovoCliente = driver.getTitle();

        Assertions.assertEquals("Novo cliente", tituloPaginaNovoCliente);

        String nomeCliente = "Cliente Selenium " + new Random().nextInt();
        WebElement nome = driver.findElement(By.id("nome"));
        nome.sendKeys(nomeCliente);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("email@selenium.com");

        WebElement form = driver.findElement(By.id("form"));
        form.submit();

        //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        Thread.sleep(2000);

        String tituloPaginaLista = driver.getTitle();

        Assertions.assertEquals("Lista clientes", tituloPaginaLista);

        WebElement nomeClienteLista = driver.findElement(By.id("nome_cliente" + nomeCliente));

        Assertions.assertTrue(nomeClienteLista.isDisplayed());
    }

    @AfterAll
    static void after() {
        driver.quit();
    }


}
