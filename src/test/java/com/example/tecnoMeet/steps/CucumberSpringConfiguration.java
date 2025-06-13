package com.example.tecnoMeet.steps;

import com.example.tecnoMeet.TecnoMeetApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TecnoMeetApplication.class, properties = "spring.profiles.active=db-h2")
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {
}
