package com.example.tecnoMeet;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.tecnocampus.tecnoMeet.cucumberspringboot")
@ConfigurationParameter(key = "cucumber.glue", value = "com.example.tecnoMeet.steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, summary")
public class TecnoMeetApplicationTests {
}
