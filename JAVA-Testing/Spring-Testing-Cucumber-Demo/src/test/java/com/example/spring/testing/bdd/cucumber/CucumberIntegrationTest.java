package com.example.spring.testing.bdd.cucumber;

import com.example.spring.testing.bdd.cucumber.config.SpringIntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "src/test/resources",
                  plugin = {
                            "pretty",
                            "html:target/cucumber-report",
                            "json:target/cucumber-reports/CucumberTestReport.json"},
                  monochrome = true)
public class CucumberIntegrationTest extends SpringIntegrationTest {
}
