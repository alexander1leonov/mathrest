package org.alexander1leonov.mathrest;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeServiceTest {
    private static final Format TIME_FORMAT = new SimpleDateFormat("HH:mm:ss zZ");

    @Autowired
    private Environment environment;

    @Before
    public void setup() {
        RestAssured.port = Integer.valueOf(environment.getProperty("local.server.port"));
    }

    @Test
    public void testNow_shouldReturnTimezoneAndCurrentTime() {
        Date dateBefore = new Date();
        ResponseBody responseBody = RestAssured
                .given().header("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .get("/time/now").getBody();
        String responseTimeValue = responseBody.jsonPath().get("time");
        Date dateAfter = new Date();

        System.out.println("ResponseTimeValue: '" + responseTimeValue + "'");

        //assert that responseTimeValue is not blank
        Assert.assertTrue(StringUtils.isNotBlank(responseTimeValue));
        //assert that time before is less or equal to responseTimeValue
        Assert.assertTrue(getFormattedTime(dateBefore).compareTo(responseTimeValue) <= 0);
        //assert that time after is greater or equal to responseTimeValue
        Assert.assertTrue(getFormattedTime(dateAfter).compareTo(responseTimeValue) >= 0);
    }

    private String getFormattedTime(Date date) {
        return TIME_FORMAT.format(date);
    }
}
