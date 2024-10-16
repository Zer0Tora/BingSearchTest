package org.example.bingsearchtest.tests;

import org.example.bingsearchtest.pages.MainPage;
import org.example.bingsearchtest.pages.ResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    private WebDriver driver;
    MainPage mp;
    ResultPage rp;
    String input = "selenium";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.bing.com/");
        mp = new MainPage(driver);
        rp = new ResultPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("www.selenium.dev открывается первой ссылкой при поиске через bing.com")
    public void searchResultTest() {
        mp.sendText(input);
        rp.waitForElement("selenium.dev", 6);
        rp.clickElement(0);
        rp.goToNewPage();
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Не совпадает URL");
    }

    @Test
    @DisplayName("Введённое значение отображается после поиска")
    public void searchFieldTest() {
        mp.sendText(input);
        assertEquals(input, rp.checkSearchFieldText(), "Текст в поле не равен: " + input);
    }
}