package dev.catalogue.topology.application.java;

import io.cucumber.junit.Cucumber;
import io.quarkus.test.junit.QuarkusTest;
import io.cucumber.junit.CucumberOptions;
import io.quarkiverse.cucumber.CucumberQuarkusTest;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-result"},
        features = "src/test/resources/network",
        glue = "dev.catalogue.topology.application.java"
)
@QuarkusTest
public class ApplicationTest {
	Addnetwork nettest = new Addnetwork();
}