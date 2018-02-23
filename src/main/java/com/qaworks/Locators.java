package com.qaworks;

import org.openqa.selenium.By;

public enum Locators {

    XPATH {
        public By getLocator(String element) {
            return By.xpath(element);
        }
    },

    CSS {
        public By getLocator(String element) {
            return By.cssSelector(element);
        }
    };

    public abstract By getLocator(String element);
}


