package com.qaworks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Utils {


    @FindBy(css = "#menu-item-18894")
    WebElement contact;

    public void openContactPage() {
        contact.click();
    }


}


