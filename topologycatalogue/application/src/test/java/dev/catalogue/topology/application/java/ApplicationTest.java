package dev.catalogue.topology.application.java;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-result"},
        features = "src/test/resources/network",
        glue = "dev.catalogue.topology.application.java"
)
public class ApplicationTest {
	Addnetwork nettest = new Addnetwork();
}