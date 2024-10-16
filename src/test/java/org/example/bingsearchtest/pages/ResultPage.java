package org.example.bingsearchtest.pages;

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

    @FindBy(css = "h2 > a[href*='selenium.dev']")
    private List<WebElement> results;

    private WebDriver driver;
    private WebDriverWait wait;

    public void clickElement(int numResult) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        results = wait.until(ExpectedConditions.visibilityOfAllElements(results));
        wait.until(ExpectedConditions.elementToBeClickable(results.get(numResult)));
        results.get(numResult).click();
        System.out.println("Выполнен клик на " + numResult + " элемент");
    }

    public void goToNewPageIfExist() {
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
