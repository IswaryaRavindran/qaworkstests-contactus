package com.qaworks;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;

@RunWith(Cucumber.class)

@CucumberOptions(features = {"src/test/resources"},
        format = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        tags = {"@test"}
)
public class RunnerTest {
    @BeforeClass

    public static void start() throws MalformedURLException, InterruptedException {
        BrowserFactory.StartBrowser("Firefox", "http://www.qaworks.com/");
    }

    @AfterClass
    public static void stop() {
        BrowserFactory.QuitBrowser();
    }


}




