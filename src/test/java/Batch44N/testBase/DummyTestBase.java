package Batch44N.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyTestBase {
    protected RequestSpecification specDum;    //extend eden childlari ulassin
    // RequestSpecification , interfacedir

    @Before
    public void setUp(){  //RequestSpecBuilder, restassured dan geliyor
        specDum=new RequestSpecBuilder().            //bu urli spec olarak kur
                setBaseUri("http://dummy.restapiexample.com/api/v1").
                build();
    }
}
