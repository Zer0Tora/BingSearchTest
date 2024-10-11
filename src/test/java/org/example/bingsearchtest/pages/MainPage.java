package org.example.bingsearchtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://www.bing.com/

public class MainPage {

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    public void sendText(String text) {
        searchField.sendKeys(text);
        System.out.println("Выполнен поиск по тексту: " + text);
        searchField.submit();
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
