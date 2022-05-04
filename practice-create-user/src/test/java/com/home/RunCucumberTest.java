package com.home;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)

@CucumberOptions(
        glue = "com.home.stepdef", // packages with step definition classes
        features = "classpath:features", // relative path resources
        plugin = {"pretty", "summary"}, // extra plugins for cucumber
        snippets = CAMELCASE
)
public class RunCucumberTest {
}