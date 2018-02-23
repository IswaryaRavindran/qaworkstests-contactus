package com.qaworks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends Utils {

    @FindBy(name = "your-name")
    WebElement name;

    @FindBy(name = "your-email")
    WebElement email;

    @FindBy(name = "your-company")
    WebElement subject;

    @FindBy(name = "your-message")
    WebElement message;

    @FindBy(css = "#contact-us-send")
    WebElement submit;


    public void setName(String nme) {
        type(name, nme);
    }

    public void setEmail(String mail) {
        type(email, mail);
    }

    public void setSubject(String sub) {
        type(subject, sub);
    }

    public void setMessage(String msg) {
        type(message, msg);
    }

    public void fillContactusForm(String name, String mail, String sub, String info) {
//            isElementPresent("your-email","name");
        setName(name);
//            isElementPresent("your-company","name");
        setEmail(mail);
//            isElementPresent("#your-message","name");
        setSubject(sub);
        click(message);
        setMessage(info);
    }

    public void submit() {
        isElementPresent("#contact-us-send", "css");
        click(submit);

    }
}



