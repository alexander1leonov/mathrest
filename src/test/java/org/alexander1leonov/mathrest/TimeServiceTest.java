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

import java.util.StringTokenizer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeServiceTest {
    private static final String TIME_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
    private static final String TIMEZONE_VALUE = "MST-0700";

    @Autowired
    private Environment environment;

    @Before
    public void setup() {
        RestAssured.port = Integer.valueOf(environment.getProperty("local.server.port"));
    }

    @Test
    public void testNow_shouldReturnTimezoneAndCurrentTime() {
        ResponseBody responseBody = RestAssured
                .given().header("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .get("/time/now").getBody();
        String responseTimeValue = responseBody.jsonPath().get("time");

        System.out.println("ResponseTimeValue: '" + responseTimeValue + "'");

        //assert that responseTimeValue is not blank
        Assert.assertTrue(StringUtils.isNotBlank(responseTimeValue));
        //explode result into time and timezone
        StringTokenizer stringTokenizer = new StringTokenizer(responseTimeValue);
        Assert.assertTrue(stringTokenizer.countTokens() == 2);
        //verify time format
        String time = stringTokenizer.nextToken();
        Assert.assertTrue(time.matches(TIME_REGEX));
        //verify timeZone value
        String timeZone = stringTokenizer.nextToken();
        Assert.assertEquals(timeZone, TIMEZONE_VALUE);
    }

}
