package Batch44N.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppTestBase {

    protected RequestSpecification specHOA;    //extend eden childlari ulassin
    // RequestSpecification , interfacedir. Polimorphism

    @Before
    public void setUp(){  //RequestSpecBuilder, restassured dan geliyor
        specHOA=new RequestSpecBuilder().           //bu urli spec olarak kur
                setBaseUri("https://restful-booker.herokuapp.com").
                build();

    }
}
