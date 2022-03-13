package Batch44N.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderTestBase {

    protected RequestSpecification specJPH;    //extend eden childlari ulassin
    // RequestSpecification , interfacedir

    @Before
    public void setUp(){  //RequestSpecBuilder, restassured dan geliyor
        specJPH=new RequestSpecBuilder().            //bu urli spec olarak kur
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();

    }
}
