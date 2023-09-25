package com.runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/GreenKartWasid.feature", 
glue = {"com.steps"},
monochrome=true,
plugin = {"pretty", "junit:target/JUnitReports/report.xml",
		"json:target/JSONReports/report.json",
		"html:target/HtmlReports/report.html",
		"json:target/cucumber.json"}
		)
public class TestRunnerGreenKart {

	
}
