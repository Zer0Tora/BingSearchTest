package org.example.bingsearchtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultPage {
    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = "h2 > a[href]")
    private List<WebElement> results;

    private By resultsLocator = By.cssSelector("h2 > a[href]");

    private WebDriver driver;

    private WebDriverWait wait;

    public void clickElement(int numResult) {
        results.get(numResult).click();
        System.out.println("Выполнен клик на " + numResult + " элемент");
    }

    public void waitForElement(String waitFor, int seconds) {
        System.out.println("Ожидаем элемент '" + waitFor + "' " + seconds + " секунд");
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(resultsLocator, "href", waitFor),
                ExpectedConditions.elementToBeClickable(resultsLocator)
        ));
        System.out.println(waitFor + " найден");
    }

    public void goToNewPage() {
        List<String> tabs_windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(tabs_windows.size() - 1));
        System.out.println("Выбрано новое окно браузера");
    }

    public String checkSearchFieldText() {
        System.out.println("Получение значения текста в поле поиска");
        return searchField.getAttribute("value");
    }

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
