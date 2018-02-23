package com.qaworks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.qaworks.HomePage;
import com.qaworks.ContactPage;

public class ContactUsStepDefs {
    HomePage homePage;
    ContactPage contactPage;

    public ContactUsStepDefs() {
        homePage = new HomePage();
        contactPage = new ContactPage();

    }

    @Given("^I am on the QAWorks \"([^\"]*)\"$")
    public void i_am_on_the_QAWorks(String url) {
//      homePage.visit(url);
    }

    @When("^I navigate to contact page$")
    public void i_navigate_to_contact_page() {
        homePage.openContactPage();

    }

    @Then("^I should be able to contact QAWorks with the \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void i_should_be_able_to_contact_QAWorks_with_the(String name, String email, String sub, String msg) {
        contactPage.fillContactusForm(name, email, sub, msg);
        contactPage.submit();

    }


}




