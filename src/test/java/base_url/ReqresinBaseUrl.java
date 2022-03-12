package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresinBaseUrl {

    protected RequestSpecification specReg;

    @Before
    public void setUp(){

        specReg = new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
    }
}