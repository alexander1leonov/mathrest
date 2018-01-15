package org.alexander1leonov.mathrest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathServiceTest {

    @Autowired
    private Environment environment;

    @Before
    public void setup() {
        RestAssured.port = Integer.valueOf(environment.getProperty("local.server.port"));
    }

    @Test
    public void testGetAdd_shouldReturnValidSum() {
        Double n1 = 2.0;
        Double n2 = 3.0;

        RestAssured
                .given().header("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when().get("/math/add?n1={n1}&n2={n2}", n1, n2)
                .then().statusCode(200)
                    .and().body("sum", Matchers.equalTo(5.0f));

    }

    @Test
    public void testPostAdd_shouldReturnValidSum() {
        Double n1 = 2.0;
        Double n2 = 3.0;

        RestAssured
                .given().header("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .formParam("n1", n1).formParam("n2", n2)
                .when().post("/math/add")
                .then().statusCode(200)
                    .and().body("sum", Matchers.equalTo(5.0f));
    }
}
